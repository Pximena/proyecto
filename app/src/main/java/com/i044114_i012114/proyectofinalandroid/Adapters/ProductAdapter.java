package com.i044114_i012114.proyectofinalandroid.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.i044114_i012114.proyectofinalandroid.Helpers.SqliteHelper;
import com.i044114_i012114.proyectofinalandroid.LoginActivity;
import com.i044114_i012114.proyectofinalandroid.Models.IdUsu;
import com.i044114_i012114.proyectofinalandroid.Models.Product;
import com.i044114_i012114.proyectofinalandroid.Models.Users;
import com.i044114_i012114.proyectofinalandroid.R;
import com.i044114_i012114.proyectofinalandroid.Utilities.Constants;
import com.i044114_i012114.proyectofinalandroid.Views.DescriptionActivity;
import com.i044114_i012114.proyectofinalandroid.Views.FavoriteActivity;
import com.i044114_i012114.proyectofinalandroid.Views.LoginUserActivity;
import com.i044114_i012114.proyectofinalandroid.Views.ProductListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 21/11/2017.
 */


public class ProductAdapter  extends  RecyclerView.Adapter<ProductAdapter.ViewHolder>{

private int id;
SqliteHelper sqliteHelper;
    List<Product> contactList = new ArrayList<>();

    Context context;



    public ProductAdapter(List<Product> contactList, Context context) {
        this.contactList = contactList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textViewName.setText(contactList.get(position).getNamepro());
        //holder.textViewDescription.setText(contactList.get(position).getDescription());
        holder.textViewCantidad.setText(contactList.get(position).getCantidad());
        Picasso.with(context).load(contactList.get(position).getUrl()).into(holder.imageView);

        holder.checkBoxfav.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                    Intent  intent = new Intent(context, FavoriteActivity.class);

                IdUsu idUsu = new IdUsu();
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();

                //ContentValues values = ew ContentValues();
               // values.put(Constants.TABLA_FIELD_ID_US, idUsu.getIdusu());
                //values.put(Constants.TABLA_FIELD_ID_PROD, "1");

                //db.insert(Constants.TABLA_NAME_FAVORITE, Constants.TABLA_FIELD_ID, values);
                Toast.makeText(context, "Bienvenido a MediFarmBlue "+ holder.getItemId(), Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewCantidad;
        ImageView imageView;
        CheckBox checkBoxfav;




        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);

            textViewName = (TextView) item.findViewById(R.id.id_tv_item_name);
            textViewDescription = (TextView) item.findViewById(R.id.id_tv_item_des);
            textViewCantidad = (TextView) item.findViewById(R.id.id_tv_item_can);
            imageView= (ImageView) item.findViewById(R.id.id_img_item_cardview);
            checkBoxfav= (CheckBox) item.findViewById(R.id.id_fav_pa);



        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();

            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("nameprod", contactList.get(getLayoutPosition()).getNamepro());
            intent.putExtra("description", contactList.get(getLayoutPosition()).getDescription());
            intent.putExtra("cantidad", contactList.get(getLayoutPosition()).getCantidad());
            intent.putExtra("url",contactList.get(getLayoutPosition()).getUrl());
            contextItem.startActivity(intent);


            //String valor = Integer.toString(albumModelList.get(getLayoutPosition()).getId());
            //Toast.makeText(contextItem, valor, Toast.LENGTH_SHORT).show();
        }
    }
}
