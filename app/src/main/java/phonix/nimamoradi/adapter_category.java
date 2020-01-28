package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.OnClick;

public class adapter_category extends RecyclerView.Adapter<adapter_category.view> {
    Context context;

    public adapter_category(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custome_category, parent, false);
        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @OnClick({R.id.img_category, R.id.txt_category})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_category:
                break;
            case R.id.txt_category:
                break;
        }
    }

    public static class view extends RecyclerView.ViewHolder {
        ImageView imageView;
        public view(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_category);
        }
    }


}
