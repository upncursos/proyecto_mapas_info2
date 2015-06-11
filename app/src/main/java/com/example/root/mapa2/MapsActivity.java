package com.example.root.mapa2;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

// Se debe implementar GoogleMap.OnMapClickListener 
public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener{
    AutoCompleteTextView textView;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker texto1=null;

    private static final String[] DATOS = new String[] {
            "b222", "sala cad", "cidup", "b125", "biblioteca"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DATOS);
        textView = (AutoCompleteTextView)
                findViewById(R.id.autocompletar);
        textView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setOnMapClickListener(this);   // Establece como procesador de los eventos Click del mapa a esta clase
        // DIBUJO DE UN POLIGONO
        PolygonOptions rectOptions = new PolygonOptions();
        rectOptions.add(new LatLng(4.668770413418966, -74.1090739890933),
                new LatLng(4.669220865870756, -74.10851005464792),
                new LatLng(4.668994303026845, -74.108298830688),
                new LatLng(4.668488045080269, -74.10874508321285)
        );
        rectOptions.fillColor(Color.BLUE);
        Polygon polygon = mMap.addPolygon(rectOptions);



    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.v("datos ", "datos " + latLng);
    }

    public void clic(View view) {
        if(texto1!=null){
            texto1.remove();
        }
        Log.v("boton", "boton presionado");
        MarkerOptions mo = new MarkerOptions();
        mo.position(new LatLng(0, 0));
        mo.title("Aquí");

        texto1=mMap.addMarker(mo);



    }
}
