package com.culturaandroid.demofragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DataActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.data_layout);
		DataFragment dataFragment = new DataFragment();
		dataFragment.setDataString(getIntent().getExtras().getString("value"));
		getSupportFragmentManager().beginTransaction().add(R.id.contentDataFragment, dataFragment).commit();
	}



	
	
}
