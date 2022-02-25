package de.androidcrypto.gfgroomdatabaseown;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StockMovementsRepository {
    //below line is the create a variable for dao and list for all courses.
    private Dao dao;
    private LiveData<List<StockMovementsModal>> allStockMovements;

    //creating a constructor for our variables and passing the variables to it.
    public StockMovementsRepository(Application application) {
        StockMovementsDatabase database = StockMovementsDatabase.getInstance(application);
        dao = database.Dao();
        allStockMovements = dao.getAllStockMovements();
    }

    //creating a method to insert the data to our database.
    public void insert(StockMovementsModal model) {
        new InsertStockMovementsAsyncTask(dao).execute(model);
    }

    //creating a method to update data in database.
    public void update(StockMovementsModal model) {
        new UpdateStockMovementsAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(StockMovementsModal model) {
        new DeleteStockMovementsAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the stock movements.
    public void deleteAllStockMovements() {
        new DeleteAllStockMovementsAsyncTask(dao).execute();
    }

    //below method is to read all the stock movements.
    public LiveData<List<StockMovementsModal>> getAllStockMovements() {
        return allStockMovements;
    }

    //we are creating a async task method to insert new stock movement.
    private static class InsertStockMovementsAsyncTask extends AsyncTask<StockMovementsModal, Void, Void> {
        private Dao dao;

        private InsertStockMovementsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockMovementsModal... model) {
            //below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our stock movements.
    private static class UpdateStockMovementsAsyncTask extends AsyncTask<StockMovementsModal, Void, Void> {
        private Dao dao;

        private UpdateStockMovementsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockMovementsModal... models) {
            //below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete stock movements.
    private static class DeleteStockMovementsAsyncTask extends AsyncTask<StockMovementsModal, Void, Void> {
        private Dao dao;

        private DeleteStockMovementsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockMovementsModal... models) {
            //below line is use to delete our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all stock movements.
    private static class DeleteAllStockMovementsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllStockMovementsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all stock movements.
            dao.deleteAllStockMovements();
            return null;
        }
    }
}
