package edu.qc.seclass.glm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.qc.seclass.glm.data.StoreCategoryDataProvider;
import edu.qc.seclass.glm.model.StoreCategory;
import edu.qc.seclass.glm.model.StoreItem;

public class DBSource {
   // String[] itemTypes = new String[]{"Fruits", "Dairy", "Alcohol", "Snacks", "Beverages", "Vegetable", "Meats"};
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDBHelper;
    List<StoreCategory> StoreCategoryList = StoreCategoryDataProvider.StoreCategoryList;


    public DBSource(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void open(){
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close(){
        mDBHelper.close();
    }



    public StoreItem createStoreItem (StoreItem storeItem){
        ContentValues values = storeItem.toValues();
        mDatabase.insert(ItemTable.TABLE_ITEMS, null, values);
        return storeItem;
    }

    public void seedDatabase(){
        long numItems = getCategoryCount();
        Toast.makeText(mContext, "Data count!"+ numItems, Toast.LENGTH_SHORT).show();
        if (numItems == 0) {
            //Toast.makeText(this.getContext(), "Data inserted!", Toast.LENGTH_SHORT).show();
            for (StoreCategory category :
                    StoreCategoryList) {
                try {
                    createStoreCategory(category);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }//end of if statement
    }


    public List<StoreCategory> getAllCategories(){
        ArrayList<StoreCategory> StoreCategories = new ArrayList<>();
        Cursor cursor = mDatabase.query(CategoryTable.TABLE_CATEGORY,CategoryTable.ALL_COLUMNS,
                null,null,null,null,null);

        while(cursor.moveToNext()){
               StoreCategory storeCategory = new StoreCategory();
               storeCategory.setCategoryID(cursor.getString(
                       cursor.getColumnIndex(CategoryTable.COLUMN_CATEGORY_ID)));
               StoreCategories.add(storeCategory);
        }
        return StoreCategories;
    }

    public StoreCategory createStoreCategory (StoreCategory storeCategory){
        ContentValues values = storeCategory.toValues();
        mDatabase.insert(CategoryTable.TABLE_CATEGORY, null, values);
        return storeCategory;
    }

    public long getCategoryCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CategoryTable.TABLE_CATEGORY);
    }

}
