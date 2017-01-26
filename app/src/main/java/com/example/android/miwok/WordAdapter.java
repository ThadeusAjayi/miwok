package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.android.miwok.R.color.category_family;
import static com.example.android.miwok.R.color.category_numbers;
import static com.example.android.miwok.R.id.img;

/**
 * Created by jayson surface on 21/01/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    int mColorResource;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResource){

        super(context,0,words);
        mColorResource = colorResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word currentWord = getItem(position);
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView defaultTranslate = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTranslate.setText(currentWord.getDefaultTranslation());


        TextView miwokTranslate = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTranslate.setText(currentWord.getMiwokTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.img);

        if(currentWord.hasImage())
            imageView.setImageResource(currentWord.getmResource());
        else
            imageView.setVisibility(View.GONE);

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.words);
               // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResource);
                // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}