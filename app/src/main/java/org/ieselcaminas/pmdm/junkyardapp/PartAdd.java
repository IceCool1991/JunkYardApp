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

public class PartAdd extends AppCompatActivity {
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
        setContentView(R.layout.activity_part_add);

        final EditText partName = findViewById(R.id.partNameAdd);
        final EditText partRef = findViewById(R.id.partRefAdd);
        final EditText partVeh = findViewById(R.id.partVehAdd);
        final EditText partPrice = findViewById(R.id.partPriceAdd);
        Button upload = findViewById(R.id.partUploadBtn);
        imageView = findViewById(R.id.partImageView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference items = database.getReference("items");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(PartAdd.this) // Activity or Fragment
                        .single()
                        .start();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = items.push().getKey();
                items.child(key).child("name").setValue(partName.getText().toString());
                items.child(key).child("price").setValue(partPrice.getText().toString());
                items.child(key).child("ref").setValue(partRef.getText().toString());
                items.child(key).child("vehicle").setValue(partVeh.getText().toString());
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

            StorageReference ref = storageReference.child("images/parts/" + partId + "/image");

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
