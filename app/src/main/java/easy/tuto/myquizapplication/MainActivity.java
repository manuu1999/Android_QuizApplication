package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, BackgroundMusicService.class));
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BackgroundMusicService.class));
        super.onDestroy();
    }

    public void LaunchNewPage(View view) {
        Intent i = new Intent(this, GameActivity.class);
        String nameInput = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        i.putExtra("userName", nameInput);
        startActivity(i);
    }

}

