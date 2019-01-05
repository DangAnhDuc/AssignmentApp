package com.example.admin.pet_managing;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.pet_managing.Common.Common;
import com.example.admin.pet_managing.Model.Tracking;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class TrackingFragment extends Fragment implements OnMapReadyCallback{
    View myFragment;

    private GoogleMap map;
    private String email;
    DatabaseReference locations;
    Double lat,lng;
    public static TrackingFragment newInstance() {
        TrackingFragment trackingFragment=new TrackingFragment();
        return trackingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_tracking,container,false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);

        locations=FirebaseDatabase.getInstance().getReference("Locations");
        email= Common.petEmail;
        lat=Common.petLat;
        lng=Common.petLng;
        if (!TextUtils.isEmpty(email)){
            loadLocationForThisUser(email);
        }
        return myFragment;
    }

    private void loadLocationForThisUser(String email) {
        Query user_location =locations.orderByChild("email").equalTo(email);
        user_location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Tracking tracking=postSnapshot.getValue(Tracking.class);

                    LatLng friendLocation=new LatLng(Double.parseDouble(tracking.getLat()),Double.parseDouble(tracking.getLng()));

                    Location currentUser =new Location("");
                    currentUser.setLatitude(lat);
                    currentUser.setLongitude(lng);

                    Location friend =new Location("");
                    friend.setLatitude(Double.parseDouble(tracking.getLat()));
                    friend.setLongitude(Double.parseDouble(tracking.getLng()));

                    map.clear();
                    map.addMarker(new MarkerOptions()
                            .position(friendLocation)
                            .title(Common.petName)
                            .snippet("Distance "+new DecimalFormat("#.#").format(currentUser.distanceTo(friend)/1000)+" km")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),12.0f));
                }
                LatLng current=new LatLng(lat,lng);
                map.addMarker(new MarkerOptions().position(current).title("My Location"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
    }
}
