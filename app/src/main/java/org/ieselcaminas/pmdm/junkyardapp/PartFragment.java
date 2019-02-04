package org.ieselcaminas.pmdm.junkyardapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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

        ImageView img = view.findViewById(R.id.partLogo);
        TextView partName = view.findViewById(R.id.partNameText);
        TextView partRef = view.findViewById(R.id.partRefText);
        TextView partVehicle = view.findViewById(R.id.partVehicleText);
        TextView partPrice = view.findViewById(R.id.partPriceText);
        TextView partJunkyard = view.findViewById(R.id.partJunkyardText);

        img.setImageResource(getArguments().getInt("Logo"));
        partName.setText(getArguments().getString("Name"));
        partRef.setText(getArguments().getString("Ref"));
        partVehicle.setText(getArguments().getString("Vehicle"));
        partPrice.setText(getArguments().getString("Price"));
        partJunkyard.setText(getArguments().getString("Junkyard"));
        return view;

    }

}
