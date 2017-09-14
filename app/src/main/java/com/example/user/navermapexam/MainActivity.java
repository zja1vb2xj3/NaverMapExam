package com.example.user.navermapexam;

import android.os.Bundle;
import android.util.Log;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

import butterknife.ButterKnife;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

public class MainActivity extends NMapActivity {


    private final String CLIENT_ID = "pwZFMZnWoyQ2cKJR4o5b";

    private NMapView nMapView;

    private NMapController nMapController;

    private NMapView.OnMapStateChangeListener onMapViewStateChangeListener;
    private NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener;

    private final double latitude = 126.745304;
    private final double longitude = 37.339892;

    private final String LOG_TAG = "MainActivity";
    private NMapError nMapError;
    private NMapViewerResourceProvider nMapViewerResourceProvider;
    private NMapOverlayManager nMapOverlayManager;
    private NMapPOIdataOverlay.OnStateChangeListener onPoIdataStateChangeListener;

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
        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        nMapOverlayManager = new NMapOverlayManager(this, nMapView, nMapViewerResourceProvider);

        onMapInitHandler(nMapView, nMapError);
    }

    public void onMapInitHandler(NMapView nMapView, NMapError errorInfo){
        if(errorInfo == null){//sucess
            Log.i(LOG_TAG, "Sucess");
            nMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 11);
            nMapView.setBuiltInZoomControls(true, null);
            markerViewOperation();
        }
        else{//fail
            Log.i(LOG_TAG, "onMapInitHandler : error=" + errorInfo.toString());
        }
    }

    public void markerViewOperation(){
        int markerId = NMapPOIflagType.PIN;

        NMapPOIdata poiData = new NMapPOIdata(2, nMapViewerResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
        poiData.addPOIitem(127.061, 37.51, "Pizza 123-456", markerId, 0);
        poiData.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData, null);

        poiDataOverlay.showAllPOIdata(0);
        poiDataOverlay.setOnStateChangeListener(onPoIdataStateChangeListener);
    }



}
