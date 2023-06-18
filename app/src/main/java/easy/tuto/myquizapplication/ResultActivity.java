package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView passStatusTextView;
    TextView scoreTextView;
    ImageView gifImageView; // Declare the ImageView for the GIF

    int totalQuestion = 6; // Total number of questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        passStatusTextView = findViewById(R.id.PassStatusTextView);
        scoreTextView = findViewById(R.id.ScoreTextView);
        gifImageView = findViewById(R.id.resultImage); // Initialize the ImageView

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        String passStatus = intent.getStringExtra("passStatus");

        String scoreText = "Score: " + score + " out of " + totalQuestion;
        passStatusTextView.setText("Pass Status: " + passStatus);
        scoreTextView.setText(scoreText);

        if (passStatus.equals("Passed")) {
            gifImageView.setImageResource(R.drawable.happy);
        } else if (passStatus.equals("Failed")) {
            gifImageView.setImageResource(R.drawable.sad);
        }

        gifImageView.setVisibility(View.VISIBLE);
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

