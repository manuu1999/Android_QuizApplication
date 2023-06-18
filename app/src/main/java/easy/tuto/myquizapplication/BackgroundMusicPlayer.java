package easy.tuto.myquizapplication;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundMusicPlayer {
    private static BackgroundMusicPlayer instance;
    private MediaPlayer mediaPlayer;

    private BackgroundMusicPlayer(Context context) {
        mediaPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.backgroundmusic);
        mediaPlayer.setLooping(true);
    }

    public static synchronized BackgroundMusicPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new BackgroundMusicPlayer(context);
        }
        return instance;
    }

    public void start() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public void release() {
        mediaPlayer.release();
        instance = null;
    }
}
