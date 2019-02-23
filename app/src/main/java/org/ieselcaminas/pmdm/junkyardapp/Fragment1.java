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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    RecyclerView recView;

    public Fragment1(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View thisView = inflater.inflate(R.layout.fragment1, container, false);

        final SearchView searchView = thisView.findViewById(R.id.partSearchView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference cars = database.getReference("items");

        final ArrayList<Item> items = new ArrayList<Item>();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cars.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        items.add(new Item(dataSnapshot.child("name").getValue(String.class),
                                dataSnapshot.child("ref").getValue(String.class),
                                dataSnapshot.child("ownerId").getValue(String.class),
                                dataSnapshot.child("price").getValue(String.class),
                                dataSnapshot.child("vehicle").getValue(String.class),
                                dataSnapshot.getKey()));
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

        recView = thisView.findViewById(R.id.recyclerView);
        recView.setHasFixedSize(true);
        final ItemAdapter adaptador = new ItemAdapter(getContext(), items);


        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartFragment fragment2 = new PartFragment();
                Transition slide = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);
                Transition fade = TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_bounds);

                fragment2.setSharedElementEnterTransition(slide);
                fragment2.setSharedElementReturnTransition(fade);

                Item t = items.get(recView.getChildAdapterPosition(v));
                Bundle bundle = new Bundle();
                bundle.putString("Name", t.getNombre());
                bundle.putString("Ref", t.getRef());
                bundle.putString("Vehicle", t.getVehiculo());
                bundle.putString("Price", t.getPrecio());
                bundle.putString("Junkyard", t.getOwnerId());
                bundle.putString("itemId", t.getItemId());

                fragment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment2)
                        .addToBackStack(null)
                        .addSharedElement(thisView.findViewById(R.id.partImage), thisView.findViewById(R.id.partImage).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partName), thisView.findViewById(R.id.partName).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partPrice), thisView.findViewById(R.id.partPrice).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partRef), thisView.findViewById(R.id.partRef).getTransitionName())
                        .commit();
            }
        });

        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = thisView.findViewById(R.id.addPart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                startActivity(new Intent(getContext(), PartAdd.class));
            }
        });
        return thisView;
    }
}
