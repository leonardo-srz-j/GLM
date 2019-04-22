package edu.qc.seclass.glm.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import edu.qc.seclass.glm.database.CategoryTable;

public class StoreCategory implements Parcelable {
    private String categoryID;
    private String categoryName;

    //empty constructor
    public StoreCategory() {
    }

    //parcel constructor
    protected StoreCategory(Parcel in) {
        this.categoryID = in.readString();
        this.categoryName = in.readString();
    }

    //data passing constructor
    public StoreCategory(String categoryID, String categoryName) {
        if (categoryID == null) {
            categoryID = UUID.randomUUID().toString();
        }
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    //this method will transform our data into Values we could pass to the DB source
    public ContentValues toValues(){
        //constructor parameter takes the # of columns
        ContentValues values = new ContentValues(2);

        //adding the field values to the ContentValues object
        values.put(CategoryTable.COLUMN_CATEGORY_ID, categoryID);
        values.put(CategoryTable.COLUMN_CATEGORY_NAME, categoryName);

        return values;
    }


    @Override
    public String toString() {
        return "StoreCategory{" +
                "categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.categoryID);
        dest.writeString(this.categoryName);
    }

    public static final Parcelable.Creator<StoreCategory> CREATOR = new Parcelable.Creator<StoreCategory>() {
        @Override
        public StoreCategory createFromParcel(Parcel source) {
            return new StoreCategory(source);
        }

        @Override
        public StoreCategory[] newArray(int size) {
            return new StoreCategory[size];
        }
    };
}
