package com.example.m08ex2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pantalla3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);
        super.setTitle("Ranking");
        ArrayAdapter<Usuarios> arrayUs= new ArrayAdapter<Usuarios>(this, R.layout.activity_pantalla3,MainActivity.lista){
          @Override
          public View getView(int pos, View convertView, ViewGroup container){
              if(convertView==null){
                  convertView = getLayoutInflater().inflate(R.layout.item_layout,container,false);

              }
              ((TextView) convertView.findViewById(R.id.text_name)).setText(getItem(pos).nombreUser);
              ((TextView) convertView.findViewById(R.id.text_tries)).setText(Integer.toString(getItem(pos).numFallos));
              return convertView;
          }
        };
        final ListView listaRes = findViewById(R.id.ListView);
        listaRes.setAdapter(arrayUs);
        final Button button2 =(Button) findViewById(R.id.finish);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 Intent pagJuego = new Intent(Pantalla3.this,MainActivity.class);
                 startActivity(pagJuego);
            }
        });

    }

}
