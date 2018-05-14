package alex.alves.textocamera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView camera;
    TextView texto;
    CameraSource capturar;
    final int cameraPermission = 1001;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case cameraPermission:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        capturar.start(camera.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = (SurfaceView) findViewById(R.id.surfaceView);
        texto = (TextView) findViewById(R.id.textView4);
        getTexto();
    }

    public void getTexto() {


        // Da API vision do google
        final TextRecognizer pegarTexto = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!pegarTexto.isOperational()) {
            Toast.makeText(getApplicationContext(), " Texto não abriu", Toast.LENGTH_SHORT).show();
        } else {
            capturar = new CameraSource.Builder(getApplicationContext(), pegarTexto)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            camera.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[](Manifest.permission.CAMERA),
                            cameraPermission);
                            return;
                        }
                        capturar.start(camera.getHolder());
                        pegarTexto.setProcessor(new Detector.Processor<TextBlock>() {

                            @Override
                            public void release() {

                            }

                            @Override
                            public void receiveDetections(Detector.Detections<TextBlock> detections) {

                            }
                        });

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    capturar.stop();
                }
            });
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
