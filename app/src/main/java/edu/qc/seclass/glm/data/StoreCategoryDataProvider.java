package edu.qc.seclass.glm.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.qc.seclass.glm.model.StoreCategory;

public class StoreCategoryDataProvider {
    public static List<StoreCategory> StoreCategoryList;
    public static Map<String, StoreCategory> StoreCategoryMap;

    //this is all static data so when you call this class
    //this data is already there
    static {
        StoreCategoryList = new ArrayList<StoreCategory>();
        StoreCategoryMap = new HashMap<>();

        addItem(new StoreCategory(null, "Fruits"));
        addItem(new StoreCategory(null, "Dairy"));
        addItem(new StoreCategory(null, "Alcohol"));
        addItem(new StoreCategory(null, "Snacks"));
        addItem(new StoreCategory(null, "Beverages"));
        addItem(new StoreCategory(null, "Vegetable"));
        addItem(new StoreCategory(null, "Meats"));
    }



    private static void addItem(StoreCategory category) {
        StoreCategoryList.add(category);
        StoreCategoryMap.put(category.getCategoryID(), category);
    }
}
