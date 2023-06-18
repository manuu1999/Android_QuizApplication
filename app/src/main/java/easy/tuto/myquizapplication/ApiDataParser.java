package easy.tuto.myquizapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;

public class ApiDataParser {

    public static void parseData(String response, String[] questions, String[][] choices, String[] correctAnswers) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                String question = Html.fromHtml(item.getString("question")).toString();
                questions[i] = question;

                JSONArray choicesArray = item.getJSONArray("incorrect_answers");
                choices[i][0] = Html.fromHtml(choicesArray.getString(0)).toString();
                choices[i][1] = Html.fromHtml(choicesArray.getString(1)).toString();
                choices[i][2] = Html.fromHtml(choicesArray.getString(2)).toString();
                choices[i][3] = Html.fromHtml(item.getString("correct_answer")).toString();

                String correctAnswer = Html.fromHtml(item.getString("correct_answer")).toString();
                correctAnswers[i] = correctAnswer;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}