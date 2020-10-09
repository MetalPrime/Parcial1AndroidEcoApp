package com.example.parcial1androidecoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,OnMessageListener {

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private Button btnColor;
    private TCPSingleton tcp;
    private Coordinate coordinate;
    private Gson gson;
    private String line;
    private boolean buttonPressed;


    @SuppressLint("ClickableViewAccessibility")
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
        buttonPressed = false;

        btnUp.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        coordinate.setPosY(coordinate.getPosY()-3);
                                                        line =  gson.toJson(coordinate);
                                                        tcp.sendMessages(line);
                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnDown.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        coordinate.setPosY(coordinate.getPosY()+3);
                                                        line =  gson.toJson(coordinate);
                                                        tcp.sendMessages(line);
                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnRight.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        coordinate.setPosX(coordinate.getPosX()+3);
                                                        line =  gson.toJson(coordinate);
                                                        tcp.sendMessages(line);
                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnLeft.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        coordinate.setPosX(coordinate.getPosX()-3);
                                                        line =  gson.toJson(coordinate);
                                                        tcp.sendMessages(line);
                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnColor.setOnClickListener(this);

        gson = new Gson();
        coordinate = new Coordinate(250,250);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnColor:
                Random r1,r2,r3;
                r1 = new Random();
                r2 = new Random();
                r3 = new Random();
                int n1,n2,n3;
                n1 = r1.nextInt(255);
                n2 = r2.nextInt(255);
                n3 = r3.nextInt(255);


                Color newColor = new Color(n1,n2,n3);
                line =  gson.toJson(newColor);
                tcp.sendMessages(line);
                break;
        }
    }

    //Llegan los mensajes

    @Override
    public void OnMessage(String line) {

    }
}