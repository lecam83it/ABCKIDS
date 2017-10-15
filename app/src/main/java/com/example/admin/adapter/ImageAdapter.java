package com.example.admin.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.abckids.MainActivity;
import com.example.admin.abckids.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/13/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private List<Integer> listImage;


    public ImageAdapter(Context context, int resource, List<Integer> listImage){
        this.context = context;
        this.resource = resource;
        this.listImage = listImage;

    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public Object getItem(int i) {
        return listImage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public ImageView imgView;
    }

    public int convertPixelstoPD(int px){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int pd = px / (metrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT);
        return pd;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder  holder = null;
        if(view == null){
            holder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(resource, null);

            holder.imgView = view.findViewById(R.id.imgView);

            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.imgView.setImageResource(listImage.get(i));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.imgView.getLayoutParams();
        params.height = convertPixelstoPD(MainActivity.MAXWIDTH);
        params.width = convertPixelstoPD(MainActivity.MAXWIDTH);
        holder.imgView.setLayoutParams(params);

        return view;
    }
}
