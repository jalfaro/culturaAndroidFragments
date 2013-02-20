package com.culturaandroid.demofragments;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TwittAdapter extends ArrayAdapter<Twitt> {
	Context ctx;
	int layout;
	InputStream is;
	Twitt t;
	ImageView imagen;
	ArrayList<Twitt> arreglo;
	Handler threadHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Drawable drawable = Drawable.createFromStream(is, "src");
			imagen.setImageDrawable(drawable);
		}
		
	};

	public TwittAdapter(Context context, int textViewResourceId,
			List<Twitt> objects) {
		super(context, textViewResourceId, objects);
		ctx = context;
		layout = textViewResourceId;
		arreglo = (ArrayList<Twitt>) objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		t = arreglo.get(position);
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(layout, null);
		}
		if (t!= null) {
			TextView usuario, texto;
			
			usuario = (TextView) v.findViewById(R.id.txtUserName);
			texto = (TextView) v.findViewById(R.id.txtComment);
			imagen = (ImageView) v.findViewById(R.id.imgTwitter);
			usuario.setText(t.getUser());
			texto.setText(t.getComment());
			
		
				Thread thr= new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						try {
							is = fetch(t.getUrlImage());
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						threadHandler.sendEmptyMessage(1);
					}
					
					
				};
				thr.start();
			

		}
		return v;
	}

	private InputStream fetch(String urlString) throws MalformedURLException, IOException {
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    HttpGet request = new HttpGet(urlString);
	    HttpResponse response = httpClient.execute(request);
	    return response.getEntity().getContent();
	}
}