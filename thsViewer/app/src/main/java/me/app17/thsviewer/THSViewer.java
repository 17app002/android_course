package me.app17.thsviewer;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import me.app17.thsviewer.util.JDBCUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class THSViewer implements Runnable {

    final class ThsData {
        String date;
        String no;
        String price;
        String startLoc;
        String endLoc;
        String startTime;
        String endTime;
    }

    //資料庫對應欄位名稱
    String[] columns = {"no", "date", "start_loc", "end_loc", "start_time", "end_time", "price"};
    ListView listView;

    public THSViewer(View view) {
        listView = (ListView) view;
    }


    /**
     * 取得資料庫資料
     * @param resultSet
     * @param length
     * @return
     */
    public List<ThsData> getDbData(ResultSet resultSet, int length) {
        int count = 0;
        List<ThsData> thsDataList = new ArrayList<>();
        try {
            //取得資料
            while (resultSet.next()) {
                ThsData thsData = new ThsData();
                thsData.date = resultSet.getString("date");
                thsData.no = resultSet.getString("no");
                thsData.startLoc = resultSet.getString("start_loc");
                thsData.endLoc = resultSet.getString("end_loc");
                thsData.startTime = resultSet.getString("start_time");
                thsData.endTime = resultSet.getString("end_time");
                thsData.price = resultSet.getString("price");
                thsDataList.add(thsData);
                if (++count >= length) {
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return thsDataList;
    }


    /***
     * 取得HashMap格式資料
     * @param thsDataList
     * @return
     */
    public List<Map<String, Object>> getListData(List<ThsData> thsDataList) {

        List<Map<String, Object>> mapList = new ArrayList<>();

        for (ThsData data : thsDataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("no", data.no);
            map.put("date", data.date);
            map.put("start_loc", data.startLoc);
            map.put("end_loc", data.endLoc);
            map.put("start_time", data.startTime);
            map.put("end_time", data.endTime);
            map.put("price", data.price);

            mapList.add(map);
        }

        return mapList;
    }

     /***
     * 更新listView
     * @param mapList
     */
    public void updateListView(List<Map<String, Object>> mapList) {

        final SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.instance,
                mapList, R.layout.ths_listview_layout,
                columns, new int[]{R.id.thsno_tv, R.id.date_tv, R.id.start_tv, R.id.end_tv,
                R.id.startime_tv, R.id.endtime_tv, R.id.price_tv});

        //回到主線程更新UI
        MainActivity.uiHandler.post(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(simpleAdapter);
            }
        });
    }


    /***
     * 取得資料庫連結
     * @return
     */
    public Connection getConnection() {
        MainActivity.showMessage("遠端主機連線中......");
        Connection connection = JDBCUtil.getConnection("db4free.net", "iiiplay001", "me516888", "ths_data");

        return connection;
    }

    /***
     * 執行線程
     */
    @Override
    public void run() {
        Connection connection = getConnection();
        if (connection == null) {
            MainActivity.showMessage("連線失敗!");
            return;
        }
        Log.i("info", "連線成功，取得資料中...");

        try {
            Statement statement = connection.createStatement();
            String sqlStr = "select * from ths_data;";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            if (resultSet == null) {
                MainActivity.showMessage("目前無資料");
                return;
            }

            //取得目前共幾筆資料
            resultSet.last();
            MainActivity.showMessage("共" + resultSet.getRow() + "筆資料");
            //移動到資料最前面
            resultSet.beforeFirst();
            List<ThsData> thsDataList = getDbData(resultSet, 100);

            //刷新ListView
            if (thsDataList.size() > 0) {
                updateListView(getListData(thsDataList));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
