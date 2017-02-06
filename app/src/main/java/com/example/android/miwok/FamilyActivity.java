package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father", "әpә",R.mipmap.father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.mipmap.mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.mipmap.son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.mipmap.daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.mipmap.obrother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.mipmap.ybrother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.mipmap.esister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.mipmap.ysister,R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama",R.mipmap.grandma,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.mipmap.grandpa,R.raw.family_grandfather));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                MediaPlayer mediaplayer = MediaPlayer.create(FamilyActivity.this,word.getmAudioResource());
                mediaplayer.start();
            }
        });
    }
}
