package com.ahmedmakramallah.newsfeedapp;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ahmed on 8/11/2017.
 */

public class Utils {
    private static final String LOG_TAG = null;

    public static List<NewsInfo> GetData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Connection error", e);
        }

        List<NewsInfo> newsInfoList = extractNewsFromJSON(jsonResponse);


        return newsInfoList;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonRespone = null;
        if (url == null) {
            return jsonRespone;
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
                jsonRespone = readFromStream(inputStream);

            } else {
                Log.e(LOG_TAG, "Error response code:  " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, " Error during retrieving the News JSONResponse ", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
        return jsonRespone;
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

    private static List<NewsInfo> extractNewsFromJSON(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }

        List<NewsInfo> newsInfoList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject respone = jsonObject.getJSONObject("response");
            JSONArray resultsArray = respone.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject current = resultsArray.getJSONObject(i);
                String type = current.getString("type");
                String sectionName = current.getString("sectionName");
                String webTitle = current.getString("webTitle");
                String webUrl = current.getString("webUrl");
                String date = current.getString("webPublicationDate");
                date = formatDate(date);
                newsInfoList.add(new NewsInfo(type,  sectionName, date,  webUrl, webTitle));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsInfoList;
    }


    private static String formatDate(String rawDate) {
        String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
        try {
            Date parseJSONDate = jsonFormatter.parse(rawDate);
            String finalDatePattern = "MMM d, yyy";
            SimpleDateFormat  simpleDateFormat = new SimpleDateFormat(finalDatePattern, Locale.US);
            return simpleDateFormat.format(parseJSONDate);
        } catch (ParseException ex) {
            Log.e("QueryUtils", "Error parsing JSON date: ", ex);
            return "";
        }
    }

    private static URL createUrl(String url) {
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error during creating URL  ", e);
        }
        return url1;
    }
}
