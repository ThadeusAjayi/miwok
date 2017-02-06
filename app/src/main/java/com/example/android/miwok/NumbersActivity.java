package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("One","Lutti",R.mipmap.one,R.raw.number_one));
        words.add(new Word("Two","Otiiko",R.mipmap.two,R.raw.number_two));
        words.add(new Word("Three","Tolookosu",R.mipmap.three,R.raw.number_three));
        words.add(new Word("Four","Oyiisa",R.mipmap.four,R.raw.number_four));
        words.add(new Word("Five","Massokka",R.mipmap.five,R.raw.number_five));
        words.add(new Word("Six","Temmokka",R.mipmap.six,R.raw.number_six));
        words.add(new Word("Seven","Kenekaku",R.mipmap.seven,R.raw.number_seven));
        words.add(new Word("Eight","kawinta",R.mipmap.eight,R.raw.number_eight));
        words.add(new Word("Nine","wo'e",R.mipmap.nine,R.raw.number_nine));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                MediaPlayer mediaplayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResource());
                mediaplayer.start();
            }
        });


    }
}
