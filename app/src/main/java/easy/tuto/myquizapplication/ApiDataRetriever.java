package easy.tuto.myquizapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiDataRetriever {

    public static void retrieveData(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = "https://opentdb.com/api.php?amount=6&category=9&difficulty=easy&type=multiple";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }
}


