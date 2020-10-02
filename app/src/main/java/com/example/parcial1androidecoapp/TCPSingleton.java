package com.example.parcial1androidecoapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class TCPSingleton extends Thread{

    public static TCPSingleton instanceUnique;

    public static TCPSingleton getInstance(){
        if(instanceUnique==null){
            instanceUnique = new TCPSingleton();
        }
        return instanceUnique;
    }

    private TCPSingleton(){

    }

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    public void run() {
        try {
            socket = new Socket("",5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
