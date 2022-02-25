package de.androidcrypto.gfgroomdatabaseown;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {
    //creating a new variable for course repository.
    private StockMovementsRepository repository;
    //below line is to create a variable for live data where all the courses are present.
    private LiveData<List<StockMovementsModal>> allStockMovements;

    //constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new StockMovementsRepository(application);
        allStockMovements = repository.getAllStockMovements();
    }


    //below method is use to insert the data to our repository.
    public void insert(StockMovementsModal model) {
        repository.insert(model);
    }

    //below line is to update data in our repository.
    public void update(StockMovementsModal model) {
        repository.update(model);
    }

    //below line is to delete the data in our repository.
    public void delete(StockMovementsModal model) {
        repository.delete(model);
    }

    //below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteAllStockMovements();
    }

    //below method is to get all the courses in our list.
    public LiveData<List<StockMovementsModal>> getAllStockMovements() {
        return allStockMovements;
    }
}
