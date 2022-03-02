package de.androidcrypto.gfgroomdatabaseown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class StockMovementsRVAdapter extends ListAdapter<StockMovementsModal, StockMovementsRVAdapter.ViewHolder> {
    //creating a variable for on item click listner.
    private OnItemClickListener listener;

    //creating a constructor class for our adapter class.
    StockMovementsRVAdapter() {
        super(DIFF_CALLBACK);
    }

    //creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<StockMovementsModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<StockMovementsModal>() {
        @Override
        public boolean areItemsTheSame(StockMovementsModal oldItem, StockMovementsModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(StockMovementsModal oldItem, StockMovementsModal newItem) {
            //below line is to check the course name, description and course duration.
            return oldItem.getStockIsin().equals(newItem.getStockIsin()) &&
                    oldItem.getStockName().equals(newItem.getStockName()) &&
                    oldItem.getDateUnix().equals(newItem.getDateUnix());
            // todo add other fields to proove they are identical
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //below line is use to inflate our layout file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_movements_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //below line of code is use to set data to each item of our recycler view.
        // todo get all other fields to binding
        StockMovementsModal model = getCourseAt(position);
        holder.stockMovementsStockNameTV.setText(model.getStockName());
        holder.stockMovementsStockIsinTV.setText(model.getStockIsin());
        holder.stockMovementsDateUnixTV.setText(model.getDateUnix());
    }

    //creating a method to get course modal for a specific position.
    public StockMovementsModal getCourseAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //view holder class to create a variable for each view.
        // todo append all other fields
        TextView stockMovementsStockNameTV, stockMovementsStockIsinTV, stockMovementsDateUnixTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing each view of our recycler view.
            stockMovementsStockNameTV = itemView.findViewById(R.id.idTVStockMovementsStockName);
            stockMovementsStockIsinTV = itemView.findViewById(R.id.idTVStockMovementsStockIsin);
            stockMovementsDateUnixTV = itemView.findViewById(R.id.idTVStockMovementsDateUnix);
            //adding on click listner for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //inside on click listner we are passing position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(StockMovementsModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}


