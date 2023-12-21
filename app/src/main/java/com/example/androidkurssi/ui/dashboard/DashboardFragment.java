package com.example.androidkurssi.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidkurssi.R;
import com.example.androidkurssi.databinding.FragmentDashboardBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment implements LocationListener{

    private FragmentDashboardBinding binding;
    LocationManager locationManager;
    TextView longTude;
    TextView lattTude;
    TextView addressText;
    Button MapBtnn;
    private static final String TAG ="Dashboard";
    String address;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        longTude = root.findViewById(R.id.longitudetxt);
        lattTude = root.findViewById(R.id.latitudetxt);
        addressText = root.findViewById(R.id.addresstxt);
        MapBtnn = root.findViewById(R.id.mapbtn);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Log.e(TAG,"oncreate class");

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onstart alku");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);




            Log.e(TAG, Double.toString(lastLocation.getLatitude()));
            longTude.setText(Double.toString(lastLocation.getLatitude()));
            lattTude.setText(Double.toString(lastLocation.getLongitude()));



        Geocoder geocoder;
        List<Address> addresses;
        Locale finnish = new Locale("fi", "FI");
        geocoder = new Geocoder(getContext(), finnish);
        addressText.setText(address);
        try {
            addresses = geocoder.getFromLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), 1); // Here 1 represent maxResults
            address = addresses.get(0).getAddressLine(0); // getAddressLine returns a line of the address
            // numbered by the given index
            addressText.setText(address);
            Log.e(TAG, address);
        } catch (IOException e) {
            e.printStackTrace();
            //addressText.setText("Osoitteen haku epäonnistui!");

        }

        Uri gmmIntentUri = Uri.parse("geo:"+lastLocation.getLatitude()+","+lastLocation.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }

        MapBtnn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(mapIntent);
            }
        });
    }




    @Override
    public void onStop() {
        super.onStop();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        longTude.setText(Double.toString(location.getLatitude()));
        lattTude.setText(Double.toString(location.getLongitude()));
        addressText.setText(address);
    }
}

