package com.example.parcial1androidecoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,OnMessageListener {

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnColor;
    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnColor = findViewById(R.id.btnColor);

        tcp = TCPSingleton.getInstance();
        tcp.SetObserver(this);

        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnColor.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnUp:
                break;
            case R.id.btnDown:
                break;
            case R.id.btnLeft:
                break;
            case R.id.btnRight:
                break;
            case R.id.btnColor:
                break;
        }
    }

    //Llegan los mensajes

    @Override
    public void OnMessage(String line) {

    }
}