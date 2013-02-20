package com.culturaandroid.demofragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String item = (String) getListAdapter().getItem(position);
		DataFragment dataFragment = (DataFragment) getFragmentManager()
				.findFragmentById(R.id.datafragment);
		if (dataFragment != null && dataFragment.isInLayout()) { 
			dataFragment.setData(item);
		}else {
			Intent intent = new Intent(getActivity().getApplicationContext(), DataActivity.class);
			intent.putExtra("value", item);
			startActivity(intent);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("TEST","Carga Datos");
		String values[] = new String [] {"RealMadrid", "Barça","Android","Google","Chismes" };
		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	

}
