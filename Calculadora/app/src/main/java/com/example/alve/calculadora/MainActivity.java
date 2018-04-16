package com.example.alve.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.lang.String;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Somar(View view){
        EditText c1 = (EditText)findViewById(R.id.editText);
        EditText c2 = (EditText)findViewById(R.id.editText2);
        EditText c3 = (EditText)findViewById(R.id.editText3);
       // c3.setText("200");
        String x = String.valueOf(c1.getText());
        double x2 = Double.parseDouble(x);
        String y = String.valueOf(c2.getText());
        double y2 = Double.parseDouble(y);
        double z= x2+y2;
        String s =""+z+"";
        c3.setText(s);

    }
    public void Subtrair(View view){
        EditText c3 = (EditText)findViewById(R.id.editText3);
        c3.setText("200");
    }
    private void Dividir(View view){

    }
    private void Multiplicar(View view){

    }

}
