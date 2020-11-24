package me.app17.mp3player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private ListView mp3List;
    private final String[] mp3Name = {"體面", "散了就散了", "我們不一樣","與我無關","刻在我心底的名字","地球上最浪漫的一首歌",
            "太陽","想見你想見你想見你","天黑請閉眼","怎麼了","Without You","再見煙火"};

    private MediaPlayer mediaPlayer;
    private Button pauseBtn;
    private boolean pause;
    private boolean isComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setData();

    }

    public void findViews() {
        mp3List = findViewById(R.id.mp3_list);
        pauseBtn = findViewById(R.id.pause_btn);

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (mediaPlayer == null) {
                    return;
                }

                //已經撥放完畢(重新撥放)
                if (isComplete) {
                    isComplete = false;
                    pause = false;
                    pauseBtn.setText(R.string.pause);
                    mediaPlayer.start();
                    return;
                }

                //暫停跟恢復
                pause = !pause;

                if (!pause) {
                    pauseBtn.setText(R.string.pause);
                } else {
                    pauseBtn.setText(R.string.resume);
                }

                if (pause && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    return;
                }

                mediaPlayer.start();
            }
        });

    }

    public void setData() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mp3Name);
        mp3List.setAdapter(adapter);

        mp3List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=3){
                    Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                    return;
                }

                playMusic(i);
                Toast.makeText(MainActivity.this, mp3Name[i], Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void playMusic(int index) {
        int[] resId = {R.raw.mp3_0, R.raw.mp3_1, R.raw.mp3_2};

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, resId[index]);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

        pause = false;
        isComplete = false;
        pauseBtn.setText(R.string.pause);
        this.setTitle(getResources().getString(R.string.app_name) + "-" + mp3Name[index]);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isComplete = true;
                pauseBtn.setText(R.string.play);
                Toast.makeText(MainActivity.this, R.string.complete, Toast.LENGTH_SHORT).show();
            }
        });
    }
}