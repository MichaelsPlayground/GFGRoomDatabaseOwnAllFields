package de.androidcrypto.gfgroomdatabaseown;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewStockMovementActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText stockNameEdt, stockIsinEdt, dateUnixEdt, direction, numberShares, amountEuro;
    // todo append other fields
    private Button stockMovementBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_ID";
    public static final String EXTRA_STOCK_NAME =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_STOCK_NAME";
    public static final String EXTRA_STOCK_ISIN =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_STOCK_ISIN";
    public static final String EXTRA_DATE_UNIX =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_DATE_UNIX";
    public static final String EXTRA_DIRECTION =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_DIRECTION";
    public static final String EXTRA_NUMBER_SHARES =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_NUMBER_SHARES";
    public static final String EXTRA_AMOUNT_EURO =
            "de.androidcrypto.gfgroomdatabaseown.EXTRA_AMOUNT_EURO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stock_movement);
        //initializing our variables for each view.
        // todo append
        stockNameEdt = findViewById(R.id.idEdtStockName);
        stockIsinEdt = findViewById(R.id.idEdtStockIsin);
        dateUnixEdt = findViewById(R.id.idEdtDateUnix);
        direction = findViewById(R.id.idEdtDirection);
        numberShares = findViewById(R.id.idEdtNumberShares);
        amountEuro = findViewById(R.id.idEdtAmountEuro);

        stockMovementBtn = findViewById(R.id.idBtnSaveStockMovement);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // todo append
            //if we get id for our data then we are setting values to our edit text fields.
            stockNameEdt.setText(intent.getStringExtra(EXTRA_STOCK_NAME));
            stockIsinEdt.setText(intent.getStringExtra(EXTRA_STOCK_ISIN));
            dateUnixEdt.setText(intent.getStringExtra(EXTRA_DATE_UNIX));
            direction.setText(intent.getStringExtra(EXTRA_DIRECTION));
            numberShares.setText(intent.getStringExtra(EXTRA_NUMBER_SHARES));
            amountEuro.setText(intent.getStringExtra(EXTRA_AMOUNT_EURO));
        }
        //adding on click listner for our save button.
        stockMovementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                // todo append other fields
                String stockName = stockNameEdt.getText().toString();
                String stockIsin = stockIsinEdt.getText().toString();
                String dateUnix = dateUnixEdt.getText().toString();
                String directionV = direction.getText().toString();
                String numberSharesV = numberShares.getText().toString();
                String amountEuroV = amountEuro.getText().toString();
                // todo check other fields
                if (stockName.isEmpty() || stockIsin.isEmpty() || dateUnix.isEmpty()) {
                    Toast.makeText(NewStockMovementActivity.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our movement.
                //saveStockMovement(stockName, stockIsin, dateUnix);
                saveStockMovement(stockName, stockIsin, dateUnix, directionV, numberSharesV, amountEuroV);
            }
        });

    }

    // todo append
    private void saveStockMovementOld(String stockName, String stockIsin, String dateUnix) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(EXTRA_STOCK_NAME, stockName);
        data.putExtra(EXTRA_STOCK_ISIN, stockIsin);
        data.putExtra(EXTRA_DATE_UNIX, dateUnix);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Stock Movement has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

    private void saveStockMovement(String stockName, String stockIsin, String dateUnix, String direction, String numberShares, String amountEuro) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(EXTRA_STOCK_NAME, stockName);
        data.putExtra(EXTRA_STOCK_ISIN, stockIsin);
        data.putExtra(EXTRA_DATE_UNIX, dateUnix);
        data.putExtra(EXTRA_DIRECTION, direction);
        data.putExtra(EXTRA_NUMBER_SHARES, numberShares);
        data.putExtra(EXTRA_AMOUNT_EURO, amountEuro);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Stock Movement has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

}