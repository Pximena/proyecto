package com.i044114_i012114.proyectofinalandroid.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.i044114_i012114.proyectofinalandroid.Helpers.SqliteHelper;
import com.i044114_i012114.proyectofinalandroid.Models.IdUsu;
import com.i044114_i012114.proyectofinalandroid.Models.Product;
import com.i044114_i012114.proyectofinalandroid.Models.Users;
import com.i044114_i012114.proyectofinalandroid.R;

import java.util.ArrayList;
import java.util.List;

public class LoginUserActivity extends AppCompatActivity {


    List<Users> userList = new ArrayList<>();

    SqliteHelper sqliteHelper;
    private Cursor fila;
    EditText editTextUser;
    EditText editTextPassword;
    public Integer idusu =10;

    private CheckBox opcionMostrar;
    private EditText Contrasena;
    RadioButton radioButton;

    Boolean isActivateRadioButton;

    private static final String STRING_PREFERENCES ="proyectofinalandroid.Helpers.SqliteHelper";
    private static final String PREFERENCES_ESTADO_BUTTON_SESION ="estado.login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        sqliteHelper = new SqliteHelper(this, "db_contacts", null, 1);

        editTextUser = (EditText) findViewById(R.id.id_et_users);
        editTextPassword = (EditText) findViewById(R.id.id_et_password);

        opcionMostrar = (CheckBox)findViewById(R.id.opcion_mostrar);
        Contrasena = (EditText)findViewById(R.id.id_et_password);
        radioButton= (RadioButton) findViewById(R.id.id_rb_s);
        isActivateRadioButton = radioButton.isChecked();

        if(getState()){
            Intent intent = new Intent(this, ProductListActivity.class);
            this.startActivity(intent);
            finish();

        }

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isActivateRadioButton){
                    radioButton.setChecked(false);
                }
                isActivateRadioButton = radioButton.isChecked();
            }
        });

        }

    public  void  userVali(View view){

        SQLiteDatabase db = sqliteHelper.getWritableDatabase();

        String stringName = editTextUser.getText().toString();
        String stringPhone = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(stringName)){
            Toast.makeText(this, "El campo de id esta vacio", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(stringPhone)){
            Toast.makeText(this, "El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
        }else {

            String usuario=editTextUser.getText().toString();
            String contrasena=editTextPassword.getText().toString();
            fila=db.rawQuery("select id, cedula, password from users where cedula='"+usuario+"' and password='"+contrasena+"'",null);
            //preguntamos si el cursor tiene algun valor almacenado
            if(fila.moveToFirst()==true){
                //capturamos los valores del cursos y lo almacenamos en variable
                int idusua=fila.getInt(0);


                String usua=fila.getString(1);
                String pass=fila.getString(2);
                saveState();
                //preguntamos si los datos ingresados son iguales
                if (usuario.equals(usua)&&contrasena.equals(pass)) {
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty

                    Intent ven = new Intent(this, ProductListActivity.class);
                    IdUsu idUsu = new IdUsu();
                    idUsu.setIdusu(idusua);
                    Toast.makeText(this,"mii "+idusua,Toast.LENGTH_LONG).show();
                    //ven.putExtra("prueba", idusua);
                    //Toast.makeText(this, "El campo nom" + idusua, Toast.LENGTH_SHORT).show();
                   //SharedPreferences sharedPreferences1=getPreferences(ProductListActivity.MODE_PRIVATE);
                    //SharedPreferences.Editor editor = sharedPreferences1.edit();
                    //editor.putInt("Miid", idusua);
                    //editor.commit();
                    startActivity(ven);
                    Toast.makeText(this, "Bienvenido a MediFarmBlue", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }else {
                //limpiamos las las cajas de texto

                editTextUser.setText("");
                editTextPassword.setText("");
                Toast.makeText(this, "El usuario y contraseña no coinciden", Toast.LENGTH_SHORT).show();
            }

        }

    }
    public void mostrarContraseña(View v){
        // Salvar cursor
        int cursor = Contrasena.getSelectionEnd();

        if(opcionMostrar.isChecked()){
            Contrasena.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            Contrasena.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        // Restaurar cursor
        Contrasena.setSelection(cursor);
    }

    public void saveState(){
        SharedPreferences sharedPreferences = getSharedPreferences(STRING_PREFERENCES,  MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(PREFERENCES_ESTADO_BUTTON_SESION,radioButton.isChecked()).apply();

    }

    public boolean getState(){
        SharedPreferences sharedPreferences = getSharedPreferences(STRING_PREFERENCES,  MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREFERENCES_ESTADO_BUTTON_SESION,false);

    }


    public static void changeState(Context c, boolean  b){
        SharedPreferences sharedPreferences = c.getSharedPreferences(STRING_PREFERENCES,  MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(PREFERENCES_ESTADO_BUTTON_SESION,b).apply();

    }



      }