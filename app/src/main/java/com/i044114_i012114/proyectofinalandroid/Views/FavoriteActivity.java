package com.i044114_i012114.proyectofinalandroid.Views;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.i044114_i012114.proyectofinalandroid.R;

public class FavoriteActivity extends AppCompatActivity {
TextView textView;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        LoginUserActivity loginUserActivity = new LoginUserActivity();
        textView= (TextView) findViewById(R.id.id);
        //textView.setText(String.valueOf(getIntent().getExtras().getInt("final")));


    }


}
