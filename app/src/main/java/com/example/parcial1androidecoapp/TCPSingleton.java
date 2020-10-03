package com.example.parcial1androidecoapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPSingleton extends Thread{

    public static TCPSingleton instanceUnique;

    public static TCPSingleton getInstance(){
        if(instanceUnique==null){
            instanceUnique = new TCPSingleton();
            getInstance().start();
        }
        return instanceUnique;
    }

    private TCPSingleton(){

    }



    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private OnMessageListener observer;

    public void SetObserver(OnMessageListener observer){
        this.observer = observer;
    }
    @Override
    public void run() {
        try {
            Log.e("Conexión","Esperando Conexión");
            socket = new Socket("192.168.0.4",5000);

            Log.e("Conexión","Conexión Establecido");
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writer = new BufferedWriter(osw);

            while (true){
                String line = reader.readLine();

                observer.OnMessage(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(String msg){
        new Thread(
                () ->{
                    try {
                            writer.write(msg);
                            writer.flush();
                            Log.e("Mensaje", msg);
                    } catch (IOException e){
                        e.printStackTrace();

                    }
                }
        ).start();


    }
}
