package org.ieselcaminas.pmdm.junkyardapp;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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

        public ImagViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.carImage);
            carBrand = itemView.findViewById(R.id.carBrand);
            carModel = itemView.findViewById(R.id.carModel);
        }

        public void bindTitular(Car i) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("/images/cars/"+ i.getVin() +"/image");
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(context).load(uri).into(imagen);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
            carBrand.setText(i.getMake());
            carModel.setText(i.getModel());
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