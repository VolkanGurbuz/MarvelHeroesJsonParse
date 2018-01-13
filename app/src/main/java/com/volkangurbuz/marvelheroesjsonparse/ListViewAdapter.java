package com.volkangurbuz.marvelheroesjsonparse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by VolkanGurbuz on 1/13/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Hero> {

    private List<Hero> heroList;

    //the context object
    private Context context;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter(List<Hero> heroList, Context context) {
        super(context, R.layout.list_items, heroList);
        this.heroList = heroList;
        this.context= context;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(context);

        //create a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //get text view and imageview
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        ImageView imageView= listViewItem.findViewById(R.id.imageView);

        //get the hero for the specified position
        Hero hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getName());
        Picasso.with(context).load(hero.getImageUrl()).into(imageView);

        //returning the listitem
        return listViewItem;
    }


}
