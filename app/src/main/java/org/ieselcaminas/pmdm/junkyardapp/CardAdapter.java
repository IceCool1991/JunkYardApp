package org.ieselcaminas.pmdm.junkyardapp;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ImagViewHolder> implements View.OnClickListener {

    private static ArrayList<Card> items;
    private View.OnClickListener listener;
    private Context context;

    private String[] spinnerMenu = new String[]{"", "Renombrar", "", "Copiar", "", "Cortar", "", "Eliminar", "", "Mover", ""};

    public CardAdapter(Context context, ArrayList<Card> items) {
        this.items = items;
        this.context = context;
    }

    public class ImagViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView texto;
        private Spinner spinner;

        public ImagViewHolder(View itemView) {
            super(itemView);

            imagen = (ImageView) itemView.findViewById(R.id.imageView);
            texto = (TextView) itemView.findViewById(R.id.cardName);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
        }

        public void bindTitular(Card i) {
            imagen.setImageResource(i.getImagen());
            texto.setText(i.getNombre());
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerMenu);
            spinner.setAdapter(adaptador);
        }
    }

    @Override
    public ImagViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        itemView.setOnClickListener(this);
        ImagViewHolder ivh = new ImagViewHolder(itemView);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ImagViewHolder viewHolder, int pos) {
        Card item = items.get(pos);
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

