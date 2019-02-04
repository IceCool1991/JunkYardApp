package org.ieselcaminas.pmdm.junkyardapp;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ImagViewHolder> implements View.OnClickListener {

    private static ArrayList<Car> cars;
    private View.OnClickListener listener;
    private Context context;

    public CarAdapter(Context context, ArrayList<Car> cars) {
        this.cars = cars;
        this.context = context;
    }

    public class ImagViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView carBrand;
        private TextView carModel;
        private TextView carVin;
        private TextView carYear;
        private TextView carJunkyard;

        public ImagViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.carImage);
            carBrand = itemView.findViewById(R.id.carBrand);
            carModel = itemView.findViewById(R.id.carModel);
            carVin = itemView.findViewById(R.id.carVin);
            carYear = itemView.findViewById(R.id.carYear);
            carJunkyard = itemView.findViewById(R.id.junkYard);
        }

        public void bindTitular(Car i) {
            imagen.setImageResource(i.getImage());
            carBrand.setText(i.getMarca());
            carModel.setText(i.getModelo());
            carVin.setText(i.getVin());
            carYear.setText(i.getAño());
            carJunkyard.setText(i.getDesguace());
        }
    }

    @Override
    public ImagViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.car_item, viewGroup, false);
        itemView.setOnClickListener(this);
        ImagViewHolder ivh = new ImagViewHolder(itemView);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ImagViewHolder viewHolder, int pos) {
        Car car = cars.get(pos);
        viewHolder.bindTitular(car);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}