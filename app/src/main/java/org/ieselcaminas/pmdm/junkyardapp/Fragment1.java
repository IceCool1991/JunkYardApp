package org.ieselcaminas.pmdm.junkyardapp;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    RecyclerView recView;

    public Fragment1(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View thisView = inflater.inflate(R.layout.fragment1, container, false);

        final ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));
        items.add(new Item("Cigueñal","1456892","Tododesguace.com","240€","Ford C-Max", R.drawable.whale));

        recView = thisView.findViewById(R.id.recyclerView);

        recView.setHasFixedSize(true);

        final ItemAdapter adaptador = new ItemAdapter(getContext(), items);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartFragment fragment2 = new PartFragment();
                Transition slide = TransitionInflater.from(getActivity()).inflateTransition(R.transition.slide);
                Transition fade = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);

                fragment2.setSharedElementEnterTransition(slide);
                fragment2.setSharedElementReturnTransition(fade);

                ImageView idlogo = thisView.findViewById(R.id.partImage);

                /*TextView partName =  thisView.findViewById(R.id.partName);
                TextView partRef =  thisView.findViewById(R.id.partRef);
                TextView partVehicle =  thisView.findViewById(R.id.partVehicle);
                TextView partPrice =  thisView.findViewById(R.id.partPrice);
                TextView partJunkyard =  thisView.findViewById(R.id.junkYard);*/

                Item t = items.get(recView.getChildAdapterPosition(v));
                Bundle bundle = new Bundle();
                bundle.putInt("Logo", t.getImage());
                bundle.putString("Name", t.getNombre());
                bundle.putString("Ref", t.getRef());
                bundle.putString("Vehicle", t.getVehiculo());
                bundle.putString("Price", t.getPrecio());
                bundle.putString("Junkyard", t.getDesguace());

                fragment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment2)
                        .addToBackStack(null)
                        .addSharedElement(idlogo, idlogo.getTransitionName())
                        .commit();
            }
        });

        recView.setAdapter(adaptador);
        recView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recView.setItemAnimator(new DefaultItemAnimator());

        return thisView;
    }
}
