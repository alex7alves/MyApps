package alex.alves.textocamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    SurfaceView camera;
    TextView texto;
    CameraSource capturar;
    final int cameraPermission = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = (SurfaceView)findViewById(R.id.surfaceView);
        texto =  (TextView)findViewById(R.id.textView4);
        getTexto();
    }

    public void getTexto() {


        // Da API vision do google
        TextRecognizer pegarTexto = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!pegarTexto.isOperational()){
            Toast.makeText(getApplicationContext(), " Texto não abriu",Toast.LENGTH_SHORT).show();
        }else{
            capturar = new CameraSource.Builder(getApplicationContext(),pegarTexto)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280,1024)
            .setRequestedFps(2.0f)
            .setAutoFocusEnabled(true)
            .build();

            SparseArray<TextBlock> palavras = pegarTexto.detect(frame);

            StringBuilder s = new StringBuilder();

            for(int i=0;i<palavras.size();i++){

                TextBlock p = palavras.valueAt(i);
                s.append(p.getValue());
                s.append("\n");
            }
           // campo.setText(s.toString());
        }
    }
}
