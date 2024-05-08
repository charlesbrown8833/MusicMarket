/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the Serializer that saves and loads
 * data to/from a file.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    //Instantiate filename and context.
    private String mFileName;
    private Context mContext;

    //Constructor that accepts parameters for filename and context of activity to create object
    // and sets values of arguments passed in.
    public JSONSerializer(String fileName, Context context) {
        mFileName = fileName;
        mContext = context;
    }

    //Save method that accepts a List as a parameter to save to file.
    public void save(List<Product> products) throws IOException, JSONException {
        //Instantiate new JSONArray
        JSONArray jsonArray = new JSONArray();

        //Iterate through list and add each product, when converted to Json, to JSONArray.
        for (Product product : products)
            jsonArray.put(product.convertToJSON());

        //Create writer, use try to open OutputStream and open the file output of the passed in context
        // and writes to the file in Private mode. Assigns OutputStream to write object and writes
        //JSONArray to the passed in file. Uses finally to check if writer is not null and closes
        //if not.
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    //Load method that checks for existing file and throws exceptions if problem arises. Creates
    //BufferedReader, checks if null(closes if not), uses InputStream to accept file to context,
    //uses try to open Stream and builds jsonString to append each read line from file to jsonString.
    //Creates JSONArray and assigns to tokener that accepts each jsonString and moves to next value.
    //Iterates through JSONArray and adds each item in array to product list.
    public ArrayList<Product> load() throws IOException, JSONException {
        ArrayList<Product> productList = new ArrayList<Product>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for (int i = 0; i < jsonArray.length(); i++) {
                productList.add(new Product(jsonArray.getJSONObject(i)));
            }
        }
        catch (FileNotFoundException e) {}
        finally {
            if (reader != null)
                reader.close();
        }
        return productList;
    }

}
