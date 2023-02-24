package com.example.gamespider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Juego_Ventana extends AppCompatActivity {
    TextView VicDer, Turno;
    ImageView piedra, papel, tijeras;
    Button Elegir;
    boolean piedraSeleccionada, papelSeleccionada, tijerasSeleccionada, juegoFinalizado, turnos=false;
    int seleccion,pcEleccion, VicODer,val=0;
    String pcCarta="";
    int Duracion = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_ventana);
        //Textos!
        VicDer= findViewById(R.id.VicDer);
        Turno = findViewById(R.id.Turno);
        //Boton!
        Elegir= findViewById(R.id.Elegir);
        //Imagenes!
        piedra = findViewById(R.id.piedra);
        papel = findViewById(R.id.papel);
        tijeras = findViewById(R.id.tijeras);


        //Inicio de Juego
        if(juegoFinalizado==false) {

            VicDer.setVisibility(View.INVISIBLE);
            //Elegir de carta
            piedra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    papelSeleccionada = false;
                    tijerasSeleccionada = false;
                    if (papelSeleccionada == false && tijerasSeleccionada == false && piedraSeleccionada == false) {
                        piedraSeleccionada = true;
                        piedra.setImageResource(R.drawable.perso);
                        papel.setImageResource(R.drawable.papel);
                        tijeras.setImageResource(R.drawable.tijeras);
                        Turno.setText("Piedra");
                        seleccion=1;
                    } else if (piedraSeleccionada == true) {
                        piedraSeleccionada = false;
                        Turno.setText("Tu turno!");
                        piedra.setImageResource(R.drawable.piedra);
                        seleccion=0;
                    }
                }
            });
            papel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    piedraSeleccionada = false;
                    tijerasSeleccionada = false;
                    if (papelSeleccionada == false && tijerasSeleccionada == false && piedraSeleccionada == false) {
                        piedraSeleccionada = true;
                        piedra.setImageResource(R.drawable.piedra);
                        papel.setImageResource(R.drawable.perso);
                        tijeras.setImageResource(R.drawable.tijeras);
                        seleccion=2;
                        Turno.setText("Papel");
                    } else if (papelSeleccionada == true) {
                        papelSeleccionada = false;
                        Turno.setText("Tu turno!");
                        piedra.setImageResource(R.drawable.papel);
                        seleccion=0;
                    }
                }
            });
            tijeras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    papelSeleccionada = false;
                    piedraSeleccionada = false;
                    if (papelSeleccionada == false && tijerasSeleccionada == false && piedraSeleccionada == false) {
                        tijerasSeleccionada = true;
                        piedra.setImageResource(R.drawable.piedra);
                        papel.setImageResource(R.drawable.papel);
                        Turno.setText("Tijeras");
                        tijeras.setImageResource(R.drawable.perso);
                        seleccion=3;
                    } else if (tijerasSeleccionada == true) {
                        tijerasSeleccionada = false;
                        Turno.setText("Tu turno!");
                        tijeras.setImageResource(R.drawable.tijeras);
                        seleccion=0;
                    }
                }
            });


            //Seleccionar
            Elegir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(turnos==false && juegoFinalizado==false && seleccion!=0){
                        turnos = true;
                        pcEleccion = pcSeleccion();
                        if(pcEleccion!=0){
                          VicODer = comparacion();
                          if(VicODer==0){

                              VicDer.setText("EMPATE :O");
                          }
                            else if(VicODer==1){

                                VicDer.setText("HAS GANADO! :D");
                            }
                            else if(VicODer==-1){

                                VicDer.setText("HAS PERDIDO! :(");
                            }
                            juegoFinalizado=true;
                            finalizar();
                        }
                    }
                }
            });



        }
    }
    private void finalizar(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Juego_Ventana.this);
        alertDialogBuilder
                .setMessage("GAME OVER! " + VicDer.getText() + " Tu oponente Eligio: " + pcCarta)
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),Juego_Ventana.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public int pcSeleccion(){
        final int min = 1;
        final int max = 3;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }
    public int comparacion(){
        if(seleccion==pcEleccion){
            val= 0;
            pcCarta="Lo mismo que tu";
        }
        if(seleccion==1 && pcEleccion==2){
            val= -1;
            pcCarta="Papel";
        }
        if(seleccion==1 && pcEleccion==3){
            val= 1;
            pcCarta="Tijeras";
        }
        if(seleccion==2 && pcEleccion==1){
            val= 1;
            pcCarta="Piedra";
        }
        if(seleccion==2 && pcEleccion==3){
            val= -1;
            pcCarta="Tijeras";
        }
        if(seleccion==3 && pcEleccion==1){
            val= -1;
            pcCarta="Piedra";
        }
        if(seleccion==3 && pcEleccion==2){
            val= 1;
            pcCarta="Papel";
        }
        return val;
    }
}