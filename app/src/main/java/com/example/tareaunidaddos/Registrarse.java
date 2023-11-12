package com.example.tareaunidaddos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {

    private EditText et1, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        et1 = (EditText) findViewById(R.id.et_usuario1);
        pass1 = (EditText) findViewById(R.id.et_password);
        pass2 = (EditText) findViewById(R.id.et_pasVerificar);
    }

    public void Enviar(View v){
        String nombre = et1.getText().toString();
        String password = pass1.getText().toString();
        String passVeri = pass2.getText().toString();
        if (nombre.length() == 0 || password.length() == 0){
            Toast.makeText(this, "INGRESA UN NOMBRE Y CONTRASEÑA", Toast.LENGTH_SHORT).show();
        } else if (password.equals(passVeri)) {
            Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "LAS CONTRASEÑAS DEBEN SER IGUALES", Toast.LENGTH_SHORT).show();
        }
    }
}