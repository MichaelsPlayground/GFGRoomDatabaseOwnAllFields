package de.androidcrypto.gfgroomdatabaseown;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // source: https://github.com/ChaitanyaMunje/GFG-Room-Database
    // tutorial: https://www.geeksforgeeks.org/how-to-perform-crud-operations-in-room-database-in-android/

    //creating a variables for our recycler view.
    private RecyclerView stockMovementsRV;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing our variable for our recycler view and fab.
        stockMovementsRV = findViewById(R.id.idRVStockMovements);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new course and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, NewStockMovementActivity.class);
                addStockMovementActivityResultLauncher.launch(intent);
            }
        });

        //setting layout manager to our adapter class.
        stockMovementsRV.setLayoutManager(new LinearLayoutManager(this));
        stockMovementsRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final StockMovementsRVAdapter adapter = new StockMovementsRVAdapter();
        //setting adapter class for recycler view.
        stockMovementsRV.setAdapter(adapter);
        //passing a data from view modal.
        viewmodal = new ViewModelProvider(this).get(ViewModal.class);
        //below line is use to get all the courses from view modal.
        viewmodal.getAllStockMovements().observe(this, new Observer<List<StockMovementsModal>>() {
            @Override
            public void onChanged(List<StockMovementsModal> models) {
                //when the data is changed in our models we are adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        //below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Stock movement deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                //below line is use to attact this to recycler view.
                        attachToRecyclerView(stockMovementsRV);
        //below line is use to set item click listner for our item of recycler view.
        adapter.setOnItemClickListener(new StockMovementsRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StockMovementsModal model) {
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(MainActivity.this, NewStockMovementActivity.class);
                // todo append
                intent.putExtra(NewStockMovementActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewStockMovementActivity.EXTRA_STOCK_NAME, model.getStockName());
                intent.putExtra(NewStockMovementActivity.EXTRA_STOCK_ISIN, model.getStockIsin());
                intent.putExtra(NewStockMovementActivity.EXTRA_DATE_UNIX, model.getDateUnix());
                //below line is to start a new activity and adding a edit stock movement constant.
                editStockMovementActivityResultLauncher.launch(intent);
            }
        });
    }

    // ersatz für deprecated startActivityForResult
    ActivityResultLauncher<Intent> addStockMovementActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        // todo append
                        String stockName = data.getStringExtra(NewStockMovementActivity.EXTRA_STOCK_NAME);
                        String stockIsin = data.getStringExtra(NewStockMovementActivity.EXTRA_STOCK_ISIN);
                        String dateUnix = data.getStringExtra(NewStockMovementActivity.EXTRA_DATE_UNIX);
// String date, String dateUnix, String stockName, String stockIsin,  String direction,  String amountEuro,  String numberShares, String bank,  String note
                        StockMovementsModal model = new StockMovementsModal("", dateUnix, stockName, stockIsin, "", "", "", "", "", "", "", "","", "");
                        viewmodal.insert(model);
                        Toast.makeText(getBaseContext(), "Stock movement saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    ActivityResultLauncher<Intent> editStockMovementActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();

                        int id = data.getIntExtra(NewStockMovementActivity.EXTRA_ID, -1);
                        if (id == -1) {
                            Toast.makeText(getBaseContext(), "Stock movement can't be updated", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // todo append
                        String stockName = data.getStringExtra(NewStockMovementActivity.EXTRA_STOCK_NAME);
                        String stockIsin = data.getStringExtra(NewStockMovementActivity.EXTRA_STOCK_ISIN);
                        String dateUnix = data.getStringExtra(NewStockMovementActivity.EXTRA_DATE_UNIX);
                        StockMovementsModal model = new StockMovementsModal("", dateUnix, stockName, stockIsin, "", "", "", "", "", "", "", "", "", "");
                        model.setId(id);
                        viewmodal.update(model);
                        Toast.makeText(getBaseContext(), "Stock movement updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });

}