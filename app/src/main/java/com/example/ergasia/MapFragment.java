package com.example.ergasia;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class MapFragment extends Fragment {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;

    MyLocationNewOverlay mLocationOverlay;
    Context ctx;
    CompassOverlay mCompassOverlay;
    RotationGestureOverlay mRotationGestureOverlay;
    ScaleBarOverlay mScaleBarOverlay;
    MinimapOverlay mMinimapOverlay;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        ctx = getContext().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);


        addMapFunctionality();

        showMyLocation();

        ArrayList<MapItem> myList = createMapItems();

        addItemsOnMap();

        return view;
    }
    public void addItemsOnMap(){
        FolderOverlay poiMarkers = new FolderOverlay(getActivity());
        map.getOverlays().add(poiMarkers);

        ArrayList<MapItem> mapItems = createMapItems();

        for (MapItem mapItem : mapItems) {
            Marker itemMarker = new Marker(map);
            itemMarker.setSnippet(mapItem.getTitle());
            itemMarker.setPosition(mapItem.getGeoPoint());
            itemMarker.setIcon(getResources().getDrawable(mapItem.getPinResourceID()));
            itemMarker.setImage(getResources().getDrawable(mapItem.getImageResourceID()));
            poiMarkers.add(itemMarker);
        }
        map.invalidate();
    }


    private ArrayList<MapItem> createMapItems() {
        MapItem item;
        ArrayList<MapItem> mapItems = new ArrayList<>();

        item = new MapItem("ΘΕΑΤΡΟ ΑΠΟΛΛΟΝ",
                new GeoPoint(37.44565562809793, 24.94375781429108),
                R.drawable.ic_map,
                R.drawable.loc_apollon);
        mapItems.add(item);

        item = new MapItem("ΠΝΕΥΜΑΤΙΚΟ ΚΕΝΤΡΟ",
                new GeoPoint(37.44509808698791,  24.943355433373988),
                R.drawable.ic_map,
                R.drawable.loc_pneumatiko);
        mapItems.add(item);

        item = new MapItem("ΠΑΛΛΑΣ ΘΕΡΙΝΟΣ ΚΙΝΗΜΑΤΟΓΡΑΦΟΣ",
                new GeoPoint(37.44428744942191, 24.94211437414641),
                R.drawable.ic_map,
                R.drawable.loc_cinama);
        mapItems.add(item);

        item = new MapItem("ΔΗΜΟΤΙΚΗ ΒΙΒΛΙΟΘΗΚΗ",
                new GeoPoint(37.44513961934941, 24.94334319994677),
                R.drawable.ic_map,
                R.drawable.loc_bibliothiki);
        mapItems.add(item);

        item = new MapItem("ΠΛΑΤΕΙΑ ΜΙΑΟΥΛΗ",
                new GeoPoint(37.44468938199177, 24.942900158004278),
                R.drawable.ic_map,
                R.drawable.loc_miaouli);
        mapItems.add(item);

        item = new MapItem("ΙΝΣΤΙΤΟΥΤΟ ΚΥΒΕΛΗ",
                new GeoPoint(37.44717789218548, 24.943822211606257),
                R.drawable.ic_map,
                R.drawable.loc_kiveli);
        mapItems.add(item);

        item = new MapItem("ΠΑΝΕΠΙΣΤΗΜΙΟ ΑΙΓΑΙΟΥ",
                new GeoPoint(37.445602087126616, 24.941633529034956),
                R.drawable.ic_map,
                R.drawable.loc_panepistimio);
        mapItems.add(item);

        item = new MapItem("ΕΠΑΥΛΗ ΤΣΙΠΩΡΙΝΑ",
                new GeoPoint(37.387906811589495, 24.889373031842915),
                R.drawable.ic_map,
                R.drawable.loc_epavli);
        mapItems.add(item);

        item = new MapItem("ΙΕΡΑ ΜΟΝΗ ΠΑΤΕΡΩΝ ΚΑΠΟΥΚΙΝΩΝ",
                new GeoPoint(37.45122627111804, 24.93593672060179),
                R.drawable.ic_map,
                R.drawable.loc_anwsyros);
        mapItems.add(item);

        item = new MapItem("ΠΛΑΤΕΙΑ ΦΟΙΝΙΚΑ",
                new GeoPoint(37.39851855601883, 24.8785623666243),
                R.drawable.ic_map,
                R.drawable.loc_foinikas);
        mapItems.add(item);

       item = new MapItem("ΚΑΠΙ ΕΡΜΟΥΠΟΛΗΣ",
                new GeoPoint(37.439606770394626, 24.936276465572167),
                R.drawable.ic_map,
                R.drawable.loc_kapi);
        mapItems.add(item);

        item = new MapItem("ΕΚΠΑΙΔΕΥΤΗΡΙΑ ΔΕΛΑΣΑΛ ΣΥΡΟΥ",
                new GeoPoint(37.44776628464118, 24.939099225097866),
                R.drawable.ic_map,
                R.drawable.loc_delasal);
        mapItems.add(item);

        item = new MapItem("ΜΕΓΑΡΟΝ ΚΑΦΕ",
                new GeoPoint(37.443870851372246, 24.944285922387497),
                R.drawable.ic_map,
                R.drawable.loc_megaron);
        mapItems.add(item);

        item = new MapItem("ΚΑΦΕ ΒΡΑΖΙΛΙΑΝΑ",
                new GeoPoint(37.449555727262435, 24.936029777505127),
                R.drawable.ic_map,
                R.drawable.loc_braziliana);
        mapItems.add(item);

        return mapItems;

    }

    private void showMyLocation() {
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx), map);
        this.mLocationOverlay.enableMyLocation();
        map.getOverlays().add(this.mLocationOverlay);
    }

    private void addMapFunctionality() {
        map.setBuiltInZoomControls(false);

        IMapController mapController = map.getController();
        mapController.setZoom(16);
        GeoPoint startPoint = new GeoPoint(37.444644, 24.942783);
        mapController.setCenter(startPoint);



        this.mCompassOverlay = new CompassOverlay(ctx, new InternalCompassOrientationProvider(ctx), map);
        this.mCompassOverlay.enableCompass();
        map.getOverlays().add(this.mCompassOverlay);

        mRotationGestureOverlay = new RotationGestureOverlay(map);
        mRotationGestureOverlay.setEnabled(true);
        map.setMultiTouchControls(true);
        map.getOverlays().add(this.mRotationGestureOverlay);

        final DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        map.getOverlays().add(this.mScaleBarOverlay);

    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissions.length > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(), permissionsToRequest.toArray(new String[0]), REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (permissions.length > 0) {
            ActivityCompat.requestPermissions(getActivity(), permissionsToRequest.toArray(new String[0]), REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}


