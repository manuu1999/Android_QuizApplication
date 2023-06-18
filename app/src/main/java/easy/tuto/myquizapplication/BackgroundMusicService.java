package easy.tuto.myquizapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {
    private BackgroundMusicPlayer musicPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = BackgroundMusicPlayer.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        musicPlayer.stop();
        musicPlayer.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}