package com.example.admin.pet_managing.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pet_managing.Interface.ItemClickListener;
import com.example.admin.pet_managing.R;

public class VetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView vetImage;
    public TextView vetName,vetAddress,vetWebsite,vetPhone;

    private ItemClickListener itemClickListener;

    public VetViewHolder(View itemView) {
        super(itemView);
        vetImage=(ImageView) itemView.findViewById(R.id.img_vet_logo);
        vetName=(TextView) itemView.findViewById(R.id.txtVetName);
        vetAddress=(TextView) itemView.findViewById(R.id.txtVetAddress);
        vetWebsite=(TextView) itemView.findViewById(R.id.txtVetWebsite);
        vetPhone=(TextView) itemView.findViewById(R.id.txtVetPhone);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
