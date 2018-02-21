package com.rodgwizz.cofeeordering;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rodgwizz.justjava.R;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        /**Gets the name from the edit textfield....getText method returns a editable data type
         * which is the converted to string by the to string () method.
         */

        EditText nameField = (EditText) (findViewById(R.id.name));
        String name = nameField.getText().toString();

        //Gets the state of the Whipped cream checkbox if checked or not
        CheckBox whippedCream = (findViewById(R.id.checkbox));
        boolean haswhippedCream = whippedCream.isChecked();

        // Gets the sate of the chocolate checkbox

        CheckBox chocolate = (findViewById(R.id.checkbox2));
        boolean hasChocolate = chocolate.isChecked();

        //Calculates the price of the cofee by calling the calculate price method
        int pricePercup = 5;
        int price = calculatePrice(quantity, pricePercup, haswhippedCream, hasChocolate);

        //Calls the display method and the oder summary
        String priceMessage = creatOrderSummary(price,haswhippedCream,name, hasChocolate);
        displayMessage(priceMessage);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Just Java" + name);
        sendIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(sendIntent);
           }

    //This method calculates price based on the given quantity

    private int calculatePrice (int quantity, int pricePercup, boolean haswhippedCream, boolean hasChocolate) {
        int price = quantity*pricePercup;
        if (hasChocolate) {pricePercup = pricePercup + 2;
            price = quantity*pricePercup; }
        if (haswhippedCream) {pricePercup = pricePercup + 1;
            price = quantity*pricePercup;}
        return price;
    }

    //**Creates order Summary

    public String creatOrderSummary (int price, boolean haswhippedCream , String name, boolean hasChocolate){
        String priceMessage = "Name: "+ name + "\nHas Whipped Cream? " + haswhippedCream +
                "\nHas Chocolate? " + hasChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThankyou Very Much!";
        return (priceMessage);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void incriment(View view) {
      if (quantity==100){
          //shows this error when cups are above 1000
          Toast.makeText(this,"You cannot have more than 100 cups of coffee ", Toast.LENGTH_SHORT).show();
          return;
            }
        quantity = quantity + 1;
      displayQuantity(quantity);
      }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity ==1)
             {
                 //shows this error when cups are below
                 Toast.makeText(this,"You cannot have less than 1 cup of coffee ", Toast.LENGTH_SHORT).show();
                return;
             }
            quantity = quantity -1;
        displayQuantity(quantity);
      }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    // Displays text on the price text view
    private void displayMessage (String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
               priceTextView.setText(message);

    }
    //Sends Order Via email using intents
    public void viewOrder(View view){
        /**Gets the name from the edit textfield....getText method returns a editable data type
         * which is the converted to string by the to string () method.
         */

        EditText nameField = (EditText) (findViewById(R.id.name));
        String name = nameField.getText().toString();

        //Gets the state of the Whipped cream checkbox if checked or not
        CheckBox whippedCream = (findViewById(R.id.checkbox));
        boolean haswhippedCream = whippedCream.isChecked();

        // Gets the sate of the chocolate checkbox

        CheckBox chocolate = (findViewById(R.id.checkbox2));
        boolean hasChocolate = chocolate.isChecked();

        //Calculates the price of the cofee by calling the calculate price method
        int pricePercup = 5;
        int price = calculatePrice(quantity, pricePercup, haswhippedCream, hasChocolate);

        //Calls the display method and the oder summary
        String priceMessage = creatOrderSummary(price,haswhippedCream,name, hasChocolate);
        displayMessage(priceMessage);

    }
}
