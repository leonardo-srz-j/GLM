package edu.qc.seclass.glm.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import edu.qc.seclass.glm.database.ItemTable;

public class StoreItem implements Parcelable {

    private String itemId;
    private String itemName;
    private String categoryID;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    //empty constructor
    public StoreItem() {
    }

    //parcel constructor
    protected StoreItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.categoryID = in.readString();
    }

    //data passing constructor
    public StoreItem(String itemId, String itemName, String categoryID) {
        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }
        this.itemId = itemId;
        this.itemName = itemName;
        this.categoryID = categoryID;
    }

    //this method will transform our data into Values we could pass to the DB source
    public ContentValues toValues(){
        //constructor parameter takes the # of columns
        ContentValues values = new ContentValues(3);

        //adding the field values to the ContentValues object
        values.put(ItemTable.COLUMN_ID,itemId);
        values.put(ItemTable.COLUMN_NAME,itemName);
        values.put(ItemTable.COLUMN_CATEGORY_ID,categoryID);
        return values;
    }


    @Override
    public String toString() {
        return "StoreItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", categoryID='" + categoryID + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.categoryID);
    }

    public static final Parcelable.Creator<StoreItem> CREATOR = new Parcelable.Creator<StoreItem>() {
        @Override
        public StoreItem createFromParcel(Parcel source) {
            return new StoreItem(source);
        }

        @Override
        public StoreItem[] newArray(int size) {
            return new StoreItem[size];
        }
    };
}
