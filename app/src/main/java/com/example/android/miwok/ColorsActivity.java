package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.STREAM_MUSIC;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mediaplayer;
    AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaplayer.pause();                   }
            else if (focusChange == AUDIOFOCUS_GAIN) {
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            } else if (focusChange == AUDIOFOCUS_GAIN) {
                mediaplayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red", "weṭeṭṭi",R.mipmap.red,R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.mipmap.myellow,R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә",R.mipmap.dyellow,R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki",R.mipmap.green,R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki",R.mipmap.brown,R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi",R.mipmap.grey,R.raw.color_gray));
        words.add(new Word("black", "kululli",R.mipmap.black,R.raw.color_black));
        words.add(new Word("white", "kelelli",R.mipmap.white,R.raw.color_white));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,STREAM_MUSIC,AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioResource());
                    mediaplayer.start();

                    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                        }
                    });
                }


            }
        });

    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaplayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaplayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
