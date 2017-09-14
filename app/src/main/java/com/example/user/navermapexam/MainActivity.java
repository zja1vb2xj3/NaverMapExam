package com.example.user.navermapexam;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

public class MainActivity extends NMapActivity {


    private final String CLIENT_ID = "pwZFMZnWoyQ2cKJR4o5b";

    private NMapView nMapView;

    private NMapController nMapController;
    private final double longitude = 37.339892;
    private final double latitude = 126.745304;

    private NMapView.OnMapStateChangeListener onMapViewStateChangeListener;
    private NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        nMapView = new NMapView(this);
        nMapView.setClientId(CLIENT_ID);
        setContentView(nMapView);

        nMapView.setClickable(true);
        nMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        nMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
        nMapController = nMapView.getMapController();
    }


}
