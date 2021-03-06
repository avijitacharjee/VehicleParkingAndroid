package com.avijit.vehicleparking;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    DrawerLayout drawer;
    Toolbar toolbar;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        MapsFragment homeFragment = new MapsFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.news_fragment_container,homeFragment);
        ft.commit();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        toolbar.setTitle("Vehicle Parking");
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primaryDarkColor));

        navigationView.setNavigationItemSelectedListener(item->{
            int id = item.getItemId();

            switch (id) {
                case R.id.nav_history : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new HistoryFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_home : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new MapsFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_profile : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new ProfileFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_help : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new HelpFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_contact : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new ContactFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_pricing : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new PricingFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_payment : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new PaymentFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }
                /*case R.id.nav_reserve : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new ReserveFragment());
                    ft.commit();
                    closeDrawer();
                    break;
                }*/
                case R.id.nav_bookings : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.news_fragment_container,new AfterBookingFragment()).addToBackStack(null);
                    ft.commit();
                    closeDrawer();
                    break;
                }

                case R.id.nav_logout : {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
/*
            ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.news_fragment_container,new AddNewsTypeFragment());
            ft.commit();
            closeDrawer();*/
            return true;
        });

        EndDrawerToggle toggle = new EndDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(v->super.onBackPressed());
        /*Menu menu = navigationView.getMenu();
        MenuItem menuItem= menu.findItem(R.id.group_title_1);
        SpannableString s = new SpannableString(menuItem.getTitle());
        s.setSpan(new TextAppearanceSpan(this,R.style.TextAppearance44),0,s.length(),0);
        menuItem.setTitle(s);*/
        if(getSharedPreferences("s",MODE_PRIVATE).getInt("balance",-1000)==-1000){
            getSharedPreferences("s",MODE_PRIVATE).edit().putInt("balance",10000).apply();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(22.3721, 91.7930);
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.3591,91.8215)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.3269,91.8162)).title(""));
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                try{
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/"+"22.3731, 91.7990/"+marker.getPosition().latitude+","+marker.getPosition().longitude);

                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                catch (Exception e){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return true;
            }
        });*/
    private void closeDrawer(){
        if(drawer.isDrawerOpen(Gravity.RIGHT)){
            drawer.closeDrawer(Gravity.RIGHT);
        }
    }
}