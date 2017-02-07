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

public class FamilyActivity extends AppCompatActivity {

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

                releaseMediaPlayer();
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,STREAM_MUSIC,AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(FamilyActivity.this,word.getmAudioResource());
                    mediaplayer.start();

                    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
