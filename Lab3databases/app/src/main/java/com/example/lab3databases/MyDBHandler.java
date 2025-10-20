package com.example.lab3databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PRODUCT_NAME + " TEXT, "
                + COLUMN_PRODUCT_PRICE + " DOUBLE)";
        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns cursor of all products
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Finds a product in the database by its name
     * @return A product object if found, otherwise null
     */
    public Product findProduct(String productName) {
        SQLiteDatabase db = this.getReadableDatabase(); // Opens the databases and makes it read only
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{productName});  // Returns a cursor of the product
        Product product = null;  // Initialize product to null

        if (cursor.moveToFirst()) {
            // Use getColumnIndexOrThrow to get the index of the column
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
            product = new Product(id, name, price);
        }
        cursor.close();
        db.close();
        return product;
    }
    /**
     * Deletes a product from the database by its name
     * Uses the safe db.delete() method
     * @return True if the product was deleted, otherwise false
     */

    public boolean deleteProduct(String productName) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase(); //gets the writable database

        // The '?' prevents SQl injection
        // the delete method returns the number of rows affected
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_PRODUCT_NAME + " = ?", new String[]{productName});
        if (rowsDeleted > 0) {
            result = true;
        }
        db.close();
        return result;

    }
}
