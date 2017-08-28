package com.example.manng.testnews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by manng on 27-Jul-17.
 */

public class MainActivity extends AppCompatActivity {

    ListView listNewsTitle;
    ArrayList<News> newsArrayList;
    NewsArrayAdapter newsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNewsTitle = (ListView) findViewById(R.id.lst_news_title);
        newsArrayList = new ArrayList<News>();
        newsArrayList.add(new News("Here’s what’s really behind Jon Snow’s unfailing honesty in Game of Thrones’ season 7 finale",
                "From a character perspective, it makes sense. From a narrative perspective, it’s more significant than that",
                "https://www.theverge.com/2017/8/28/16213078/game-of-thrones-dragon-and-wolf-jon-snow-honesty-season-7-finale",
                "https://cdn.vox-cdn.com/thumbor/5unzwAGZXKTMtrFeocWU8ytG9AE=/0x164:3150x1936/1600x900/cdn.vox-cdn.com/uploads/chorus_image/image/56390431/63f38aaab0ee09337e31d21c54adbefd913e1553d24aca0154b7bfea0a91a924a97f45e8b28a94502b546b8a638e6c7f.0.jpg"));
        newsArrayAdapter = new NewsArrayAdapter(MainActivity.this, R.layout.layout_news_row, newsArrayList);
        listNewsTitle.setAdapter(newsArrayAdapter);

        new ParserNewsJSONTask().execute("https://newsapi.org/v1/articles?source=the-verge&sortBy=top&apiKey=f2a2c2f3b48c4608baa6dd38a187bab3");
    }

    public class ParserNewsJSONTask extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            ArrayList<News> newsArrayListResult = new ArrayList<News>();
            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setReadTimeout(500);
                httpsURLConnection.setConnectTimeout(500);

                int responseCode = httpsURLConnection.getResponseCode();
                Toast.makeText(MainActivity.this, responseCode + "", Toast.LENGTH_SHORT).show();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    Toast.makeText(MainActivity.this, jsonArray.length() + "", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject news = jsonArray.getJSONObject(i);
                        String title = news.getString("title");
                        String description = news.getString("description");
                        String link = news.getString("url");
                        String image = news.getString("urlToImage");
                        News newsObject = new News(title, description, link, image);
                        newsArrayListResult.add(newsObject);
                    }
                    return newsArrayListResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newsArrayListResult;
        }

        @Override
        protected void onPostExecute(ArrayList<News> newsArrayList) {
            super.onPostExecute(newsArrayList);
//            Toast.makeText(MainActivity.this, newsArrayList.size() + "", Toast.LENGTH_SHORT).show();
//            newsArrayAdapter.clear();
//            newsArrayAdapter.addAll(newsArrayList);
//            newsArrayAdapter.notifyDataSetChanged();
        }
    }


}
