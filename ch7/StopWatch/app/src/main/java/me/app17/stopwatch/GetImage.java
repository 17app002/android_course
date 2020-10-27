package me.app17.stopwatch;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

public class GetImage extends AsyncTask<String, Integer, Bitmap>{

    private ProgressDialog progressBar;
    //進度條元件

    private Context context;

    public GetImage(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        //執行前 設定可以在這邊設定
        super.onPreExecute();

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();
        //初始化進度條並設定樣式及顯示的資訊。
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        //執行中 在背景做事情
        int progress = 0;
        for (String urlStr : params) {
            try {
                URL url = new URL(urlStr);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            publishProgress(progress += 33);
            //有三張圖 每張圖33%
        }
        publishProgress(100);
        //最後達到100%
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //執行中 可以在這邊告知使用者進度
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        //取得更新的進度
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //執行後 完成背景任務
        super.onPostExecute(bitmap);

        progressBar.dismiss();


        //當完成的時候，把進度條消失
        //imageView.setImageBitmap(bitmap);
    }
}