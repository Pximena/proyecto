package com.i044114_i012114.proyectofinalandroid.Views;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.i044114_i012114.proyectofinalandroid.Adapters.ProductAdapter;
import com.i044114_i012114.proyectofinalandroid.Helpers.SqliteHelper;
import com.i044114_i012114.proyectofinalandroid.Models.Product;
import com.i044114_i012114.proyectofinalandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    RecyclerView recyclerViewContacts;
    ProductAdapter productosAdapter;
    List<Product> productList = new ArrayList<>();
    SqliteHelper sqliteHelper;
    FloatingActionButton floatingActionButton;
   TextView tex;
    public int id_usuario;
    // = getIntent().getExtras().getInt("prueba");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerViewContacts = (RecyclerView) findViewById(R.id.id_rv_contacts);
        tex=(TextView) findViewById(R.id.p);
        //tex.setText( String.valueOf (getIntent().getExtras().getInt("prueba")));
       // id_usuario=Integer.parseInt(tex.getText().toString());
        sqliteHelper = new SqliteHelper(this, "db_contact", null, 1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewContacts.setLayoutManager(linearLayoutManager);


        listProduct();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.id_fb_cs);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUserActivity.changeState(ProductListActivity.this,false);
                finish();
            }
        });

    }
    public void listProduct(){
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id,namepro,description,cantidad,url from products order by id desc", null);

        while (cursor.moveToNext()){
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setNamepro(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setCantidad(cursor.getString(3));
            product.setUrl(cursor.getString(4));
            productList.add(product);
        }

        cursor.close();

        if (productList.size() != 0){
            processData();
        }else{
            Toast.makeText(this, "Lista vacia", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        productosAdapter = new ProductAdapter(productList, getApplicationContext());
        recyclerViewContacts.setAdapter(productosAdapter);
    }

    public void estus(){
        id_usuario=Integer.parseInt(tex.getText().toString());
    }
    public int egetus(){
       return id_usuario;
    }

}
