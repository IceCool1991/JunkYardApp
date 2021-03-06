package org.ieselcaminas.pmdm.junkyardapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ImagViewHolder> implements View.OnClickListener {

    private static ArrayList<Item> items;
    private View.OnClickListener listener;
    private Context context;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        this.context = context;
    }

    public class ImagViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView partName;
        private TextView partRef;
        private TextView partPrice;

        public ImagViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.partImage);
            partName = (TextView) itemView.findViewById(R.id.partName);
            partRef = (TextView) itemView.findViewById(R.id.partRef);
            partPrice = (TextView) itemView.findViewById(R.id.partPrice);
        }

        public void bindTitular(Item i) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("/images/parts/"+ i.getItemId() +"/image");
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
            partName.setText(i.getNombre());
            partRef.setText(i.getRef());
            partPrice.setText(i.getPrecio()+"€");
        }
    }

    @Override
    public ImagViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.part_item, viewGroup, false);
        itemView.setOnClickListener(this);
        ImagViewHolder ivh = new ImagViewHolder(itemView);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ImagViewHolder viewHolder, int pos) {
        Item item = items.get(pos);
        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
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