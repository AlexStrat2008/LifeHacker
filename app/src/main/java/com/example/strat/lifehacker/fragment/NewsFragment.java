package com.example.strat.lifehacker.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.example.strat.lifehacker.MainActivity;
import com.example.strat.lifehacker.adapter.CustomListAdapter;
import com.example.strat.lifehacker.jsonConnect.IMGClient2;
import com.example.strat.lifehacker.jsonConnect.JSONClient;
import com.example.strat.lifehacker.model.News;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by strat on 23.02.15.
 */
public class NewsFragment extends ListFragment {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://public-api.wordpress.com/rest/v1.1/sites/lifehacker.ru/posts/?number=10";
    private ProgressDialog pDialog;
    private ArrayList<News> movieList = new ArrayList<News>();
    private CustomListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JSONClient json = new JSONClient();
        json.execute();
        try {
            movieList = json.get();
        } catch (InterruptedException e) {
            Log.d("Ошибка", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("Ошибка", e.getMessage());
        }


        adapter = new CustomListAdapter(getActivity(), movieList);
        setListAdapter(adapter);

        new IMGClient().execute();
/*
        try {
            icon.setImageBitmap(img.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/
    }

    class IMGClient extends AsyncTask<Void, News, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (News item : movieList) {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet request = new HttpGet(item.getImgUrl());
                    HttpResponse response = null;
                    response = httpClient.execute(request);
                    InputStream input = response.getEntity().getContent();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    item.setImg(myBitmap);
                    publishProgress(item);
                }
            } catch (Exception e) {
                Log.e("Ошибка", e.toString());
                return null;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(News... item) {
            adapter.updateAdaper(movieList);
        }
    }
}
