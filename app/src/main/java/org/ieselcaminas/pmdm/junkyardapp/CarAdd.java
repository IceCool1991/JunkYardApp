package org.ieselcaminas.pmdm.junkyardapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.UUID;

public class CarAdd extends AppCompatActivity {

    FirebaseUser user;
    ImageView imageView;
    Uri filePath;
    com.esafirm.imagepicker.model.Image image;
    FirebaseStorage storage;
    StorageReference storageReference;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);

        final EditText carMake = findViewById(R.id.carMakeAdd);
        final EditText carModel = findViewById(R.id.carModelAdd);
        final EditText carYear = findViewById(R.id.carYearAdd);
        final EditText carEmgine = findViewById(R.id.carEngineAdd);
        final EditText carVin = findViewById(R.id.carVinAdd);

        Button upload = findViewById(R.id.carUploadBtn);
        imageView = findViewById(R.id.carImageView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference items = database.getReference("cars");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(CarAdd.this) // Activity or Fragment
                        .single()
                        .start();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = carVin.getText().toString();
                items.child(key).child("make").setValue(carMake.getText().toString());
                items.child(key).child("model").setValue(carModel.getText().toString());
                items.child(key).child("year").setValue(carYear.getText().toString());
                items.child(key).child("engine").setValue(carEmgine.getText().toString());
                items.child(key).child("ownerId").setValue(user.getUid());
                uploadImage(key);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {
                filePath = Uri.fromFile(new File(image.getPath()));
                Glide.with(imageView)
                        .asBitmap()
                        .load(image.getPath())
                        .into(imageView);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImage(String partId) {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/cars/" + partId + "/image");

            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}
