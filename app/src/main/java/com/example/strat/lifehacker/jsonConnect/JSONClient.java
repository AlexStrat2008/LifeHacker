package com.example.strat.lifehacker.jsonConnect;

import android.os.AsyncTask;

import com.example.strat.lifehacker.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by strat on 27.02.15.
 */
public class JSONClient extends AsyncTask<Void, Void, ArrayList<News>> {

    private String urlS = "https://public-api.wordpress.com/rest/v1.1/sites/lifehacker.ru/posts/?number=10";
    BufferedReader reader;
    HttpURLConnection urlConnection;
    String resultJson = "";

    @Override
    protected ArrayList<News> doInBackground(Void... params) {
        URL url = null;
        try {
            url = new URL(urlS);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getInformation(resultJson);
    }

    @Override
    protected void onPostExecute(ArrayList<News> newses) {
        super.onPostExecute(newses);
    }

    private ArrayList<News> getInformation(String json) {
        ArrayList<News> information = new ArrayList<News>();
        try {
            JSONObject dataJsonObj = new JSONObject(json);
            JSONArray posts = dataJsonObj.getJSONArray("posts");
            JSONObject post;
            News news = null;
            for (int i = 0; i < posts.length(); i++) {
                post = posts.getJSONObject(i);
                news = new News();
                news.setTitle(parceUniode(post.getString("title")));
                news.setImgUrl(changeSizeImg(post.getString("featured_image")));
                information.add(news);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return information;
    }

    private String parceUniode(String str) {
        try {
            byte[] utf8Byte = str.getBytes("UTF8");
            return Jsoup.parse((new String(utf8Byte, "UTF8"))).text();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String changeSizeImg(String str) {
        return str.substring(0, str.length() - 4) + "-630x315.png";
    }
}
