package phonix.nimamoradi.frags;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import phonix.nimamoradi.modeldata_item_address;

public class adapter_item_address extends RecyclerView.Adapter<adapter_item_address.view> {
    ArrayList<modeldata_item_address> addresses;
    Context context;

    public adapter_item_address(ArrayList<modeldata_item_address> addresses, Context context) {
        this.addresses = addresses;
        this.context = context;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public static class view extends RecyclerView.ViewHolder {
        public view(@NonNull View itemView) {
            super(itemView);
        }
    }
}
