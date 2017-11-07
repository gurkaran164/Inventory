package com.example.android.inventory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.inventory.data.InventoryContract.InventEntry;

/**
 * Created by gurkaran on 05-03-2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(InventEntry.CREATE_TABLE_INVENTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertItem(InventoryProvider item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventEntry.COLUMN_INVENT_NAME, item.getProductName());
        values.put(InventEntry.COLUMN_INVENT_SUPPLIER, item.getSuppliername());
        values.put(InventEntry.COLUMN_INVENT_PRICE, item.getPrice());
        values.put(InventEntry.COLUMN_INVENT_QUANTITY, item.getQuantity());
        values.put(InventEntry.COLUMN_INVENT_IMAGE, item.getImage());
        long id = db.insert(InventEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventEntry._ID,
                InventEntry.COLUMN_INVENT_NAME,
                InventEntry.COLUMN_INVENT_SUPPLIER,
                InventEntry.COLUMN_INVENT_PRICE,
                InventEntry.COLUMN_INVENT_QUANTITY,
                InventEntry.COLUMN_INVENT_IMAGE
        };
        Cursor cursor = db.query(
                InventEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventEntry._ID,
                InventEntry.COLUMN_INVENT_NAME,
                InventEntry.COLUMN_INVENT_SUPPLIER,
                InventEntry.COLUMN_INVENT_PRICE,
                InventEntry.COLUMN_INVENT_QUANTITY,
                InventEntry.COLUMN_INVENT_IMAGE
        };
        String selection = InventEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                InventEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventEntry.COLUMN_INVENT_QUANTITY, quantity);
        String selection = InventEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(InventEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(InventEntry.COLUMN_INVENT_QUANTITY, newQuantity);
        String selection = InventEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(InventEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
