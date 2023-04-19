package com.example.myetesito;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myetesito.Basesita.BDCuentas;
import com.example.myetesito.Basesita.BDHelper;
import com.example.myetesito.Basesita.BDinfo;
import com.example.myetesito.Basesita.tablitas.Cuentas;
import com.example.myetesito.Encriptación.Sha1;
import com.example.myetesito.Json.Info;
import com.example.myetesito.Json.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.example.myetesito.Basesita.BDHelper;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IniciarSesion (View v){
        EditText userName = (EditText) findViewById(R.id.editTextISuserName);
        EditText Password = (EditText) findViewById(R.id.editTextISPassword);

        String mensaje = "";

        if("".equals(userName.getText().toString()) || "".equals(Password.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }else{
            if(userName.length() > 20 || Password.length() > 30){
                mensaje = "Parametro Erroneo";
                if(userName.length() > 20){mensaje = "Nombre de Usuario Muy Largo";}
                if(Password.length() > 30){mensaje = "Contraseña Muy Larga";}
            }else {
                try {

                    Sha1 digest = new Sha1();
                    byte[] txtByte = digest.createSha1(userName.getText().toString() + Password.getText().toString());
                    String Sha1Password1 = digest.bytesToHex(txtByte);

                    Json json = new Json();

                    boolean BucleArchivo = true;
                    int x = 1;
                    int numArchivo = 0;
                    while (BucleArchivo) {
                        BDinfo bdInfo = new BDinfo(Login.this);
                        if (bdInfo.comprobarInfo(x)) {
                            String completoTexto = bdInfo.verInfo(x);

                            Info datos = json.leerJson(completoTexto);
                            String Sha1Password2 = datos.getPassword();

                            if (Sha1Password1.equals(Sha1Password2)) {
                                mensaje = "Usuario Encontrado";
                                numArchivo = x;
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        }else{
                            mensaje = "Usuario no Encontrado";
                            BucleArchivo = false;
                        }
                    }

                    if("Usuario Encontrado".equals(mensaje)){
                        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Website.class);
                        intent.putExtra("numArchivo", numArchivo);
                        intent.putExtra("numLista", 1);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    mensaje = "Error en el Archivo";
                }
            }
        }
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Registrarse (View v){
        Intent intent = new Intent (Login.this, Register.class);
        startActivity( intent );
    }

    public void OlvidarContra (View v){
        Intent intent = new Intent (Login.this, Forgotpass.class);
        startActivity( intent );
    }

}

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BDCuentas bdCuentas = new BDCuentas(Login.this);


    }

    public void IniciarSesion (View v){
        EditText userName = (EditText) findViewById(R.id.editTextISuserName);
        EditText Password = (EditText) findViewById(R.id.editTextISPassword);

        String mensaje = "Hay un error inesperado? Los duendes estan trabajando en ello";

        if("".equals(userName.getText().toString()) || "".equals(Password.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }else{
            if(userName.length() > 20 || Password.length() > 30){
                mensaje = "Parametro Erroneo";
                if(userName.length() > 20){mensaje = "Nombre de Usuario Muy Largo";}
                if(Password.length() > 30){mensaje = "Contraseña Muy Larga";}
            }else {
                    try {

                        BDHelper bdHelper = new BDHelper(this);

                        String user = userName.getText().toString();
                        String contra = Password.getText().toString();

                        Sha1 digest = new Sha1();
                        byte[] txtByte = digest.createSha1(userName.getText().toString() + Password.getText().toString());
                        String Sha1Password = digest.bytesToHex(txtByte);
                        String ValorPassword = Sha1Password;

                        Boolean checkCredencials = bdHelper.checkUsuarioPassw(user,ValorPassword);



                        if (checkCredencials==true){
                            mensaje = "Usuario Encontrado";
                            Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Website.class);
                            startActivity(intent);
                        }

                    }

                    catch (Exception e) {
                    }

            }
        }
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
    }


    public void Registrarse (View v){
        Intent intent = new Intent (Login.this, Register.class);
        startActivity( intent );
    }

    public void OlvidarContra (View v){
        Intent intent = new Intent (Login.this, Forgotpass.class);
        startActivity( intent );
    }
}
 */