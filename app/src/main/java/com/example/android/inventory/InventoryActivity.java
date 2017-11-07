package com.example.android.inventory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.android.inventory.data.InventoryContract.InventEntry;
import com.example.android.inventory.data.InventoryDbHelper;
import com.example.android.inventory.data.InventoryProvider;

public class InventoryActivity extends AppCompatActivity {

    InventoryDbHelper dbHelper;
    InventoryCursorAdapter mCursoradapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        dbHelper = new InventoryDbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, InventoryEditor.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        mCursoradapter = new InventoryCursorAdapter(this, cursor);
        listView.setAdapter(mCursoradapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void insertProduct() {
        InventoryProvider Beach_Beauty = new InventoryProvider(
                "Beach Beauty",
                "Aquarian Bev",
                "180",
                10,
                "android.resource://com.example.android.inventory/drawable/logo");
        dbHelper.insertItem(Beach_Beauty);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    private int deleteAllProducts() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.delete(InventEntry.TABLE_NAME, null, null);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCursoradapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, InventoryEditor.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        mCursoradapter.swapCursor(dbHelper.readStock());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertProduct();
                mCursoradapter.swapCursor(dbHelper.readStock());
                break;
            case R.id.action_delete_all_entries:
              deleteAllProducts();
                mCursoradapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

}
