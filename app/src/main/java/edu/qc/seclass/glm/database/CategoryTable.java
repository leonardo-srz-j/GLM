package edu.qc.seclass.glm.database;

public class CategoryTable {
    public static final String TABLE_CATEGORY = "Categories";
    public static final String COLUMN_CATEGORY_ID = "CategoryId";
    public static final String COLUMN_CATEGORY_NAME = "CategoryName";

    public static final String [] ALL_COLUMNS = {COLUMN_CATEGORY_ID,COLUMN_CATEGORY_NAME};

    public static final String SQL_CREATE =
            "CREATE TABLE "+TABLE_CATEGORY+ "("
                    +COLUMN_CATEGORY_ID+" TEXT PRIMARY KEY,"
                    + COLUMN_CATEGORY_NAME +" TEXT NOT NULL"+");";


    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_CATEGORY;

}
