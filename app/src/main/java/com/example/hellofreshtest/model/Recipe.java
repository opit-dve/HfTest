package com.example.hellofreshtest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by srd on 9/23/2015.
 */
public class Recipe implements Parcelable {

    public String id;
    public String name;
    public String headline;
    public String description;
    public String image;
    public String calories;
    public String carbos;
    public String fats;
    public String proteins;
    public String country;
    public int difficulty;
    public int favorites;
    public boolean highlighted;
    public ArrayList<String> ingredients;
    public ArrayList<String> products;
    public ArrayList<String> keywords;

    // custom fields
    public float userRating;
    public boolean favorite;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.headline);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.calories);
        dest.writeString(this.carbos);
        dest.writeString(this.fats);
        dest.writeString(this.proteins);
        dest.writeString(this.country);
        dest.writeInt(this.difficulty);
        dest.writeInt(this.favorites);
        dest.writeByte(highlighted ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.ingredients);
        dest.writeStringList(this.products);
        dest.writeStringList(this.keywords);
        dest.writeFloat(this.userRating);
        dest.writeByte(favorite ? (byte) 1 : (byte) 0);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.headline = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.calories = in.readString();
        this.carbos = in.readString();
        this.fats = in.readString();
        this.proteins = in.readString();
        this.country = in.readString();
        this.difficulty = in.readInt();
        this.favorites = in.readInt();
        this.highlighted = in.readByte() != 0;
        this.ingredients = in.createStringArrayList();
        this.products = in.createStringArrayList();
        this.keywords = in.createStringArrayList();
        this.userRating = in.readFloat();
        this.favorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
