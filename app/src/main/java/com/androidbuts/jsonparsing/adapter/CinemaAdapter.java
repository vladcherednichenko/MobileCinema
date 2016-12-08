package com.androidbuts.jsonparsing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.model.Film;

import java.util.List;

public class CinemaAdapter extends ArrayAdapter<Film> {

    List<Film> filmSessionList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public CinemaAdapter(Context context, List<Film> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        filmSessionList = objects;
    }

    @Override
    public Film getItem(int position) {
        return filmSessionList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Film item = getItem(position);


        //item.setName("item");
        vh.textViewName.setText(item.getFilmName());
        //vh.textViewEmail.setText(item.getEmail());
        //Picasso.with(context).load(item.getProfilePic()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        //public final ImageView imageView;
        public final TextView textViewName;
        //public final TextView textViewEmail;

        private ViewHolder(RelativeLayout rootView, TextView textViewName) {
            this.rootView = rootView;
            //this.imageView = imageView;
            this.textViewName = textViewName;
            //this.textViewEmail = textViewEmail;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            //ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            //TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);
            return new ViewHolder(rootView, textViewName);
        }
    }
}
