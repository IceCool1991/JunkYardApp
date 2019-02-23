package org.ieselcaminas.pmdm.junkyardapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartFragment extends Fragment {


    public PartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part, container, false);

        final ImageView img = view.findViewById(R.id.partLogo);
        TextView partName = view.findViewById(R.id.partNameText);
        TextView partRef = view.findViewById(R.id.partRefText);
        TextView partVehicle = view.findViewById(R.id.partVehicleText);
        TextView partPrice = view.findViewById(R.id.partPriceText);
        final TextView partJunkyard = view.findViewById(R.id.partJunkyardText);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("/images/parts/"+ getArguments().getString("itemId") +"/image");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity()).load(uri).into(img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        partName.setText(getArguments().getString("Name"));
        partRef.setText(getArguments().getString("Ref"));
        partVehicle.setText(getArguments().getString("Vehicle"));
        partPrice.setText(getArguments().getString("Price") + "€");

        final DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        users.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                partJunkyard.setText(dataSnapshot.child(getArguments().getString("Junkyard")).child("name").getValue(String.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
