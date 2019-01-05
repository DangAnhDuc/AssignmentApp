package com.example.admin.pet_managing.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.pet_managing.R;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images = {
            R.drawable.image_11,
            R.drawable.image_21,
            R.drawable.image_31,
            R.drawable.image_41
    };
    // list of titles
    public String[] lst_title = {
            "PET TRACKING",
            "VET CONTACT",
            "PET MANAGING",
            "CHAT ROOM"
    }   ;

    // list of descriptions
    public String[] lst_description = {
            "Real time tracking give information of current location of pet",
            "Contact information of Vet in current location",
            "Helping user to manage pet and observer all information about pet",
            "Live chat room in current location of user to communicate with other user"};
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(192,219,238),
            Color.rgb(8,75,104),
            Color.rgb(239,131,33),
            Color.rgb(75,197,208)
    };

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        RelativeLayout layoutslide = (RelativeLayout) view.findViewById(R.id.slideRelativeLayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideImg);
        TextView txttitle= (TextView) view.findViewById(R.id.slideTxtTitle);
        TextView description = (TextView) view.findViewById(R.id.slideTxtDes);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
