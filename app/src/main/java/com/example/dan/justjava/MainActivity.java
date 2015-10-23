package com.example.dan.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Variable for number of Coffees
    int quantity = 0;
    // Variable for total price of order
    int price = 0;

    // Creates Checkboxes and booleans to store whether whipped cream or chocolate is selected
    CheckBox isWhippedBox;
    CheckBox isChocolateBox;
    boolean isWhipped;
    boolean isChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when to calculate price of order
     */
    public int calculatePrice() {
        // Init base price
        int price = 5;

        // If Whipped cream added, add $1
        if (isWhipped) price += 1;

        // If Chocolate added, add $2
        if (isChocolate) price += 2;

        // Calculate and return final price
        return quantity * price;
    }


    /**
     * This method is called to create the text of the order summary
     */
    public String createSummary(int price) {

        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        return "Name: " + name +
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

        // Assign and store whipped cream checkbox and boolean, checks whether whipped cream is
        // ordered and stores it.
        isWhippedBox = (CheckBox) findViewById(R.id.boxWhipped);
        isWhipped = isWhippedBox.isChecked();

        // Assign and store choco checkbox and boolean, checks whether choco is
        // ordered and stores it.
        isChocolateBox = (CheckBox) findViewById(R.id.boxChocolate);
        isChocolate = isChocolateBox.isChecked();

        price = calculatePrice();
        displayMessage(createSummary(price));
    }

    /**
     * This method is called when the email order button is clicked.
     * Instead of displaying the order, it sends the order through email
     */
    public void submitOrderEmail(View view) {

        // Assign and store whipped cream checkbox and boolean, checks whether whipped cream is
        // ordered and stores it.
        isWhippedBox = (CheckBox) findViewById(R.id.boxWhipped);
        isWhipped = isWhippedBox.isChecked();

        // Assign and store choco checkbox and boolean, checks whether choco is
        // ordered and stores it.
        isChocolateBox = (CheckBox) findViewById(R.id.boxChocolate);
        isChocolate = isChocolateBox.isChecked();

        price = calculatePrice();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        intent.putExtra(Intent.EXTRA_TEXT, createSummary(price));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
        if (quantity <= 99) {
            quantity++;
            display(quantity);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Can't order more than 100!", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    // Method for increasing quantity
    public void quantityDecrease(View view) {
        if (quantity >= 1) {
            quantity--;
            display(quantity);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Can't order negative coffees!", Toast.LENGTH_LONG);
            toast.show();
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
