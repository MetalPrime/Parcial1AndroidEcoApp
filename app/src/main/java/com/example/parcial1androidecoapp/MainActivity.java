package com.example.parcial1androidecoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnMessageListener{

    private EditText name;
    private Button btnPass;
    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        btnPass = findViewById(R.id.btnPass);

        btnPass.setOnClickListener(this);

        tcp = TCPSingleton.getInstance();
        tcp.SetObserver(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnPass:

                Gson gson = new Gson();
                Name newName = new Name(name.getText().toString());
                String msg = gson.toJson(newName);
                tcp.sendMessages(msg);
                Intent i = new Intent(this,SecondActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void OnMessage(String line) {

    }
}