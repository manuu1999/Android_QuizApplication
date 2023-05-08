package easy.tuto.myquizapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiDataParser {



    public static void parseData(String response, String[] questions, String[][] choices, String[] correctAnswers) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                String question = item.getString("question");
                questions[i] = question;

                JSONArray choicesArray = item.getJSONArray("incorrect_answers");
                choices[i][0] = choicesArray.getString(0);
                choices[i][1] = choicesArray.getString(1);
                choices[i][2] = choicesArray.getString(2);
                choices[i][3] = item.getString("correct_answer");

                String correctAnswer = item.getString("correct_answer");
                correctAnswers[i] = correctAnswer;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}