package com.example.aramirez.paleta20;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{


    private List<Button>buttons;

    private static final int[] BUTTON_IDS = {
        R.id.botonGeneral,
        R.id.btprueba1,
        R.id.btprueba2
    };


    int colorPorDefecto,numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////este es el array de id guadados como el onclick listener y onLongclick listener

        buttons = new ArrayList<Button>();

        for(int id : BUTTON_IDS) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
            button.setOnLongClickListener(this);
            buttons.add(button);
        }


        /// es necesario jalar el color por defecto al iniciar aplicacion

        colorPorDefecto = ContextCompat.getColor(this,R.color.colorbotones);

    }


    ////lista de onclick inicializado con un implement

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.botonGeneral):


              Toast.makeText(this, "texto 1 enviado", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.btprueba1):
                Toast.makeText(this, "texto 2 enviado", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.btprueba2):
                Toast.makeText(this, "texto 3 enviado", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {

        /// aqui envia un numero para identificar luego cual seleccionamos y
        /// ademas facilitar el if del metodo abrir selector

        switch (v.getId()){
            case (R.id.botonGeneral):
                numero=0;
                abrirSelector();
                break;

            case (R.id.btprueba1):
                numero=1;
                abrirSelector();
               break;

            case (R.id.btprueba2):
               numero=2;
                abrirSelector();
                break;
        }
        return true;
    }



    private void abrirSelector() {
        AmbilWarnaDialog colorseleccionado = new AmbilWarnaDialog(this, colorPorDefecto, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                /// aqui recibimos el color que tiene por defecto que jalamos al iniciar la app

                      colorPorDefecto = color;

                      // aqui colocamos el if  y validamos que numero que sobre escribió el
                        // onlongclick listener

                        if(numero!=0){
                            buttons.get(numero).setBackgroundColor(colorPorDefecto);
                        }else

                            //// aqui esta el for que cambiara todos los botones excepto el mismo
                            /// si quiere que cambie el tambien modificar la variabe i de  1 a numero 0

                            for(int i=1;i<3;i++){
                             buttons.get(i).setBackgroundColor(colorPorDefecto);
               }
                }
        });

        colorseleccionado.show();
    }

    }

