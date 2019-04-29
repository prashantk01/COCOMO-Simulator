package com.example.cocomo_ii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Launcher extends AppCompatActivity {
    Button button_fp;
    Button button_kloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Toast.makeText(Launcher.this, "Created by:-     PRASHANT   KUMAR             B.TECH(II Y)     FAC NO :  17COB020", Toast.LENGTH_LONG).show();
        TextView txt = findViewById(R.id.text_movable);
        txt.setSelected(true);
        button_fp = findViewById(R.id.button_fp);
        button_kloc = findViewById(R.id.button_kloc);
        button_fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, FP_ACTIVITY.class));
            }
        });
        button_kloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, MainActivity.class));
            }
        });
    }
}
