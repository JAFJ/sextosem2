package com.example.myetesito.Basesita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myetesito.Basesita.tablitas.Cuentas;

import java.util.ArrayList;

public class BDCuentas extends BDHelper{

    Context context;
    String usuariobonito = "";
    String contrita = "";

    public BDCuentas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String cuenta, String usuario, String apellido1, String apellido2, String email, String telefono, String letra, String escena, String edad, String contra){

        long id = 0;

        try {
            BDHelper bdHelper = new BDHelper(context);
            SQLiteDatabase bd = bdHelper.getWritableDatabase();

            //para insertar en la bd
            ContentValues values = new ContentValues();
            values.put("cuenta", cuenta);
            values.put("usuario", usuario);
            values.put("apellido1", apellido1);
            values.put("apellido2", apellido2);
            values.put("email", email);
            values.put("telefono", telefono);
            values.put("letra", letra);
            values.put("escena", escena);
            values.put("edad", edad);
            values.put("contra", contra);

            id = bd.insert(TABLA_CUENTAS, null, values);
        } catch(Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<Cuentas> mostrarCuentas(){

        BDHelper bdHelper = new BDHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ArrayList<Cuentas> listaCuentas = new ArrayList<>();

        Cuentas cuentas = null;
        Cursor cursorCuentas = null;

        cursorCuentas = bd.rawQuery("SELECT * FROM " + TABLA_CUENTAS, null);

        if(cursorCuentas.moveToFirst()){
            do{
                cuentas = new Cuentas();
                cuentas.setUsuario(cursorCuentas.getString(2));
                cuentas.setContra(cursorCuentas.getString(10));
                listaCuentas.add(cuentas);
            } while (cursorCuentas.moveToNext());
        }
        cursorCuentas.close();

        return listaCuentas;
    }
    public Boolean checkUsuarioPassw(String usuario, String contra){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM t_usuarios WHERE  usuario = ? and contra = ?", new String[]{usuario, contra});

        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }


}
