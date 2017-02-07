package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;

public class NumbersActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    MediaPlayer mediaplayer;

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

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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
                releaseMediaPlayer();
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaplayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResource());
                    mediaplayer.start();

                    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
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
