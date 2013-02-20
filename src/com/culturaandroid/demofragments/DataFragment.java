package com.culturaandroid.demofragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DataFragment extends Fragment {
	private HttpUtility http;
	private JSONObject json;
	private ArrayList<Twitt> arregloTwitt;
	private ListView list;
	private View defaultView;
	private String busqueda;
	private Handler threadHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.d("DEBUG",arregloTwitt.size()+"");
			list = (ListView) defaultView.findViewById(R.id.listTwitts);
			list.setAdapter(new TwittAdapter(getActivity().getApplicationContext(),R.layout.row_layout,arregloTwitt));
		}
		
	};
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	public void setDataString(String data) {
		busqueda = data;
	}
	public void setData(String search) {
		busqueda = search;
		Thread thread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				http= new HttpUtility();
				arregloTwitt = new ArrayList<Twitt>();
				json = http.readTwitts(busqueda);
				try {
					Twitt t;
					JSONArray arr = json.getJSONArray("results");
					for (int i=0; i< arr.length();i++) {
						t = new Twitt();
						t.setUser(arr.getJSONObject(i).getString("from_user"));
						t.setUrlImage(arr.getJSONObject(i).getString("profile_image_url"));
						t.setComment(arr.getJSONObject(i).getString("text"));
						arregloTwitt.add(t);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				threadHandler.sendEmptyMessage(0);
			}
			
		};
		thread.start();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.content_fragment_layout, container, false);
		list = (ListView)view.findViewById(R.id.listTwitts);
		defaultView = view;
		if (busqueda!= null) {
			setData(busqueda);
		}
		return view;
	}
}
