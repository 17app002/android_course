package me.app17.thsviewer;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AlertDialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.app17.thsviewer.util.JDBCUtil;


public class THSViewer {

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
    private String[] columns = {"no", "date", "start_loc", "end_loc", "start_time", "end_time", "price"};
    private ListView listView;
    private List<Map<String, Object>> mapList;


    private static AlertDialog loadingDialog;

    public THSViewer(View view) {
        listView = (ListView) view;
    }

    /***
     * 取得mapList
     * @return
     */
    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    /***
     * 取得資料長度
     * @return
     */
    public int getListCount() {
        return mapList.size();
    }

    /**
     * 取得資料庫資料
     *
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
     * 更新資料
     */
    public void update() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //產生loadingBar
                MainActivity.uiHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.instance);
                        dialog.setView(MainActivity.instance.getLayoutInflater().inflate(R.layout.ths_loading_layout, null));
                        loadingDialog = dialog.create();
                        loadingDialog.setCanceledOnTouchOutside(false);
                        loadingDialog.show();
                    }
                });


                Connection connection = getConnection();
                if (connection == null) {
                    loadingDialog.dismiss();
                    MainActivity.showMessage("連線失敗!");
                    return;
                }
                Log.i("info", "連線成功，取得資料中...");

                try {
                    Statement statement = connection.createStatement();
                    String sqlStr = "select * from ths_data;";
                    ResultSet resultSet = statement.executeQuery(sqlStr);
                    if (resultSet == null) {
                        loadingDialog.dismiss();
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
                        mapList = getListData(thsDataList);
                        updateListView(mapList);
                    }

                    loadingDialog.dismiss();
                    //接收資料庫完成
                    Message message = new Message();
                    message.what = 1;
                    MainActivity.uiHandler.sendMessage(message);

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

        }).start();
    }
}


