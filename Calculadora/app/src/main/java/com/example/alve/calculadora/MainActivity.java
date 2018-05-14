package com.example.alve.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    EditText campo1,campo2,resultado;
    TextView mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitEditText();
    }
    public void setInitEditText(){
        campo1 = (EditText)findViewById(R.id.editText);
        campo2 = (EditText)findViewById(R.id.editText2);
        resultado = (EditText)findViewById(R.id.editText3);
        mostrar = (TextView)findViewById(R.id.textView);
        mostrar.setText("  ");
        campo1.setText("0");
        campo2.setText("0");
    }
    public void setMostrar(String s){
        mostrar.setText("O resultado da "+s+ " foi :");
    }
    public double getCampo( EditText c){
        String s = String.valueOf(c.getText());
        return Double.parseDouble(s);
    }
    public void setResultado(double z){
        resultado.setText(""+z+"");
    }
    public void Somar(View view){
        setResultado(getCampo(campo1)+getCampo(campo2));
        setMostrar("soma");
    }
    public void Subtrair(View view){
        setResultado(getCampo(campo1)-getCampo(campo2));
        setMostrar("subtração");
    }
    public void Dividir(View view){
        setResultado(getCampo(campo1)/getCampo(campo2));
        setMostrar("divisão");
    }
    public void Multiplicar(View view){
        setResultado(getCampo(campo1)*getCampo(campo2));
        setMostrar("multiplicação");
    }
    public void Potencia(View view) {
        setResultado(Math.pow(getCampo(campo1),getCampo(campo2)));
        setMostrar("potência");
    }
}
