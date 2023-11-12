package com.example.tareaunidaddos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class IniciarSesion extends AppCompatActivity {

    private EditText et1, et2;
    private Button btn;
    private ProgressBar pg1;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        btn = findViewById(R.id.btn_iniciar);
        et1 =(EditText)findViewById(R.id.et_usuarioinicio);
        et2 =(EditText)findViewById(R.id.et_passwordinicio);
        pg1 = findViewById(R.id.progressBar);
    }

    public void Enviar(View v){
        String nombre = et1.getText().toString();
        String password = et2.getText().toString();
        if (nombre.length() == 0 || password.length() == 0){
            Toast.makeText(this, "INGRESA UN NOMBRE Y CONTRASEÑA", Toast.LENGTH_SHORT).show();
        }
        if (nombre.length() != 0 || password.length() == 0){
            pg1.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    counter++;
                    pg1.setProgress(counter);
                    if (counter == 50){
                        timer.cancel();
                        Intent i = new Intent(IniciarSesion.this, Menu.class);
                        i.putExtra("dato", et1.getText().toString()); startActivity(i);
                    }
                }
            };
            timer.schedule(timerTask, 50, 50);
            Toast.makeText(this, "INICIO SESION EXITOSO", Toast.LENGTH_SHORT).show();
            }
    }
}