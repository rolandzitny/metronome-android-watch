package com.example.metronome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SettingsActivity extends Activity {

    private Button themes_btn;
    private Button my_modes_btn;

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_settings);

        fa = this;

        themes_btn = findViewById(R.id.theme_btn);
        my_modes_btn = findViewById(R.id.my_modes_btn);

        themes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemesActivity.class);
                startActivity(intent);
            }
        });

        my_modes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModesActivity.class);
                startActivity(intent);
            }
        });
    }
}