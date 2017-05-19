package com.example.android.miwok;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends Activity {

    private MediaPlayer mMediaPlayer = null;
    private String LABEL = getClass().getName();
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        }
    };
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red","Weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green","Chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown","Takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray","Topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black","Kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White","Kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Dusty yellow","Topiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("Mustard yellow","Chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idPronunciaAudio = words.get(position).getPronunciaAudio();
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mAudioFocusListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,idPronunciaAudio);
                    Toast.makeText(ColorsActivity.this,"Tocando Pronuncia",Toast.LENGTH_SHORT).show();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.start();
                }


            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer(){
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
            Log.i(LABEL,"Recurso Media Player liberado");
        }
    }

}
