package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red", "weṭeṭṭi",R.mipmap.red));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.mipmap.myellow));
        words.add(new Word("dusty yellow", "ṭopiisә",R.mipmap.dyellow));
        words.add(new Word("green", "chokokki",R.mipmap.green));
        words.add(new Word("brown", "ṭakaakki",R.mipmap.brown));
        words.add(new Word("gray", "ṭopoppi",R.mipmap.grey));
        words.add(new Word("black", "kululli",R.mipmap.black));
        words.add(new Word("white", "kelelli",R.mipmap.white));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

}
