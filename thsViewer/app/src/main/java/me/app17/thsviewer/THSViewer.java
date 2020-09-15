package me.app17.thsviewer;

import android.util.Log;

import me.app17.thsviewer.util.JDBCUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public static ArrayList<ThsData> thsDataList;
    public static int status;

    @Override
    public void run() {
        status = 0;
        Log.i("info", "遠端主機連線中......");
        Connection connection = JDBCUtil.getConnection("db4free.net",
                "iiiplay001", "me516888", "ths_data");

        if (connection == null) {
            status = -1;
            Log.i("info", "連線失敗!");
            return;
        }

        Log.i("info", "連線成功，取得資料中...");

        try {
            Statement statement = connection.createStatement();
            String sqlStr = "select * from ths_data;";
            ResultSet resultSet = statement.executeQuery(sqlStr);
            String[] columns = {"date", "no", "start_loc", "start_time",
                    "end_loc", "end_time", "discount", "ticks", "price", "url", "status"};

            if (resultSet == null) {
                Log.i("info", "目前無資料");
                return;
            }

            resultSet.last();
            System.out.println("共" + resultSet.getRow() + "筆資料");
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
            status = 1;
            //System.out.println(thsDataList);

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
