/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mmediaplayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mcompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    };
    AudioManager.OnAudioFocusChangeListener monAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener()
            {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mmediaplayer.pause();
                        mmediaplayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPLayer();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mmediaplayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
       final  ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("father","apa",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","eta",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        WordAdaptor adaptor=new WordAdaptor(this,words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word=words.get(i);
                releaseMediaPLayer();
                int result=mAudioManager.requestAudioFocus(monAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mmediaplayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResource());
                    mmediaplayer.start();
                    mmediaplayer.setOnCompletionListener(mcompletionListener);
                }


            }
        });





    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPLayer();
    }
    private void releaseMediaPLayer() {
        if(mmediaplayer!=null) {
            mmediaplayer.release();
        }
        mmediaplayer=null; mAudioManager.abandonAudioFocus(monAudioFocusChangeListener);
    }
}
