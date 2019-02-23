package org.ieselcaminas.pmdm.junkyardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.ieselcaminas.pmdm.junkyardapp.R;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    RecyclerView recView;

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View thisView = inflater.inflate(R.layout.fragment2, container, false);

        final SearchView searchView = thisView.findViewById(R.id.carSearchView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference cars = database.getReference("cars");

        final ArrayList<Car> items = new ArrayList<>();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cars.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        items.add(new Car(dataSnapshot.child("make").getValue(String.class),
                                dataSnapshot.child("model").getValue(String.class),
                                dataSnapshot.child("engine").getValue(String.class),
                                dataSnapshot.child("year").getValue(String.class),
                                dataSnapshot.getKey(),
                                dataSnapshot.child("ownerId").getValue(String.class)));
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
            }
        });

        recView = thisView.findViewById(R.id.recyclerViewCars);

        recView.setHasFixedSize(true);

        final CarAdapter adaptador = new CarAdapter(getContext(), items);


        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recView.setItemAnimator(new DefaultItemAnimator());
        FloatingActionButton fab = thisView.findViewById(R.id.addCar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CarAdd.class));
            }
        });
        return thisView;
    }
}
