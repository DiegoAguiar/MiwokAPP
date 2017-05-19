package com.example.android.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilysFragment extends Fragment {

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

    public FamilysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father","Әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("Mother","Әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("Son","Angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("Daughter","Tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("Older brother","Taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("Younger brother","Chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("Older sister","Teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("Younger sister","Kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("Grandmother","Ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("Grandfather","Paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(),words,R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idPronunciaAudio = words.get(position).getPronunciaAudio();
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mAudioFocusListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(),idPronunciaAudio);
                    Toast.makeText(getActivity(),"Tocando Pronuncia",Toast.LENGTH_SHORT).show();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.start();
                }


            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer(){
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
            Log.i("PhrasesActivity","Recurso Media Player liberado");

        }
    }

}
