package com.example.tareaunidaddos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    daoPaciente dao;
    AdaptadorPaciente adapter;
    ArrayList<Paciente> lista;
    Paciente c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dao= new daoPaciente(Menu.this);
        lista=dao.verTodo();
        adapter= new AdaptadorPaciente(lista, dao, this);
        ListView list = findViewById(R.id.lista);
        Button insertar = findViewById(R.id.btn_insertar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Menu.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText razon = dialog.findViewById(R.id.et_razon);
                final EditText email = dialog.findViewById(R.id.et_email);
                final EditText telefono = dialog.findViewById(R.id.et_telefono);
                final EditText horario = dialog.findViewById(R.id.et_horario);

                Button guardar = dialog.findViewById(R.id.btn_agregar);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c = new Paciente(nombre.getText().toString(),
                                    razon.getText().toString(),
                                    email.getText().toString(),
                                    telefono.getText().toString(),
                                    horario.getText().toString());
                            dao.insertar(c);
                            lista=dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e){
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
}