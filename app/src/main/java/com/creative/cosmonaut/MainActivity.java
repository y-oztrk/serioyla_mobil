package com.creative.cosmonaut;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
bottomNav=(ChipNavigationBar)findViewById(R.id.bottom_nav);
if (savedInstanceState==null){
    bottomNav.setItemSelected(R.id.home,true);
    fragmentManager = getSupportFragmentManager();
    VitrinFragment vitrinFragment = new VitrinFragment() ;
    fragmentManager.beginTransaction()
            .replace(R.id.Fragment_container,vitrinFragment)
            .commit();
}
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new VitrinFragment();
                        break;
                    case R.id.activity:
                        fragment = new HareketlerFragment();
                        break;
                    case R.id.favorites:
                        fragment = new KesfetFragment();
                        break;
                    case R.id.settings:
                        fragment = new AyarlarFragment();
                        break;

                }


                if (fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.Fragment_container,fragment)
                            .commit();

                }else {
                    Log.e(TAG,"Parça oluşturulurken hata oluştu");
                }



            }
        });

    }

}