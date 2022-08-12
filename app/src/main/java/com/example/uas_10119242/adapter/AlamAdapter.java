package com.example.uas_10119242.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_10119242.R;
import com.example.uas_10119242.model.ModelGor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlamAdapter extends RecyclerView.Adapter<AlamAdapter.ViewHolder> {
    List<ModelGor> items;
    AlamAdapter.onSelectData onSelectData;
    Context mContext;

    public interface onSelectData {
        void onSelected(ModelGor ModelGor);
    }

    public AlamAdapter(Context context, List<ModelGor> items, AlamAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelGor data = items.get(position);

        //Get Image
        Picasso.get()
                .load(data.getGambar())
                .into(holder.imgAlam);
        holder.nama.setText(data.getNama());
        holder.kota.setText(data.getAlamat());
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView kota;
        TextView nama;
        CardView cvWisata;
        ImageView imgAlam;

        public ViewHolder(View itemView) {
            super(itemView);

            cvWisata = (CardView) itemView.findViewById(R.id.cvWisata);
            imgAlam = itemView.findViewById(R.id.imgWisata);
            nama = itemView.findViewById(R.id.tvNama);
            kota = itemView.findViewById(R.id.tvKota);
        }
    }
}
