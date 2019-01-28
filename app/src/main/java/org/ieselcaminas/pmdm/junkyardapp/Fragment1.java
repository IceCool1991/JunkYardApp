package org.ieselcaminas.pmdm.junkyardapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    RecyclerView recView;

    public Fragment1()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View thisView = inflater.inflate(R.layout.fragment1, container, false);

        final ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Item 1", R.drawable.whale));
        items.add(new Item("Item 2", R.drawable.whale2));
        items.add(new Item("Item 3", R.drawable.whale3));
        items.add(new Item("Item 4", R.drawable.whale4));
        items.add(new Item("Item 5", R.drawable.whale5));
        items.add(new Item("Item 6", R.drawable.whale6));
        items.add(new Item("Item 7", R.drawable.whale7));
        items.add(new Item("Item 8", R.drawable.whale8));

        recView = thisView.findViewById(R.id.recyclerView);


        recView.setHasFixedSize(true);

        final ItemAdapter adaptador = new ItemAdapter(getContext(), items);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recView.setItemAnimator(new DefaultItemAnimator());

        return thisView;

    }
}
