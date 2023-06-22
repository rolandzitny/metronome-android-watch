package com.example.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import android.widget.NumberPicker;
import android.util.Log;

public class SetTempoActivity extends Activity {
    private NumberPicker picker1;
    private Button btn;
    private View myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_set_tempo);

        picker1 = findViewById(R.id.number_picker);
        picker1.setMaxValue(180);
        picker1.setMinValue(10);
        picker1.setValue(80); // set value somewhere in the middle

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
            //save_mode(bpm); // Ak pouzivame set tempo iba na nastavenie rychlosti hrania, nema ho zmysel ukladat
            MainActivity.fa.finish();
            Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main_intent);
            //SettingsActivity.fa.finish();
            Intent mode_intent = new Intent(getApplicationContext(), BpmActivity.class);
            mode_intent.putExtra("bpm", picker1.getValue());
            startActivity(mode_intent);
            finish();
        }
    });

}

    public void save_mode(int bpm){
        File file = new File(SetTempoActivity.this.getFilesDir(), "bpm_modes");
        try {
            File new_mode_file = new File(file, String.valueOf(bpm) + "mode.txt");
            FileWriter writer = new FileWriter(new_mode_file);
            writer.append(String.valueOf(bpm) + " new");
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }
}