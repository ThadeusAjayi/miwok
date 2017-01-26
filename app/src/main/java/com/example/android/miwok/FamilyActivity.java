package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father", "әpә",R.mipmap.father));
        words.add(new Word("mother", "әṭa",R.mipmap.mother));
        words.add(new Word("son", "angsi",R.mipmap.son));
        words.add(new Word("daughter", "tune",R.mipmap.daughter));
        words.add(new Word("older brother", "taachi",R.mipmap.obrother));
        words.add(new Word("younger brother", "chalitti",R.mipmap.ybrother));
        words.add(new Word("older sister", "teṭe",R.mipmap.esister));
        words.add(new Word("younger sister", "kolliti",R.mipmap.ysister));
        words.add(new Word("grandmother ", "ama",R.mipmap.grandma));
        words.add(new Word("grandfather", "paapa",R.mipmap.grandpa));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }
}
