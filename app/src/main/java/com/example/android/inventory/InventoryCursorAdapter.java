package com.example.android.inventory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inventory.data.InventoryContract.InventEntry;

import static com.example.android.inventory.R.id.price;
import static com.example.android.inventory.R.id.quantity;

/**
 * Created by gurkaran on 05-03-2017.
 */

public class InventoryCursorAdapter extends CursorAdapter {
    private final InventoryActivity activity;

    public InventoryCursorAdapter(InventoryActivity context, Cursor c) {
        super(context, c, 0 );
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView prodName = (TextView) view.findViewById(R.id.name);
        TextView prodQuantity = (TextView) view.findViewById(quantity);
        ImageView left = (ImageView) view.findViewById(R.id.left);
        TextView prodPrice = (TextView) view.findViewById(price);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        String name = cursor.getString(cursor.getColumnIndex(InventEntry.COLUMN_INVENT_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventEntry.COLUMN_INVENT_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(InventEntry.COLUMN_INVENT_PRICE));

        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(InventEntry.COLUMN_INVENT_IMAGE))));

        prodName.setText(name);
        prodQuantity.setText(String.valueOf(quantity));
        prodPrice.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(InventEntry._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(id);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnSale(id,
                        quantity);
            }
        });
    }
}
