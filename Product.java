/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the Product class that allows user to create
 * object to be listed.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;

//Implement parcelable to pass data as parcelable object
public class Product implements Parcelable {
    //Instantiate properties of Product item
    private String brand;
    private String model;
    private String type;
    private double price;
    private String city;
    private String state;

    //Instantiate constants to store string as a constant for Json serialization
    private static final String JSON_BRAND = "brand";
    private static final String JSON_MODEL = "model";
    private static final String JSON_TYPE = "type";
    private static final String JSON_PRICE = "price";
    private static final String JSON_CITY = "city";
    private static final String JSON_STATE = "state";

    //Empty constructor to create object
    public Product() {}

    //Getters and Setters of Product object
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String description) {
        this.type = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //toString method that builds string for display in cardview.
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return "Brand: " + brand + "\t Model: " + model + "\n" +
                "Location: " + city + ", " + state + "\n" +
                "Type: " + type + "\n" +
                "Price: " + nf.format(price) + "\n";
    }

    //Product constructor that accepts JSONObject for building serializable Product object.
    public Product(JSONObject jo) throws JSONException {
        brand = jo.getString(JSON_BRAND);
        model = jo.getString(JSON_MODEL);
        type = jo.getString(JSON_TYPE);
        price = jo.getDouble(JSON_PRICE);
        city = jo.getString(JSON_CITY);
        state = jo.getString(JSON_STATE);
    }

    //Method to convert Product object to JSONObject by passing in constants set above as keys
    //and Product properties and values. Returns object.
    public JSONObject convertToJSON() throws JSONException {
        JSONObject jo = new JSONObject();

        jo.put(JSON_BRAND, brand);
        jo.put(JSON_MODEL, model);
        jo.put(JSON_TYPE, type);
        jo.put(JSON_PRICE, price);
        jo.put(JSON_CITY, city);
        jo.put(JSON_STATE, state);

        return jo;
    }

    //Method to allow creation of Product object by reading from passed in Parcel
    protected Product(Parcel in) {
        brand = in.readString();
        model = in.readString();
        type = in.readString();
        price = in.readDouble();
        city = in.readString();
        state = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Write object to Parcel
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeString(type);
        dest.writeDouble(price);
        dest.writeString(city);
        dest.writeString(state);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
