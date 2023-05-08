package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView PassStatusTextView;
    TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        PassStatusTextView = findViewById(R.id.PassStatusTextView);
        scoreTextView = findViewById(R.id.ScoreTextView);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        String passStatus = intent.getStringExtra("passStatus");

        PassStatusTextView.setText("Pass Status: " + passStatus);
        scoreTextView.setText("Score: " + score);
    }

    public void restartQuiz(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void changePlayer(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


