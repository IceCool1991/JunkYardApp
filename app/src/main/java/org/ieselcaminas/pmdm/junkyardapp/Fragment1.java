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

    public Fragment1(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View thisView = inflater.inflate(R.layout.fragment1, container, false);

        final ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));

        recView = thisView.findViewById(R.id.recyclerView);


        recView.setHasFixedSize(true);

        final ItemAdapter adaptador = new ItemAdapter(getContext(), items);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recView.setItemAnimator(new DefaultItemAnimator());

        return thisView;

    }
}
