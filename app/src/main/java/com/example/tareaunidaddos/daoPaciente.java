package com.example.tareaunidaddos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPaciente {

    SQLiteDatabase bd;
    ArrayList<Paciente> lista = new ArrayList<Paciente>();
    Paciente c;
    Context ct;
    String nombreBD = "BDPacientes";

    String tabla = "create table if not exists Paciente(id integer primary key autoincrement, nombre text, razon text, email text, telefono text, horario text)";


    public daoPaciente(Context c){
        this.ct = c;
        bd = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Paciente c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", c.getNombre());
        contenedor.put("razon", c.getrazon());
        contenedor.put("email", c.getEmail());
        contenedor.put("telefono", c.getTelefono());
        contenedor.put("horario", c.gethorario());
        return (bd.insert("Paciente", null, contenedor))>0;

    }
    public boolean eliminar(int id){
        return (bd.delete("Paciente", "id =" + id, null)) > 0;
    }
    public boolean editar(Paciente c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", c.getNombre());
        contenedor.put("razon", c.getrazon());
        contenedor.put("email", c.getEmail());
        contenedor.put("telefono", c.getTelefono());
        contenedor.put("horario", c.gethorario());
        return (bd.update("Paciente", contenedor, "id=" + c.getId(), null)) > 0;
    }
    public ArrayList<Paciente>verTodo(){
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from Paciente", null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Paciente(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return  lista;
    }
    public Paciente verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from Paciente", null);
        cursor.moveToPosition(posicion);
        c = new Paciente(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5));
        return c;
    }
}
