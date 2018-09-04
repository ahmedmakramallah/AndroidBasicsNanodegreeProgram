package com.ahmedmakramallah.booklistapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 8/10/2017.
 */

public class Utils {

    private static final String LOG_TAG = null;

    public static List<Book> fetchData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Connection error", e);
        }

        List<Book> books = extractFeatureFromJson(jsonResponse);

        return books;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "ERROR during fetch the book results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Book> extractFeatureFromJson(String BooksJSON) {
        if (TextUtils.isEmpty(BooksJSON)) {
            return null;
        }
        List<Book> books = new ArrayList<>();
        try {
            String authors = "Author N/A";
            JSONObject baseJsonResponse = new JSONObject(BooksJSON);
            int j = 0;
            if (baseJsonResponse.has("items")) {
                JSONArray results = baseJsonResponse.getJSONArray("items");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject currentobj = results.getJSONObject(i);
                    JSONObject volumeInfo = currentobj.getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");
                    if (volumeInfo.has("authors")) {
                        JSONArray authorsArray = volumeInfo.getJSONArray("authors");
                        authors = getAuthors(authorsArray);
                    }
                    books.add(new Book(title, authors));
                }
            } else {
                return books;
            }
        } catch (JSONException e) {
            Log.e("Utils", "Error during fetch Json results", e);
        }
        return books;
    }

    public static String getAuthors(JSONArray authorsList) throws JSONException {
        String authorsListInString = null;
        if (authorsList.length() == 0) {
            return null;
        }
        for (int i = 0; i < authorsList.length(); i++) {
            if (i == 0) {
                authorsListInString = authorsList.getString(0);
            } else {
                authorsListInString += ", " + authorsList.getString(i);
            }
        }

        return authorsListInString;
    }

    private static URL createUrl(String stringUrl) {
        URL murl = null;
        try {
            murl = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error during creating URL ", e);
        }
        return murl;
    }
}
