package me.app17.thsviewer;

import android.os.Message;
import android.util.Log;
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


class ThsData {

    String date;
    String no;
    int price;
    String startLoc;
    String endLoc;
    String startTime;
    String endTime;

}

public class THSViewer implements Runnable {
    public ArrayList<ThsData> thsDataList;

    @Override
    public void run() {
        MainActivity.showMessage("遠端主機連線中......");
        Log.i("info", "遠端主機連線中......");

        Connection connection = JDBCUtil.getConnection("db4free.net",
                "iiiplay001", "me516888", "ths_data");

        if (connection == null) {
            MainActivity.showMessage("連線失敗!");
            Log.i("info", "連線失敗!");
            return;
        }

        //MainActivity.showMessage("連線成功，取得資料中...");
        Log.i("info", "連線成功，取得資料中...");

        try {
            Statement statement = connection.createStatement();
            String sqlStr = "select * from ths_data;";
            ResultSet resultSet = statement.executeQuery(sqlStr);
//            String[] columns = {"date", "no", "start_loc", "start_time",
//                    "end_loc", "end_time", "discount", "ticks", "price", "url", "status"};

            if (resultSet == null) {
                Log.i("info", "目前無資料");
                return;
            }

            resultSet.last();
            MainActivity.showMessage("共" + resultSet.getRow() + "筆資料");
            Log.i("info", "共" + resultSet.getRow() + "筆資料");

            resultSet.beforeFirst();
            int count = 0;
            thsDataList = new ArrayList<>();
            //取得資料
            while (resultSet.next()) {
                ThsData thsData = new ThsData();
                thsData.date = resultSet.getString("date");
                thsData.no = resultSet.getString("no");
                thsData.startLoc = resultSet.getString("start_loc");
                thsData.endLoc = resultSet.getString("end_loc");
                thsDataList.add(thsData);
                if (++count >= 10) {
                    break;
                }
            }

            String[] columns = {"no", "date", "start_loc", "end_loc"};

            List<Map<String, Object>> mapList = new ArrayList<>();

            for (ThsData data : thsDataList) {
                Map<String, Object> map = new HashMap<>();
                map.put("no", data.no);
                map.put("date", data.date);
                map.put("start_loc", data.startLoc);
                map.put("end_loc", data.endLoc);
                mapList.add(map);
            }

            final SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.instance, mapList, R.layout.ths_listview_layout,
                    columns, new int[]{R.id.thsno_tv, R.id.date_tv, R.id.start_tv, R.id.end_tv});

            //回到主線程更新UI
            MainActivity.uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.instance.itemLv.setAdapter(simpleAdapter);
                }
            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
