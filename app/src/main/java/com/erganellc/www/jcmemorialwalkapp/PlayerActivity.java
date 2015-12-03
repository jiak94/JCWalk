package com.erganellc.www.jcmemorialwalkapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jiakuan
 * on 11/30/2015.
 */
public class PlayerActivity extends AppCompatActivity {
    private boolean complete = true;
    private ImageButton play_pause;
    private SeekBar seekbar;
    private boolean ifPlay = false;
    private MediaPlayer player = null;
    private boolean ifFirst = false;
    private String audioName = "";
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isChanging = false;
    private TextView audio;
    private TextView desc;
    private Chronometer et_time;
    private long falgTime = 0,beginTime = 0, pauseTime = 0, subTime = 0;
    private String pointName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Intent intent = getIntent();
        this.pointName = intent.getStringExtra("pointName");

        player = new MediaPlayer();

        this.audioName = setAudioName(this.pointName);

        findViews(this.pointName);
    }

    private void findViews(final String pointName) {
        audio = (TextView) findViewById(R.id.audio);
        audio.setText(this.audioName);

        desc = (TextView)findViewById(R.id.desc);
        desc.setText(Html.fromHtml(readTextFile(this, R.raw.introdesc)));

        et_time = (Chronometer)findViewById(R.id.et_time);

        play_pause = (ImageButton) findViewById(R.id.playButton);
        play_pause.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                if (player != null && !ifPlay) {

                    if (!ifFirst) {
                        player.reset();
                        player = setMediaPlayer(pointName);
                        seekbar.setMax(player.getDuration());

                        mTimer = new Timer();
                        mTimerTask = new TimerTask() {
                            @Override
                            public void run() {
                                if (isChanging == true) {
                                    return;
                                }
                                seekbar.setProgress(player.getCurrentPosition());
                            }
                        };
                        mTimer.schedule(mTimerTask, 0, 10);
                        ifFirst = true;
                    }
                    play_pause.setImageResource(R.drawable.pause_50);
                    if (!complete) {
                        subTime += SystemClock.elapsedRealtime() - pauseTime;
                        beginTime = falgTime + subTime;
                        et_time.setBase(beginTime);
                        et_time.start();
                    } else {
                        falgTime = SystemClock.elapsedRealtime();
                        pauseTime = 0;
                        et_time.setBase(falgTime);
                        et_time.start();
                    }

                    player.start();
                    ifPlay = true;
                    complete = false;
                } else if (ifPlay) {
                    play_pause.setImageResource(R.drawable.play_50);
                    player.pause();
                    ifPlay = false;
                    et_time.stop();
                    pauseTime = SystemClock.elapsedRealtime();
                }
            }
        });

        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    player.seekTo(progress);
                    falgTime = SystemClock.elapsedRealtime() - seekbar.getProgress();
                    et_time.setBase(falgTime);
                    subTime = 0;
                    et_time.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChanging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
                isChanging = false;
            }
        });
        et_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });
    }

    private MediaPlayer setMediaPlayer(String pointName) {
        MediaPlayer player = new MediaPlayer();
        switch (pointName) {
            case "afghan":
                try {
                    player = MediaPlayer.create(this, R.raw.afghanistan);

                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "gulf":
                try {
                    player = MediaPlayer.create(this, R.raw.gulfwar);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "iraq":
                try {
                    player = MediaPlayer.create(this, R.raw.iraqwar);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "korea":
                try {
                    player = MediaPlayer.create(this, R.raw.koreanwar);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "pow":
                try {
                    player = MediaPlayer.create(this, R.raw.powmia);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "purple":
                try {
                    player = MediaPlayer.create(this, R.raw.purpleheart);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "start":
                try {
                    player = MediaPlayer.create(this, R.raw.intro);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "vietnam":
                try {
                    player = MediaPlayer.create(this, R.raw.vietnam);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "women":
                try {
                    player = MediaPlayer.create(this, R.raw.women);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "wwi":
                try {
                    player = MediaPlayer.create(this, R.raw.ww1);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "wwii":
                try {
                    player = MediaPlayer.create(this, R.raw.ww2);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                try {
                    player = MediaPlayer.create(this, R.raw.intro);
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);
                falgTime = 0;
                subTime = 0;
                et_time.setBase(SystemClock.elapsedRealtime());
                complete = true;
                ifPlay = false;
                play_pause.setImageResource(R.drawable.play_50);
                seekbar.setProgress(0);
                et_time.start();
                et_time.stop();
            }
        });
        return player;
    }

    protected void onDestroy() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
        }
        super.onDestroy();
        if (player != null) {
            if (player.isPlaying()) {

                player.stop();
            }
            player.release();
        }
    }

    protected void onPause() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
            }
        }
        super.onPause();
    }

    protected void onResume() {
        if (player != null) {
            if (!player.isPlaying()) {
                player.start();
            }
        }
        super.onResume();
    }

    private String ShowTime(int time) {
        time /= 100;
        int min = time/60;
        int h = min/60;
        int sec = time % 60;
        return String.format("%02d:%02d", min, sec);
    }

    private String setAudioName(String pointName) {
        String audioName;
        switch (pointName) {
            case "afghan":
                audioName = "Afghanistan";
                break;

            case "gulf":
                audioName = "Gulf War";
                break;

            case "iraq":
                audioName = "Iraq War";
                break;

            case "korea":
                audioName = "Korean War";
                break;

            case "pow":
                audioName = "POW MIA";
                break;

            case "purple":
                audioName = "Purple Heart";
                break;

            case "start":
                audioName = "Intro";
                break;

            case "vietnam":
                audioName = "Vietnam";
                break;

            case "women":
                audioName = "Women in Service";
                break;

            case "wwi":
                audioName = "WWI";
                break;

            case "wwii":
                audioName = "WWII";
                break;

            default:
               audioName = "Intro";
        }
        return audioName;
    }

    public static String readTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            while (( line = bufferedreader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return stringBuilder.toString();
    }
}
