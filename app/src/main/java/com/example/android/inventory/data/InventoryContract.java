package com.example.android.inventory.data;

import android.provider.BaseColumns;

/**
 * Created by gurkaran on 05-03-2017.
 */

public class InventoryContract {

    private InventoryContract(){  };
    public static final String CONTENT_AUTHORITY = "com.example.android.inventory";

    public static final class InventEntry implements BaseColumns {

        public final static String TABLE_NAME="inventory";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_INVENT_NAME="name";
        public static final String COLUMN_INVENT_SUPPLIER="supplier";
        public static final String COLUMN_INVENT_PRICE="price";
        public static final String COLUMN_INVENT_QUANTITY="quantity";
        public static final String COLUMN_INVENT_IMAGE = "image";

        public static final String CREATE_TABLE_INVENTORY = "CREATE TABLE " +
                InventEntry.TABLE_NAME + "(" +
                InventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventEntry.COLUMN_INVENT_NAME + " TEXT NOT NULL," +
                InventEntry.COLUMN_INVENT_SUPPLIER+ " TEXT NOT NULL," +
                InventEntry.COLUMN_INVENT_PRICE + " TEXT NOT NULL," +
                InventEntry.COLUMN_INVENT_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventEntry.COLUMN_INVENT_IMAGE + " TEXT NOT NULL" + ");";
    }
}

