package com.example.myetesito.Basesita;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//ES EL IMPORT DE ANDROIX, NO EL DE JETBRAINS, SI HAY ERROR CAMBIAR

public class BDweb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "myweb.db";
    public static final String TABLE_INFO = "t_info";
    public static final String TABLE_CUENTA = "t_cuenta";

    public BDweb (@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_INFO + "(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "textoC TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CUENTA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUsr INTEGER NOT NULL," +
                "idCut INTEGER NOT NULL," +
                "textoCC TEXT NOT NULL)");
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i , int ii){

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_INFO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CUENTA);
        onCreate(sqLiteDatabase);
    }



}
