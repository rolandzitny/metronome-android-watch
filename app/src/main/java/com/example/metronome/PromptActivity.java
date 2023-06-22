package com.example.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;

public class PromptActivity extends Activity {
    public static Activity fa;
    private Button savebtn;
    private Button canclebtn;

    private int bpm;
    private TextView bpm_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_prompt);

        fa = this;

        savebtn = findViewById(R.id.savebtn);
        canclebtn = findViewById(R.id.canclebtn);
        bpm_text = findViewById(R.id.bpm_textview);

        Bundle input_bundle = getIntent().getExtras();
        bpm = input_bundle.getInt("bpm");

        String bpm_string = String.valueOf(bpm);
        bpm_text.setText(bpm_string);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_mode(bpm);

                MainActivity.fa.finish();
                Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_intent);

                Intent mode_intent = new Intent(getApplicationContext(), BpmActivity.class);
                String val = String.valueOf(bpm);
                mode_intent.putExtra("bpm", Integer.parseInt(val));
                startActivity(mode_intent);
                finish();
            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void save_mode(int bpm){
        File file = new File(PromptActivity.this.getFilesDir(), "bpm_modes");
        try {
            File new_mode_file = new File(file, String.valueOf(bpm) + "mode.txt");
            FileWriter writer = new FileWriter(new_mode_file);
            writer.append(String.valueOf(bpm) + " new");
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }
}