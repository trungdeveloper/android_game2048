package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button[][] buttons = new Button[4][4];
    int[][] bigArr = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout root = findViewById(R.id.root);

        for (int i = 0; i < 4; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            for (int j = 0; j < 4; j++) {
                Button button = new Button(this);
                buttons[i][j] = button;
                linearLayout.addView(button);
            }
            root.addView(linearLayout);
        }

        ControlActivity.addRandom(bigArr);
        ControlActivity.addRandom(bigArr);
        ControlActivity.setTextButton(bigArr, buttons);

        root.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                bigArr = ControlActivity.pushUp(bigArr);
                ControlActivity.setTextButton(bigArr, buttons);
            }

            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                ControlActivity.pushLeft(bigArr);
                ControlActivity.setTextButton(bigArr, buttons);
            }

            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
