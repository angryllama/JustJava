package com.example.dan.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Variable for number of Coffees
    int quantity = 0;
    // Variable for total price of order
    int price = 0;
    // Boolean for whipped cream
    //boolean isWhipped = false;
    // Boolean for whipped cream
    //boolean isChocolate = false;


    /**
     * This method is called when to calculate price of order
     */
    public int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method is called to create the text of the order summary
     */
    public String createSummary(int price) {

        CheckBox isWhippedBox = (CheckBox) findViewById(R.id.boxWhipped);
        boolean isWhipped = isWhippedBox.isChecked();

        CheckBox isChocolateBox = (CheckBox) findViewById(R.id.boxChocolate);
        boolean isChocolate = isChocolateBox.isChecked();

        return "Name: Kunal" +
                "\nAdd Whipped Cream?: " + isWhipped +
                "\nAdd Chocolate?: " + isChocolate +
                "\nQuantity: " + quantity +
                "\nTotal $" + price +
                "\nThank you!";
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //display(quantity);
        //displayPrice(quantity * 5);
        price = calculatePrice();
        displayMessage(createSummary(price));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    // Method for increasing quantity
    public void quantityIncrease(View view) {
        quantity++;
        display(quantity);
    }

    // Method for increasing quantity
    public void quantityDecrease(View view) {
        if (quantity >= 1) {
            quantity--;
            display(quantity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
