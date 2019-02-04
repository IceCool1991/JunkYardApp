package org.ieselcaminas.pmdm.junkyardapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        View thisView = inflater.inflate(R.layout.fragment2, container, false);

        final ArrayList<Car> items = new ArrayList<>();
        items.add(new Car("Ford","Focus 1.6 TDCI","Tododesguace.com","2013","WF0FXXWPDFX", R.drawable.whale2));

        recView = thisView.findViewById(R.id.recyclerViewCars);


        recView.setHasFixedSize(true);

        final CarAdapter adaptador = new CarAdapter(getContext(), items);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recView.setItemAnimator(new DefaultItemAnimator());

        return thisView;
    }
}
