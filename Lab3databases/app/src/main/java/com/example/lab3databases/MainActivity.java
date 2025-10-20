package com.example.lab3databases;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView productId;
    EditText productName, productPrice;
    Button addBtn, findBtn, deleteBtn;
    ListView productListView;

    ArrayList<String> productList;
    ArrayAdapter<String> adapter;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();

        productId = findViewById(R.id.productId);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);

        addBtn = findViewById(R.id.addBtn);
        findBtn = findViewById(R.id.findBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        productListView = findViewById(R.id.productListView);

        dbHandler = new MyDBHandler(this);

        addBtn.setOnClickListener(v -> addProduct());

        findBtn.setOnClickListener(v -> findProduct());

        deleteBtn.setOnClickListener(v -> deleteProduct());

        viewProducts(); // started here, understand what the code does from here
    }

    private void viewProducts() {
        productList.clear(); //empties the current list to avoid duplicates
        Cursor cursor = dbHandler.getData();  //pointer that points to a position before the first row
        if (cursor.getCount() == 0) { //checks if the database is empty
            Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) { // move the pointer to the next row
                String name = cursor.getString(1);
                double price = cursor.getDouble(2);
                productList.add(String.format(Locale.getDefault(), "%s ($%.2f)", name, price));
            }
        }
        cursor.close();  //we are done

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
            productListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void addProduct() {
        String name = productName.getText().toString();
        String priceStr = productPrice.getText().toString();

        if (name.trim().isEmpty() || priceStr.trim().isEmpty()) {
            Toast.makeText(this, "Please enter both name and price.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            Product product = new Product(0, name, price);
            dbHandler.addProduct(product);

            clearInputFields();
            Toast.makeText(this, "Product Added.", Toast.LENGTH_SHORT).show();
            viewProducts();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format.", Toast.LENGTH_SHORT).show();
        }
    }

    private void findProduct() {
        String nameToFind = productName.getText().toString();

        if (nameToFind.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a product name to find.", Toast.LENGTH_SHORT).show();
            return;
        }

        Product foundProduct = dbHandler.findProduct(nameToFind);

        if (foundProduct != null) {
            productId.setText(String.valueOf(foundProduct.getId()));
            productName.setText(foundProduct.getProductName());
            productPrice.setText(String.valueOf(foundProduct.getProductPrice()));
            Toast.makeText(this, "Product Found.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product not found.", Toast.LENGTH_SHORT).show();
            clearInputFields();
        }
    }

    private void deleteProduct() {
        String nameToDelete = productName.getText().toString();

        if (nameToDelete.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a product name to delete.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean wasDeleted = dbHandler.deleteProduct(nameToDelete);

        if (wasDeleted) {
            Toast.makeText(this, "Product Deleted.", Toast.LENGTH_SHORT).show();
            clearInputFields();
            viewProducts();
        } else {
            Toast.makeText(this, "Product not found. No item was deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputFields() {
        productId.setText("Product ID");
        productName.setText("");
        productPrice.setText("");
    }
}
