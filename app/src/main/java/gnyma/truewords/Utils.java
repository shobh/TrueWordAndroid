package gnyma.truewords;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 12/26/2018.
 */

public class Utils {

    static void storeLocally(String string, String sFileName) {
        File file = new File(App.getContext().getFilesDir(),"mydir");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(string);
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Quotes> readFromFile() {

        String ret = "";
        InputStream inputStream = null;
        try {
            String file_name=App.getContext().getFilesDir() + "/mydir/data.json";
            inputStream = new FileInputStream(new File(file_name));

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!performCheckForValidity(ret)) return null;

        ArrayList<Quotes> ar = new ArrayList<Quotes>();
        try {
            JSONObject obj = new JSONObject(ret);
            int count = obj.optInt("count");
            JSONArray arr = obj.optJSONArray("quotes");
            for (int i = 0; i < count ; i++) {
                String author = ((JSONObject)arr.get(i)).optString("author");
                String quote = ((JSONObject)arr.get(i)).optString("quote");
                if (quote.length() < 450) {
                    Quotes item = new Quotes(quote, author);
                    ar.add(item);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.shuffle(ar);

        return ar;
    }

    private static void addToArrayList(JSONObject obj0, ArrayList<String> list) {
        try {
            obj0 = (JSONObject) obj0.optJSONArray("category").get(0);
            int count = obj0.optInt("count");
            JSONArray jar = obj0.optJSONArray("array");
            for (int i = 0; i < count; i++) {
                list.add(((JSONObject)jar.get(i)).optString("quote"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static boolean performCheckForValidity(String data) {
        if (TextUtils.isEmpty(data)) return false;

        try {
            JSONObject obj = new JSONObject(data);
            if (obj.optInt("count") < 1) return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //need to add more check
        return true;
    }



}