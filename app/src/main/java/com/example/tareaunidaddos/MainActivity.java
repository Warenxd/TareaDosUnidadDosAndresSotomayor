package com.example.tareaunidaddos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IniciarSesion(View view){
        Intent i = new Intent(this, IniciarSesion.class);
        startActivity(i);
    }
    public void Registrarse(View view){
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);
    }
    public void Ubicación(View view){
        Intent i = new Intent(this, Ubicacion.class);
        startActivity(i);
    }
}