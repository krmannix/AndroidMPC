package com.globalappinitiative.gai_summer_project;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.os.Build;

public class MainActivity extends Activity {
	
	Context context = this;
	String[] gridColorList  = {"#ee3090b0",
            				   "#ffd4c0FF",
            				   "#ffffc0cb",
            				   "#ffff3232",
            				   "#ffe3b333",
            				   "#ff398eb5",
            				   "#ffff77aa",
            				   "#ff6d6d6d",
            				   "#ffa5682a"};
	GridView grid;
	SoundPool soundPool;
	int[] sounds = {R.raw.sound1,
				  R.raw.sound2,
				  R.raw.sound3,
				  R.raw.sound4,
				  R.raw.sound5,
				  R.raw.sound6,
				  R.raw.sound7,
				  R.raw.sound8,
				  R.raw.sound9};
	int[] soundIDs = new int[9];
	AudioManager audioManager;
	SeekBar bar;
	float rate = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		
		// Load Sounds
		for (int i = 0; i < 9; i++) {
			soundIDs[i] = soundPool.load(this, sounds[i], 1);
		}		
		
		grid = (GridView) findViewById(R.id.gridView);
		grid.setAdapter(new CustomGridAdapter(context, gridColorList, grid));
		
		bar = (SeekBar) findViewById(R.id.bar);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// Get volume
				float volume = (float) (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)/audioManager
				          .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
				
				// Take position, and play that sound
				soundPool.play(soundIDs[position], 1f, 1f, 1, 0, rate);
			}
		});
		
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekbar, int value, boolean fromUser) {
				rate = (float) (value/50.0); // The higest we can get is 2
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
	}

}
