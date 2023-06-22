package com.example.metronome;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ModesActivity extends Activity {

    private static final String BPM_MODES_FILE = "bpm_modes";
    private LinearLayout mode_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_modes);

        mode_buttons = findViewById(R.id.bpm_buttons);

        File file = new File(ModesActivity.this.getFilesDir(), BPM_MODES_FILE);
        File[] files = file.listFiles();
        for(int i=0; i < files.length; i++){
            File currentFile = files[i];
            StringBuilder mode_text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(files[i]));
                String mode_text_line;
                while ((mode_text_line = br.readLine()) != null){
                    mode_text.append(mode_text_line);
                }
                br.close();
            } catch (Exception e) {}

            String[] mode_values = mode_text.toString().split(" ", 2);

            LinearLayout new_hor_layout = new LinearLayout(this);
            Button new_mode_button = new Button(this);
            TextView new_text_view = new TextView(this);

            new_hor_layout.setOrientation(LinearLayout.HORIZONTAL);
            new_hor_layout.setGravity(Gravity.RIGHT);


            new_mode_button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_delete_forever_24, 0);
            new_mode_button.setBackgroundColor(Color.TRANSPARENT);
            new_mode_button.setText(mode_values[1] + " (" + mode_values[0] + " bpm)");
            new_mode_button.setTextSize(15);
            new_mode_button.setAllCaps(false);
            new_mode_button.setPadding(0,0,30,0);

            new_mode_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentFile.delete();
                    MainActivity.fa.finish();
                    Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main_intent);
                    SettingsActivity.fa.finish();
                    Intent mode_intent = new Intent(getApplicationContext(), ModesActivity.class);
                    startActivity(mode_intent);
                    finish();
                }
            });

//            new_text_view.setText(mode_values[1] + " (" + mode_values[0] + " bpm)");
//            new_text_view.setBackgroundColor(Color.TRANSPARENT);
//            new_text_view.setTextSize(15);
//            new_text_view.setAllCaps(false);

            new_hor_layout.addView(new_mode_button);
            //new_hor_layout.addView(new_text_view);
            mode_buttons.addView(new_hor_layout);
        }
    }
}