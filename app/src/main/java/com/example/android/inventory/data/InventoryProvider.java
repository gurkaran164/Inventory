package com.example.android.inventory.data;

/**
 * Created by gurkaran on 05-03-2017.
 */

public class InventoryProvider{
    private final String productName;
        private final String price;
        private final String supplier;
        private final int quantity;
        private final String image;

        public InventoryProvider(String productName,String suplier, String price, int quantity, String image) {
            this.productName = productName;
            this.price = price;
            this.supplier=suplier;
            this.quantity = quantity;
            this.image = image;
        }

        public String getSuppliername() {
            return supplier;
        }
        public String getProductName() {

            return productName;
        }

        public String getPrice()
        {
            return price;
        }

        public int getQuantity() {

            return quantity;
        }

        public String getImage() {

            return image;
        }
        @Override
        public String toString() {
            return "StockItem{" +
                    "productName='" + productName +'\''+
                    ",supplier='"+ supplier+'\''+
                    ", price='" + price +'\''+
                    ", quantity=" + quantity +
                    '}';
        }
}
