package com.example.tareaunidaddos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorPaciente extends BaseAdapter {

    ArrayList<Paciente> lista;
    daoPaciente dao;
    Paciente c;
    Activity a;
    int id = 0;

    public AdaptadorPaciente(ArrayList<Paciente> lista, daoPaciente dao, Activity a) {
        this.lista = lista;
        this.dao = dao;
        this.a = a;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        c= lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = lista.get(i);
        return c.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        c = lista.get(posicion);

        TextView nombre = v.findViewById(R.id.nombre);
        TextView razon = v.findViewById(R.id.razon);
        TextView email = v.findViewById(R.id.email);
        TextView telefono = v.findViewById(R.id.telefono);
        TextView horario = v.findViewById(R.id.horario);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);

        nombre.setText(c.getNombre());
        razon.setText(c.getrazon());
        email.setText(c.getEmail());
        telefono.setText(c.getTelefono());
        horario.setText(c.gethorario());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText razon = dialog.findViewById(R.id.et_razon);
                final EditText email = dialog.findViewById(R.id.et_email);
                final EditText telefono = dialog.findViewById(R.id.et_telefono);
                final EditText horario = dialog.findViewById(R.id.et_horario);

                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);

                c = lista.get(pos);
                setId(c.getId());
                nombre.setText(c.getNombre());
                razon.setText(c.getrazon());
                email.setText(c.getEmail());
                telefono.setText(c.getTelefono());
                horario.setText(c.gethorario());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            c= new Paciente(getId(), nombre.getText().toString(),
                                    razon.getText().toString(),
                                    email.getText().toString(),
                                    telefono.getText().toString(),
                                    horario.getText().toString());
                            dao.editar(c);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= Integer.parseInt(view.getTag().toString());
                c= lista.get(posicion);
                setId(c.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("¿Estás seguro de que deseas eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista= dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                del.show();
            }
        });
        return v;
    }
}
