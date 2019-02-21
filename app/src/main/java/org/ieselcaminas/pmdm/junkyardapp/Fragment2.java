package org.ieselcaminas.pmdm.junkyardapp;
import android.content.Intent;
import android.os.Bundle;
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

        final ArrayList<Car> items = new ArrayList<>();
        items.add(new Car("Ford","Focus 1.6 TDCI","Tododesguace.com","2013","WF0FXXWPDFX", R.drawable.whale2));

        recView = thisView.findViewById(R.id.recyclerViewCars);

        recView.setHasFixedSize(true);

        final CarAdapter adaptador = new CarAdapter(getContext(), items);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarFragment fragment2 = new CarFragment();
                Transition slide = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);
                Transition fade = TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_bounds);

                fragment2.setSharedElementEnterTransition(slide);
                fragment2.setSharedElementReturnTransition(fade);


                Car t = items.get(recView.getChildAdapterPosition(v));
                Bundle bundle = new Bundle();
                bundle.putInt("Logo", t.getImage());
                /*bundle.putString("Name", t.getNombre());
                bundle.putString("Ref", t.getRef());
                bundle.putString("Vehicle", t.getVehiculo());
                bundle.putString("Price", t.getPrecio());*/
                bundle.putString("Junkyard", t.getDesguace());

                fragment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment2)
                        .addToBackStack(null)
                        .addSharedElement(thisView.findViewById(R.id.partImage), thisView.findViewById(R.id.partImage).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partName), thisView.findViewById(R.id.partName).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partVehicle), thisView.findViewById(R.id.partVehicle).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partPrice), thisView.findViewById(R.id.partPrice).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.partRef), thisView.findViewById(R.id.partRef).getTransitionName())
                        .addSharedElement(thisView.findViewById(R.id.junkYard), thisView.findViewById(R.id.junkYard).getTransitionName())
                        .commit();
            }
        });
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
