package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_sabadekharid_header extends RecyclerView.Adapter<adapter_sabadekharid_header.view> {
    Context context;

    public adapter_sabadekharid_header()
    {

    }
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return  null;
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class view extends RecyclerView.ViewHolder {
        public view(@NonNull View itemView) {
            super(itemView);
        }
    }
}
