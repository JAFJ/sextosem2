package com.example.myetesito.Basesita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
//se uso el import de androidxx
public class BDcuenta extends BDweb{

    Context context;

    public BDcuenta(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public boolean comprobarCuenta (int idUsr, int idCut){
        BDweb bdweb = new BDweb(context);
        SQLiteDatabase bd = bdweb.getWritableDatabase();

        boolean cuentita;
        Cursor cursorcuentita;

        cursorcuentita = bd.rawQuery("SELECT * FROM " + TABLE_CUENTA + " WHERE idUsr =" + idUsr +" and idCut=" + idCut + " LIMIT 1", null);

        if (cursorcuentita.moveToFirst()){
            cuentita = true;
        }else{
            cuentita = false;
        }

        cursorcuentita.close();
        return cuentita;
    }

    public long insertarCuenta(int idUsr, int idCut, String textoCC) {
        long status = 0;

        try{
            BDweb bdweb = new BDweb (context);
            SQLiteDatabase bd = bdweb.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("idUsr", idUsr);
            values.put("idCut", idCut);
            values.put("textoCC", textoCC);

            status = bd.insert(TABLE_CUENTA, null, values);
        } catch (Exception e){
            e.toString();
        }

        return status;
    }
    public boolean EditarCuenta (int idUsr, int idCut, String textoCC){
        boolean correcto = false;

        BDweb bdweb = new BDweb(context);
        SQLiteDatabase bd = bdweb.getWritableDatabase();

        //linea 86
        try {
            bd.execSQL("UPDATE " + TABLE_CUENTA + "SET textoCC= '" + textoCC + "' WHERE idUsr=" + idUsr + " and idCut=" + idCut + " LIMIT 1", null);
            correcto = true;
        } catch (Exception e){
            e.toString();
            correcto = false;
        } finally {
            bd.close();
        }
        return correcto;

    }

}
