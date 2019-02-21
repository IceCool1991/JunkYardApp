package org.ieselcaminas.pmdm.junkyardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.oswaldogh89.picker.ImagePicker;

import java.util.HashMap;
import java.util.Map;

public class CarAdd extends AppCompatActivity {
    ImagePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);

        picker = (ImagePicker) findViewById(R.id.picker);
        picker.setMainactivity(this);
        picker.SetBorderImageColor("#075e55");
        picker.enableDelateAll(false);

        HashMap<Integer, String> images = picker.GetPathImages();
        for (Map.Entry entry : images.entrySet()) {
            Log.v("IMAGENES_AGREGADAS", "TAMAÑO : " + entry.getValue());
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    picker.AddNewImage(imageReturnedIntent);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    picker.AddNewImage(imageReturnedIntent);
                }
                break;
        }
    }
}
