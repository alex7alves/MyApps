package alexalves.capturartexto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    EditText campo;
    ImageView imagem ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitComponentes();

    }

    public void setInitComponentes(){
        campo = (EditText)findViewById(R.id.editText2);
        imagem = (ImageView)findViewById(R.id.imageView);

        campo.setText("");
    }
    public void getTexto(View view) {

        Bitmap bm = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.olhos);

        // Da API vision do google
        TextRecognizer pegarTexto = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!pegarTexto.isOperational()){
            Toast.makeText(getApplicationContext(), " Texto não abriu",Toast.LENGTH_SHORT).show();
        }else{
            Frame frame = new Frame.Builder().setBitmap(bm).build();
            SparseArray<TextBlock> palavras = pegarTexto.detect(frame);

            StringBuilder s = new StringBuilder();

            for(int i=0;i<palavras.size();i++){

                TextBlock p = palavras.valueAt(i);
                s.append(p.getValue());
                s.append("\n");
            }
            campo.setText(s.toString());
        }
    }

    public void SegundaTela(View view){
        setContentView(R.layout.tela2);
    }
    public void TelaCheia(View view){
        Intent telaC = new Intent(MainActivity.this, SegundaTela.class);
        telaC.putExtra("tela",String.valueOf(campo.getText()));
        startActivity(telaC);
    }
}
