package com.example.metronome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.util.Timer;
import java.util.TimerTask;

public class TapTempoActivity extends Activity {

    private Timer timer;
    private Timer timer_wait;
    private ImageButton taptemposet_btn;
    private int bpm_setter;
    private TextView bpm_text;
    final Context context = this;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_tap_tempo);

        bpm_setter = 0;
        taptemposet_btn = findViewById(R.id.taptemposet_btn);
        bpm_text = findViewById(R.id.taptempo_text);

        taptemposet_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(bpm_setter == 0 ){
                    timer = new Timer();
                    timer_wait = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            bpm_setter = bpm_setter * 6;
                            String val = String.valueOf(bpm_setter);
                            //bpm_text.setText(val);

                            timer_wait.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Intent prompt_activity = new Intent(getApplicationContext(), PromptActivity.class);
                                    prompt_activity.putExtra("bpm", bpm_setter);
                                    startActivity(prompt_activity);;
                                    finish();
                                }
                            }, 250);
                        }
                    }, 5000);
                }

                bpm_setter += 1;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    float x = (float) 1.25;
                    float y = (float) 1.25;

                    taptemposet_btn.setScaleX(x);
                    taptemposet_btn.setScaleY(y);

                    return true;
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    taptemposet_btn.setScaleX(x);
                    taptemposet_btn.setScaleY(y);

                }
                return false;
            }
        });
    }

    public void save_mode(int bpm){
        File file = new File(TapTempoActivity.this.getFilesDir(), "bpm_modes");
        try {
            File new_mode_file = new File(file, String.valueOf(bpm) + "mode.txt");
            FileWriter writer = new FileWriter(new_mode_file);
            writer.append(String.valueOf(bpm) + " new");
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }
}