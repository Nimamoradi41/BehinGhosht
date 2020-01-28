package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import phonix.nimamoradi.mainPage_api.cat;
import phonix.nimamoradi.showCat_api.subCat;

public class adapter_subcate_cate extends RecyclerView.Adapter<adapter_subcate_cate.view> {
        ArrayList<subCat> list;
    Context context;
    info info;
     public adapter_subcate_cate(Context context, ArrayList<subCat> list, info info) {
        this.list=list;
        this.context=context;
        this.info=info;
    }
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class  view extends RecyclerView.ViewHolder {
        TextView name;
        public view(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_subcate);
        }
        public  void Bind(int pos)
        {
            subCat s=list.get(pos);
            name.setText(s.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    info.data(s);
                }
            });
        }
    }
    public interface info{
        public void data(subCat subcate);
    }
}
