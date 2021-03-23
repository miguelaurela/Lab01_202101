package com.example.lab01_202101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtSaludo;
    Button guardar;
    Boolean switche=false;

    EditText usuario;
    EditText password;
    CheckBox TermCond;
    CheckBox RmbUser;
    Button login;
    Button btnDeletePreferences;
    String Usuario1="Hola";
    String Contraseña1="Hola";

    String Usuario2="Hola2";
    String Contraseña2="Hola2";

    String Usuario3="Hola3";
    String Contraseña3="Hola3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario =findViewById(R.id.editUser);
        password=findViewById(R.id.editPassword);
        login =findViewById(R.id.btn_continue);
        TermCond=findViewById(R.id.checkBoxTC);
        RmbUser=findViewById(R.id.checkBoxRemenber);
        btnDeletePreferences=findViewById(R.id.btnDeletePrefe);
        login.setEnabled(false);

        cargarPreferencias();
        TermCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Terminosycondiciones();
            }
        });
        btnDeletePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarPreferencias();
            }


        });
        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(usuario.getText())||TextUtils.isEmpty(password.getText())){
                Toast.makeText(getApplicationContext(),"usuario o contraseña no ingresada", Toast.LENGTH_LONG).show();
            }
            else{
               Sesion();
            }
        }


        });

        txtSaludo =findViewById(R.id.txt_saludo);
        guardar =findViewById(R.id.btn_guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!switche) {
                    txtSaludo.setText("HOLA MIGUEL AURELA");
                    switche=true;
                }else{
                    txtSaludo.setText("APLICACIONES MOVILES");
                    switche=false;
                }
            }
        });
    }

    private void cargarPreferencias() {
        SharedPreferences preferences =getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user=preferences.getString("user","");
        String pass=preferences.getString("pass","");
        usuario.setText(user);
        password.setText(pass);

    }
    private void BorrarPreferencias() {
        SharedPreferences preferences =getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(getApplicationContext(),"Credenciales borradas", Toast.LENGTH_SHORT).show();
    }

    private void Terminosycondiciones() {
        if (TermCond.isChecked()){
            login.setEnabled(true);
        }
        if (!TermCond.isChecked()){
            login.setEnabled(false);
        }
    }
    private void Sesion() {
        String user=GetUser();
        String pass=GetPassword();
        if ((user.equals(Usuario1)&&pass.equals(Contraseña1))||(user.equals(Usuario2)&&pass.equals(Contraseña2))||(user.equals(Usuario3)&&pass.equals(Contraseña3))){
            Intent i =new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"ingreso" , Toast.LENGTH_LONG).show();
            if (RmbUser.isChecked()){
                GuardarPreferencias();
            }
        }else {
            Toast.makeText(getApplicationContext(),"usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
     }
    }

    private void GuardarPreferencias() {
        SharedPreferences preferences =getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String usuario =GetUser();
        String pass=GetPassword();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user",usuario);
        editor.putString("pass",pass);
        editor.commit();
    }

    private String GetUser() {
        return usuario.getText().toString();
    }
    private String GetPassword() {
        return password.getText().toString();
    }
}