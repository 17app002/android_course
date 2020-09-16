package me.app17.thsviewer;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AlertDialog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.app17.thsviewer.util.JDBCUtil;


public class THSViewer implements AdapterView.OnItemClickListener {


    final class ThsData {
        int id;
        String date;
        String no;
        String price;
        String startLoc;
        String endLoc;
        String startTime;
        String endTime;
        String url;
        String status;
        int ticks;
    }

    //資料庫對應欄位名稱
    private String[] columns = {"no", "date", "start_loc", "end_loc", "start_time", "end_time", "price"};
    private ListView listView;
    private List<Map<String, Object>> mapList;
    private List<ThsData> thsDataList;
    //紀錄今天全部資料
    private List<ThsData> allThsDataList;


    private static AlertDialog loadingDialog;

    public THSViewer(View view) {
        listView = (ListView) view;
        listView.setOnItemClickListener(this);

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
                thsData.id = resultSet.getInt("id");
                thsData.date = resultSet.getString("date");
                thsData.no = resultSet.getString("no");
                thsData.startLoc = resultSet.getString("start_loc");
                thsData.endLoc = resultSet.getString("end_loc");
                thsData.startTime = resultSet.getString("start_time");
                thsData.endTime = resultSet.getString("end_time");
                thsData.price = resultSet.getString("price");
                thsData.url = resultSet.getString("url");
                thsData.status = resultSet.getString("status");
                thsData.ticks = resultSet.getInt("ticks");

                thsDataList.add(thsData);
                if (length != -1 && ++count >= length) {
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
            map.put("id", data.id);
            map.put("no", data.no);
            map.put("date", data.date);
            map.put("start_loc", data.startLoc);
            map.put("end_loc", data.endLoc);
            map.put("start_time", data.startTime);
            map.put("end_time", data.endTime);
            map.put("price", data.price);
            map.put("url", data.url);
            map.put("status", data.status);
            map.put("ticks", data.ticks);

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

        //接收資料庫完成
        Message message = new Message();
        message.what = 1;
        MainActivity.uiHandler.sendMessage(message);
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

                    allThsDataList = new ArrayList<>();
                    //儲存資料
                    thsDataList = getDbData(resultSet, -1);
                    allThsDataList.addAll(thsDataList);

                    //刷新ListView
                    if (thsDataList.size() > 0) {
                        mapList = getListData(thsDataList);
                        updateListView(mapList);
                    }

                    loadingDialog.dismiss();


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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //取得資料
        Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
        System.out.println(item.toString());
        MainActivity.showMessage(item.toString());
    }


    public void findData(String startLoc, String endLoc, String date) {

        List<ThsData> tempData = new ArrayList<>();

        if (thsDataList.size() == 0) {
            MainActivity.showMessage("請先取得資料!");
            return;
        }

        for (ThsData data : thsDataList) {
            if (data.startLoc.equals(startLoc) && data.endLoc.equals(endLoc) && data.date.equals(date)) {
                tempData.add(data);
            }
        }


        mapList = getListData(tempData);
        updateListView(mapList);
    }

    /***
     * 更新成全部資料
     */
    public void updateAllData() {
        mapList = getListData(allThsDataList);
        updateListView(mapList);
    }
}


