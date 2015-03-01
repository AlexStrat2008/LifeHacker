package com.example.strat.lifehacker.jsonConnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.strat.lifehacker.adapter.CustomListAdapter;
import com.example.strat.lifehacker.model.News;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Алексей on 01.03.2015.
 */
public class IMGClient2 extends AsyncTask<ArrayList<News>, Void, Void> {
    private CustomListAdapter adapter;

    public IMGClient2(CustomListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(ArrayList<News>... params) {

        try {
            for (ArrayList<News> i : params) {
                for (News urlS : i) {

/*                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet request = new HttpGet(urlS.getImgUrl());
                    HttpResponse response = httpClient.execute(request);
                    InputStream input = response.getEntity().getContent();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);*/
/*                    adapter.notifyDataSetChanged();*/
                    double j = 0;
                    while (true) {
                        j++;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Ошибка", e.toString());
            return null;
        }
        return null;
    }
}
