package de.androidcrypto.gfgroomdatabaseown;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao {

    //below method is use to add data to database.
    @Insert
    void insert(StockMovementsModal model);

    //below method is use to update the data in our database.
    @Update
    void update(StockMovementsModal model);

    //below line is use to delete a specific stock movement in our database.
    @Delete
    void delete(StockMovementsModal model);

    //on below line we are making query to delete all stock movements from our databse.
    @Query("DELETE FROM stock_movements_table")
    void deleteAllStockMovements();

    //below line is to read all the stock movements from our database.
    //in this we are ordering our movements in ascending order with our dateUnix.
    @Query("SELECT * FROM stock_movements_table ORDER BY dateUnix ASC")
    LiveData<List<StockMovementsModal>> getAllStockMovements();

}
