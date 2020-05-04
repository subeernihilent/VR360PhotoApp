package com.example.vrapp;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private VrPanoramaView mVRPanoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVRPanoramaView = findViewById(R.id.vrPanoramaView);
        loadPhotoSphere();
    }

    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
            inputStream = assetManager.open("360photo1.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
    }

    @Override
    public void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();
    }
}
