package com.example.metronome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.metronome.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;
    private static final String BPM_MODES_FILE = "bpm_modes";
    private LinearLayout main_buttons;
    private Button settings_btn;
    private Button tap_tempo_btn;
    private Button play_btn;
    private Button presets_btn;

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fa = this;

        main_buttons = findViewById(R.id.main_buttons);
        settings_btn = findViewById(R.id.settings_btn);
        tap_tempo_btn = findViewById(R.id.taptempo_btn);
        play_btn = findViewById(R.id.play_btn);
        presets_btn = findViewById(R.id.presets_btn);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetTempoActivity.class);
                startActivity(intent);
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings_intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settings_intent);
            }
        });

        tap_tempo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tap_tempo_intent = new Intent(getApplicationContext(), TapTempoActivity.class);
                startActivity(tap_tempo_intent);
            }
        });

        presets_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent presets_intent = new Intent(getApplicationContext(), PresetsActivity.class);
                startActivity(presets_intent);
            }
        });

//        File file = new File(MainActivity.this.getFilesDir(), BPM_MODES_FILE);
//        File[] files = file.listFiles();
//        for(int i=0; i < files.length; i++){
//            Log.d("T", String.valueOf(files[i]));
//
//            StringBuilder mode_text = new StringBuilder();
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(files[i]));
//                String mode_text_line;
//                while ((mode_text_line = br.readLine()) != null){
//                    mode_text.append(mode_text_line);
//                }
//                br.close();
//            } catch (Exception e) {}
//
//            String[] mode_values = mode_text.toString().split(" ", 2);
//
//            Log.d("STRING", "---------------------");
//            Log.d("STRING", mode_values[0]);
//            Log.d("STRING", mode_values[1]);
//
//
//            Button new_mode_button = new Button(this);
//            new_mode_button.setText(mode_values[0] + " " + mode_values[1]);
//            new_mode_button.setBackgroundColor(Color.TRANSPARENT);
//            new_mode_button.setAllCaps(false);
//            new_mode_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent mode_intent = new Intent(getApplicationContext(), BpmActivity.class);
//                    mode_intent.putExtra("bpm", Integer.parseInt(mode_values[0]));
//                    startActivity(mode_intent);
//                }
//            });
//            main_buttons.addView(new_mode_button);
//        }
    }

//    public void checkModesDir(){
//        File file = new File(MainActivity.this.getFilesDir(), BPM_MODES_FILE);
//        if (!file.isDirectory()) {
//            file.mkdir();
//        }
//
//        try {
//            File base60file = new File(file, "60base.txt");
//            FileWriter writer = new FileWriter(base60file);
//            writer.append("60 Base");
//            writer.flush();
//            writer.close();
//        } catch (Exception e) { }
//
//        try {
//            File base90file = new File(file, "90base.txt");
//            FileWriter writer = new FileWriter(base90file);
//            writer.append("90 Base");
//            writer.flush();
//            writer.close();
//        } catch (Exception e) { }
//
//    }
}

