package com.culturaandroid.demofragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if (findViewById(R.id.fragmentContent)!= null) {
        	MenuFragment menuFragment = new MenuFragment();
        	getSupportFragmentManager().beginTransaction()
        	 .add(R.id.fragmentContent, menuFragment).commit();
        }
    }

}
