package com.example.myetesito.Basesita;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Basesita";
    public static final String TABLA_CUENTAS = "t_usuarios";
    public static final String TABLA_ENTRADAS = "t_entradas";


    public BDHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CUENTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cuenta TEXT NOT NULL,"+
                "usuario TEXT NOT NULL,"+
                "apellido1 TEXT NOT NULL,"+
                "apellido2 TEXT NOT NULL,"+
                "email TEXT NOT NULL,"+
                "telefono TEXT NOT NULL,"+
                "letra TEXT NOT NULL,"+
                "escena TEXT NOT NULL,"+
                "edad TEXT NOT NULL,"+
                "contra TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_CUENTAS);
        onCreate(sqLiteDatabase);

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
