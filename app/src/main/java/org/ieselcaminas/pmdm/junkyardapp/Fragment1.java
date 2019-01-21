package org.ieselcaminas.pmdm.junkyardapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ieselcaminas.pmdm.junkyardapp.Card;
import org.ieselcaminas.pmdm.junkyardapp.CardAdapter;
import org.ieselcaminas.pmdm.junkyardapp.R;

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

        final ArrayList<Card> items = new ArrayList<Card>();
        items.add(new Card("Card 1", R.drawable.whale));
        items.add(new Card("Card 2", R.drawable.whale2));
        items.add(new Card("Card 3", R.drawable.whale3));
        items.add(new Card("Card 4", R.drawable.whale4));
        items.add(new Card("Card 5", R.drawable.whale5));
        items.add(new Card("Card 6", R.drawable.whale6));
        items.add(new Card("Card 7", R.drawable.whale7));
        items.add(new Card("Card 8", R.drawable.whale8));


        recView = thisView.findViewById(R.id.recyclerView);


        recView.setHasFixedSize(true);

        final CardAdapter adaptador = new CardAdapter(getContext(), items);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recView.setItemAnimator(new DefaultItemAnimator());

        return thisView;

    }
}
