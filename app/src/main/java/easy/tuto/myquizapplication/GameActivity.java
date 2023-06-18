package easy.tuto.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = 6;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    String[] questions = new String[totalQuestion];
    String[][] choices = new String[totalQuestion][4];
    String[] correctAnswers = new String[totalQuestion];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent i = getIntent();
        String userName = i.getStringExtra("userName");
        ((TextView)findViewById(R.id.textView4)).setText("Good luck with your Quiz " + userName + "!");

        // game start

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Question 1 out of " + totalQuestion);

        retrieveDataAndParse();

    }

    private void retrieveDataAndParse() {
        ApiDataRetriever.retrieveData(this,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ApiDataParser.parseData(response, questions, choices, correctAnswers);
                        loadNewQuestion();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                score++;
                // Highlight the correct answer button in green
                highlightCorrectAnswer();
            } else {
                clickedButton.setBackgroundColor(Color.RED);
                // Highlight the wrong answer button in red
                highlightWrongAnswer();
            }

            // Disable all answer buttons to prevent further selection
            ansA.setEnabled(false);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);

            // Change the text of the submit button to "Next Question"
            submitBtn.setText("Next Question");
            submitBtn.setBackgroundColor(Color.parseColor("#8A2BE2"));
            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentQuestionIndex++;
                    loadNewQuestion();
                }
            });
        } else {
            // Choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }
    }


    void loadNewQuestion() {
        if (currentQuestionIndex >= totalQuestion) {
            finishQuiz();
            return;
        }

        totalQuestionsTextView.setText("Question " + (currentQuestionIndex + 1) + " out of " + totalQuestion);

        questionTextView.setText(questions[currentQuestionIndex]);

        // Randomly shuffle the order of the answer choices
        List<String> answerChoices = Arrays.asList(choices[currentQuestionIndex]);
        Collections.shuffle(answerChoices);

        ansA.setText(answerChoices.get(0));
        ansB.setText(answerChoices.get(1));
        ansC.setText(answerChoices.get(2));
        ansD.setText(answerChoices.get(3));

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        submitBtn.setText("Submit");
        submitBtn.setBackgroundColor(Color.parseColor("#8A2BE2"));
        submitBtn.setOnClickListener(this);

        selectedAnswer = ""; // Reset selected answer

        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);
    }



    void highlightWrongAnswer() {
        // Get the index of the wrong answer
        int wrongAnswerIndex = -1;
        for (int i = 0; i < choices[currentQuestionIndex].length; i++) {
            if (choices[currentQuestionIndex][i].equals(selectedAnswer)) {
                wrongAnswerIndex = i;
                break;
            }
        }

        // Highlight the wrong answer button in red
        switch (wrongAnswerIndex) {
            case 0:
                ansA.setBackgroundColor(Color.RED);
                break;
            case 1:
                ansB.setBackgroundColor(Color.RED);
                break;
            case 2:
                ansC.setBackgroundColor(Color.RED);
                break;
            case 3:
                ansD.setBackgroundColor(Color.RED);
                break;
            default:
                break;
        }

        // Highlight the correct answer button in green
        int correctAnswerIndex = -1;
        for (int i = 0; i < choices[currentQuestionIndex].length; i++) {
            if (choices[currentQuestionIndex][i].equals(correctAnswers[currentQuestionIndex])) {
                correctAnswerIndex = i;
                break;
            }
        }

        // Highlight the correct answer button in green
        switch (correctAnswerIndex) {
            case 0:
                ansA.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                ansB.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                ansC.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                ansD.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    void highlightCorrectAnswer() {
        // Highlight the correct answer button in green
        if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            if (selectedAnswer.equals(choices[currentQuestionIndex][0])) {
                ansA.setBackgroundColor(Color.GREEN);
            } else if (selectedAnswer.equals(choices[currentQuestionIndex][1])) {
                ansB.setBackgroundColor(Color.GREEN);
            } else if (selectedAnswer.equals(choices[currentQuestionIndex][2])) {
                ansC.setBackgroundColor(Color.GREEN);
            } else if (selectedAnswer.equals(choices[currentQuestionIndex][3])) {
                ansD.setBackgroundColor(Color.GREEN);
            }
        }
    }

    void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("passStatus", passStatus);
        startActivity(intent);
        finish();
    }

}