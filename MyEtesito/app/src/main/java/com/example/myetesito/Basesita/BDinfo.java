package com.example.myetesito.Basesita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
//igual tiene el import de androidxx
public class BDinfo extends BDweb{

    Context context;
    public BDinfo(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public boolean comprobarInfo (int id){
        BDweb bdweb = new BDweb(context);
        SQLiteDatabase bd = bdweb.getWritableDatabase();

        boolean Info;
        Cursor cursorinfo;
        cursorinfo = bd.rawQuery("SELECT * FROM "+ TABLE_INFO + " WHERE id " + id + " LIMIT 1", null);

    if (cursorinfo.moveToFirst()){
        Info = true;
    }else{
        Info= false;
    }
    cursorinfo.close();
    return Info;
        }

    public String verInfo (int id){
        BDweb bdweb = new BDweb(context);
        SQLiteDatabase bd = bdweb.getWritableDatabase();

        String Info = null;
        Cursor infocursor;

        infocursor = bd.rawQuery("SELECT * FROM " + TABLE_INFO + " WHERE id=" + id +" LIMIT 1", null);

        if (infocursor.moveToFirst()){
            Info = infocursor.getString(1);
        }
        infocursor.close();
        return Info;
    }
    public long insertarInfo(int id, String textoC){

        long status = 0;

        try {
            BDweb bdweb = new BDweb(context);
            SQLiteDatabase bd = bdweb.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("textoC", textoC);

            status = bd.insert(TABLE_INFO, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return status;

    }
    public boolean editarInfo (int id, String textoC) {
        boolean correcto = false;

        BDweb bdweb = new BDweb(context);
        SQLiteDatabase bd = bdweb.getWritableDatabase();

        try {
            bd.execSQL("UPDATE " + TABLE_INFO + "SET textoC = '" + textoC +"' WHERE id= " + id + "");
            correcto = true;
        } catch (Exception e){
            e.toString();
        } finally {
            bd.close();
        }
        return correcto;
    }
}
