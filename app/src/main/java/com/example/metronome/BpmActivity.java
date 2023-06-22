package com.example.metronome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences; // to remember selected theme
import android.content.Context;

public class BpmActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Vibrator vibrator;
    private VibrationEffect vibe;
    private int bpm;
    private int beats_ms;
    private String selected_theme;
    private int current_beat;
    private boolean isPlaying;

    private TextView bpm_text;

    public static int getResId(String resName, Class<?> c) {
        // helper function to get id from string
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void start_bpm(){
        beats_per_minute.run();
    }

    public void stop_bpm(){
        mHandler.removeCallbacks(beats_per_minute);
    }

    private Runnable beats_per_minute = new Runnable() {
        @Override
        public void run() {
            Log.d("VIB", "Vibrate");
            Log.d("Current beat", Integer.toString(current_beat));
            vibrator.vibrate(vibe);
            mHandler.postDelayed(this, beats_ms);


            if (selected_theme.equals("theme1")) {
                current_beat = (current_beat + 1) % 4;
                // Selected theme 1 - change active rectangles
                switch (current_beat) {
                    case 0: // set inactive rect4, set active rect1
                        findViewById(R.id.beat4Rect).setBackgroundResource(R.color.theme1_rectangle_inactive);
                        findViewById(R.id.beat1Rect).setBackgroundResource(R.color.theme1_rectangle_active);
                        break;
                    case 1: // set inactive rect1, set active rect2
                        findViewById(R.id.beat1Rect).setBackgroundResource(R.color.theme1_rectangle_inactive);
                        findViewById(R.id.beat2Rect).setBackgroundResource(R.color.theme1_rectangle_active);
                        break;
                    case 2: // set inactive rect2, set active rect3
                        findViewById(R.id.beat2Rect).setBackgroundResource(R.color.theme1_rectangle_inactive);
                        findViewById(R.id.beat3Rect).setBackgroundResource(R.color.theme1_rectangle_active);
                        break;
                    case 3: // set inactive rect3, set active rect4
                        findViewById(R.id.beat3Rect).setBackgroundResource(R.color.theme1_rectangle_inactive);
                        findViewById(R.id.beat4Rect).setBackgroundResource(R.color.theme1_rectangle_active);
                        break;
                }
            } // end theme1
            else if (selected_theme.equals("theme2")) {
                current_beat = (current_beat + 1) % 8;
                switch (current_beat) { // REALLY SORRY about this, didn't find better way
                    case 0:
                        findViewById(R.id.theme2_circle8).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle1).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 1:
                        findViewById(R.id.theme2_circle1).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle2).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 2:
                        findViewById(R.id.theme2_circle2).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle3).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 3:
                        findViewById(R.id.theme2_circle3).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle4).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 4:
                        findViewById(R.id.theme2_circle4).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle5).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 5:
                        findViewById(R.id.theme2_circle5).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle6).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 6:
                        findViewById(R.id.theme2_circle6).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle7).setBackgroundResource(R.drawable.circle_green);
                        break;
                    case 7:
                        findViewById(R.id.theme2_circle7).setBackgroundResource(R.drawable.circle_black);
                        findViewById(R.id.theme2_circle8).setBackgroundResource(R.drawable.circle_green);
                        break;
                }
            } // end theme2

        }
    };

    @Override
    protected void onDestroy() {
        Log.d("VIB","Destroy - stop vibrations");
        stop_bpm();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        selected_theme = sharedpreferences.getString("theme", "theme1");
        Log.d("THEME", selected_theme);
        current_beat = 0;
        isPlaying = true;

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (selected_theme.equals("theme1")) {
            // Selected theme 1
            setContentView(R.layout.activity_theme1);
            bpm_text = findViewById(R.id.bpm_text);
            findViewById(R.id.beat1Rect).setBackgroundResource(R.color.theme1_rectangle_active);

            ConstraintLayout clayout = (ConstraintLayout) findViewById(R.id.theme1_main_layout);
            clayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPlaying) {
                        findViewById(R.id.theme1_triangle).setVisibility(View.GONE);
                        findViewById(R.id.theme1_bkg).setVisibility(View.GONE);
                        start_bpm();
                    } else {
                        findViewById(R.id.theme1_triangle).setVisibility(View.VISIBLE);
                        findViewById(R.id.theme1_bkg).setVisibility(View.VISIBLE);
                        stop_bpm();
                    }
                    isPlaying = !isPlaying;
                }
            });
        }
        else if (selected_theme.equals("theme2")) {
            // Selected theme 2
            setContentView(R.layout.activity_theme2);
            bpm_text = findViewById(R.id.bpm_theme2_text);
            // set active first circle
            findViewById(R.id.theme2_circle1).setBackgroundResource(R.drawable.circle_green);

            RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.theme2_main_layout);
            rlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPlaying) {
                        findViewById(R.id.theme2_triangle).setVisibility(View.GONE);
                        findViewById(R.id.theme2_bkg).setVisibility(View.GONE);
                        start_bpm();
                    } else {
                        findViewById(R.id.theme2_triangle).setVisibility(View.VISIBLE);
                        findViewById(R.id.theme2_bkg).setVisibility(View.VISIBLE);
                        stop_bpm();
                    }
                    isPlaying = !isPlaying;
                }
            });

        }
        else if (selected_theme.equals("theme6")) {
            // Selected theme 2
            setContentView(R.layout.activity_theme6);
            bpm_text = findViewById(R.id.bpm_theme6_text);
            // set active first circle
            //findViewById(R.id.theme2_circle1).setBackgroundResource(R.drawable.circle_green);

            ConstraintLayout clayout  = (ConstraintLayout) findViewById(R.id.theme6_main_layout);
            clayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPlaying) {
                        findViewById(R.id.theme6_triangle).setVisibility(View.GONE);
                        findViewById(R.id.theme6_bkg).setVisibility(View.GONE);
                        start_bpm();
                    } else {
                        findViewById(R.id.theme6_triangle).setVisibility(View.VISIBLE);
                        findViewById(R.id.theme6_bkg).setVisibility(View.VISIBLE);
                        stop_bpm();
                    }
                    isPlaying = !isPlaying;
                }
            });

        }


        else {
            setContentView(R.layout.activity_bpm);
            bpm_text = findViewById(R.id.bpm_text);
        }


        Bundle input_bundle = getIntent().getExtras();
        bpm = input_bundle.getInt("bpm");

        String bpm_string = String.valueOf(bpm);
        bpm_text.setText(bpm_string);

        beats_ms = Math.round(60000/bpm);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibe = VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE);

        start_bpm();
    }
}