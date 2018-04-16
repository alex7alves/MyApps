package com.example.alve.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.lang.String;
public class MainActivity extends AppCompatActivity {

    EditText campo1,campo2,resultado;
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

        campo1.setText("0");
        campo2.setText("0");
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
    }
    public void Subtrair(View view){
        setResultado(getCampo(campo1)-getCampo(campo2));
    }
    public void Dividir(View view){
        setResultado(getCampo(campo1)/getCampo(campo2));
    }
    public void Multiplicar(View view){
        setResultado(getCampo(campo1)*getCampo(campo2));
    }

    public void Potencia(View view) {
        setResultado(Math.pow(getCampo(campo1),getCampo(campo2)));
    }
}
