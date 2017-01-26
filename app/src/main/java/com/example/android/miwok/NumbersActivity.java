package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("One","Lutti",R.mipmap.one));
        words.add(new Word("Two","Otiiko",R.mipmap.two));
        words.add(new Word("Three","Tolookosu",R.mipmap.three));
        words.add(new Word("Four","Oyiisa",R.mipmap.four));
        words.add(new Word("Five","Massokka",R.mipmap.five));
        words.add(new Word("Six","Temmokka",R.mipmap.six));
        words.add(new Word("Seven","Kenekaku",R.mipmap.seven));
        words.add(new Word("Eight","kawinta",R.mipmap.eight));
        words.add(new Word("Nine","wo'e",R.mipmap.nine));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);


    }
}
