/*
 * Copyright 2015 TheShark34
 *
 * This file is part of Swinger.

 * Swinger is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Swinger is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Swinger.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.swinger.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import java.util.Arrays;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * A music player
 *
 * <p>
 *    You can read any musics with this class, pause it,
 *    unpause it, set the volume, etc...
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class Player {

	private double volume = 1.0D;
	private MediaPlayer mediaPlayer;
	private boolean pause = false;
	private ArrayList<File> musics;
	private int music;

	public Player(File[] musics) {
		this(new ArrayList<>(Arrays.asList(musics)));
	}

	public Player(ArrayList<File> musics) {
		this.musics = musics;
		new JFXPanel();
	}

	public void start() {
		try {
			setMusic(musics.get(music).toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if(mediaPlayer != null)
			mediaPlayer.stop();
	}

	public void setMusic(String music) {
		if (music.contains(".DS_Store") || music.contains("Thumbs.db")) {
			next();
			return;
		}
		mediaPlayer = new MediaPlayer(new Media(music));
		mediaPlayer.setVolume(volume);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				next();
			}
		});
		if (!pause)
			mediaPlayer.play();
	}

	public void pause() {
		if (!pause) {
			if (mediaPlayer != null)
				mediaPlayer.pause();
			pause = true;
		} else {
			if (mediaPlayer != null)
				mediaPlayer.play();
			pause = false;
		}
	}

	public void setVolume(double volume) {
		this.volume = volume;
		if (mediaPlayer != null)
			mediaPlayer.setVolume(this.volume);
	}

	public void next() {
		if (mediaPlayer != null)
			mediaPlayer.stop();
		if ((music + 1) < musics.size())
			music++;
		else
			music = 0;
		try {
			setMusic(musics.get(music).toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void previous() {
		if (mediaPlayer != null)
			if (mediaPlayer.getCurrentTime().toSeconds() < 3)
				try {
					mediaPlayer.stop();
					if (music > 0)
						music--;
					else
						music = musics.size() - 1;
					setMusic(musics.get(music).toURI().toURL().toString());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			else
				mediaPlayer.seek(mediaPlayer.getStartTime());
	}

	public boolean isPaused() {
		return this.pause;
	}

	public double getSongLenght() {
		if(mediaPlayer != null)
			return mediaPlayer.getTotalDuration().toMillis();
		else
			return 0;
	}

	public double getCurrentTime() {
		if(mediaPlayer != null)
			return mediaPlayer.getCurrentTime().toMillis();
		else
			return 0;
	}

	public void setCurrentTime(double time) {
		mediaPlayer.seek(new Duration(time));
	}

	public String getStringOfCurrentTime() {
		if(mediaPlayer == null)
			return "0:00";
		int second = (int) mediaPlayer.getCurrentTime().toSeconds();
		while (second >= 60)
			second -= 60;
		int minute = (int) (int) mediaPlayer.getCurrentTime().toMinutes();
		return minute + ":" + (String.valueOf(second).length() == 1 ? "0" : "")
				+ second;
	}

	public String getStringOfRemainingTime() {
		if(mediaPlayer == null)
			return "0:00";
		int second = (int) (mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer
				.getCurrentTime().toSeconds());
		while (second >= 60)
			second -= 60;
		int minute = (int) (mediaPlayer.getTotalDuration().toMinutes() - mediaPlayer
				.getCurrentTime().toMinutes());
		return minute + ":" + (String.valueOf(second).length() == 1 ? "0" : "")
				+ second;
	}

}
