package me.app17.mp3player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton playBtn;
    private ImageButton stopBtn;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.play_btn);
        stopBtn = findViewById(R.id.stop_btn);

        playBtn.setOnClickListener(new PlayMusic());

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.abc);
                mediaPlayer.setLooping(true);
            }
        });

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.abc);
            mediaPlayer.setLooping(true);
            playBtn.setImageResource(R.drawable.music_play);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class PlayMusic implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playBtn.setImageResource(R.drawable.music_play);
                return;
            }

            mediaPlayer.start();
            playBtn.setImageResource(R.drawable.music_pause);


        }
    }
}