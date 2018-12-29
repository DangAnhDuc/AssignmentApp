package com.example.admin.pet_managing.PetManage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.pet_managing.Common.Common;
import com.example.admin.pet_managing.R;
import com.squareup.picasso.Picasso;

//http://www.objgen.com/json/models/y1h
public class HealthIndexFragment extends Fragment {
    View myFragment;
    NestedScrollView listHealthIndex;
    TextView txtName, txtAge,txtGender,txtType,txtWeight,txtHeight,txtBloodType,txtFavouriteFood;
    String name,age,gender,type,weight,height,blood,food;
    ImageView petAva;
    public static HealthIndexFragment newInstance() {
        HealthIndexFragment healthIndexFragment=new HealthIndexFragment();
        return healthIndexFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_health_index,container,false);
        listHealthIndex= (NestedScrollView) myFragment.findViewById(R.id.health_index_nestedscroll);
        petAva=myFragment.findViewById(R.id.pet_ava);
        txtName=myFragment.findViewById(R.id.txtNameDetail);
        txtAge=myFragment.findViewById(R.id.txtAgeDetail);
        txtGender=myFragment.findViewById(R.id.txtGenderDetail);
        txtType=myFragment.findViewById(R.id.txtTypeDetail);
        txtWeight=myFragment.findViewById(R.id.txtWeightDetail);
        txtHeight=myFragment.findViewById(R.id.txtHeightDetail);
        txtBloodType=myFragment.findViewById(R.id.txtBloodTypeDetail);
        txtFavouriteFood=myFragment.findViewById(R.id.txtFavouriteFoodeDetail);
        loadPets();
        return myFragment;
    }

    private void loadPets() {
        Picasso.with(getActivity()).load(Common.petList.get(0).getImage()).into(petAva);
        txtName.setText(Common.petList.get(0).getName());
        txtAge.setText(Common.petList.get(0).getAge());
        txtGender.setText(Common.petList.get(0).getGender());
        txtType.setText(Common.petList.get(0).getType());
        txtWeight.setText(Common.petList.get(0).getWeight());
        txtHeight.setText(Common.petList.get(0).getHeight());
        txtBloodType.setText(Common.petList.get(0).getBloodType());
        txtFavouriteFood.setText(Common.petList.get(0).getFavouriteFood());

    }
}

