package com.example.admin.pet_managing.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pet_managing.Interface.ItemClickListener;
import com.example.admin.pet_managing.R;

public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView petName;
    public ImageView petImage;

    private ItemClickListener itemClickListener;



    public PetViewHolder(View itemView) {
        super(itemView);
        petName=(TextView) itemView.findViewById(R.id.petName);
        petImage=(ImageView) itemView.findViewById(R.id.petImg);

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
