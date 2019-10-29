package com.example.m08ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    int intento=0;
    int numrandom;
    String name="";
    static List<Usuarios> lista = new ArrayList<Usuarios>();
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (lista.size()==0){
            loadInfo();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numrandom=new Random().nextInt(100)+1;
        final TextView titulo=(TextView) findViewById(R.id.Titulo);
        final EditText imput=(EditText) findViewById(R.id.imputid);
        final TextView intentos=(TextView) findViewById(R.id.intentos);
        final Button button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(imput.getText().toString().equals("")){
                    intento++;
                    intentos.setText("intentos: "+ intento);

                }else {
                    puntoNumero(imput, intentos);

                }

            }
        });
    }
    public void puntoNumero(EditText imput,TextView intentos){
        final TextView falloacierto=(TextView) findViewById(R.id.falloOacierto);
        String num= imput.getText().toString();
        int numeroimput=Integer.parseInt(num);
        if(numeroimput==numrandom){
            falloacierto.setText("!has ganado¡"+numrandom);

            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.nombre_dialog);
            dialog.setTitle("Introduce nombre");
            Button button2 =(Button) dialog.findViewById(R.id.ok);
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText edit =(EditText) dialog.findViewById(R.id.text_name);
                    name = edit.getText().toString();
                    if (!name.equals("")) {
                        dialog.dismiss();
                        lista.add(new Usuarios(name,intento));
                        name = "";
                        intento = 0;
                        numrandom=new Random().nextInt(100)+1;
                        Collections.sort(lista);
                        guardarInfo();
                        Intent pagInfo = new Intent(MainActivity.this,Pantalla3.class);
                        startActivity(pagInfo);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "No puedes dejar el nombre en blanco", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            dialog.show();

        }else{
            if(numeroimput>numrandom){
                falloacierto.setText("el numer es menor!"+numrandom);
                intento++;
                imput.setText("");


            }else{
                falloacierto.setText("El numero es mayor!"+numrandom);
                intento++;
                imput.setText("");


            }

        }

        intentos.setText("intentos: "+ intento);
    }
    private void guardarInfo(){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("persistence.txt", Context.MODE_PRIVATE));
            for (int i=0; i<lista.size(); i++){
                osw.write(lista.get(i).nombreUser+";"+lista.get(i).numFallos);
                osw.append("\r\n");
            }
            osw.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
    private void loadInfo(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("persistence.txt")));
            String linia;
            while((linia = br.readLine())!=null){
                lista.add(new Usuarios(linia.split(";")[0],Integer.parseInt(linia.split(";")[1])));
            }
            br.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
