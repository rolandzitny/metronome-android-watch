package com.example.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class ThemesActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private ImageView theme1;
    private ImageView theme2;
    private ImageView theme3;
    private ImageView theme6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_themes);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        theme1 = findViewById(R.id.theme1img);
        theme2 = findViewById(R.id.theme2img);
        //theme3 = findViewById(R.id.theme3img);
        theme6 = findViewById(R.id.theme6img);

        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("theme","theme1");
                editor.commit();
                MainActivity.fa.finish();
                Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_intent);
                SettingsActivity.fa.finish();
                finish();
            }
        });

        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("theme","theme2");
                editor.commit();
                MainActivity.fa.finish();
                Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_intent);
                SettingsActivity.fa.finish();
                finish();
            }
        });

//        theme3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editor.putString("theme","theme3");
//                editor.commit();
//                MainActivity.fa.finish();
//                Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(main_intent);
//                SettingsActivity.fa.finish();
//                finish();
//            }
//        });

        theme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("theme","theme6");
                editor.commit();
                MainActivity.fa.finish();
                Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_intent);
                SettingsActivity.fa.finish();
                finish();
            }
        });
    }
}