package com.example.manng.testnews;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by manng on 28-Aug-17.
 */

public class NewsArrayAdapter extends ArrayAdapter{
    Context context;
    int layout;
    ArrayList<News> arrayList;
    ImageView imageView;
    TextView txtTitle, txtDescript;
    public NewsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<News> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_news_row, null);
        imageView = convertView.findViewById(R.id.img_news);
        txtTitle = convertView.findViewById(R.id.txt_news_title);
        txtDescript = convertView.findViewById(R.id.txt_news_description);

        Picasso.with(context).load("https://cnet4.cbsistatic.com/img/QJcTT2ab-sYWwOGrxJc0MXSt3UI=/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg").into(imageView);
//        Picasso.with(context).load(arrayList.get(position).getImage()).into(imageView);
        txtTitle.setText(arrayList.get(position).getTitle().toString());
        txtDescript.setText(arrayList.get(position).getDescription().toString() + " ...");
        Toast.makeText(context, arrayList.get(position).getImage(), Toast.LENGTH_SHORT).show();

        return convertView;
    }
}
