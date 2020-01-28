package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter_sabadkharid extends RecyclerView.Adapter<adapter_sabadkharid.view> {
    ArrayList<modeldata_sabadkharid> list;
    Context context;
    int wi,he;
    click click;
public void data(click click)
{
    this.click=click;
}
    public adapter_sabadkharid(ArrayList<modeldata_sabadkharid> list, Context context,int wi,int he) {
        this.wi=wi;
        this.he=he;
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.layout_cuustome_sabadkharid,parent,false);

       return new view(view);
    }
    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
        modeldata_sabadkharid sabadkharid=list.get(position);
        holder.textView_name.setText(sabadkharid.getName().trim());
        holder.textView_unit_price.setText("قیمت محصول: "+sabadkharid.getPrice()+" ریال");
        holder.textView_off.setText("تخفیف: "+sabadkharid.getOff().trim()+" ریال");
        holder.textView_count.setText(sabadkharid.getCount());
        holder.textView_sum.setText("مجموع: "+sabadkharid.getTotal().trim()+" ریال");
//        holder.imageView.setLayoutParams(new ConstraintLayout.LayoutParams(wi/5,wi/5));
//        holder.imageView.requestLayout();
        Picasso.with(context).load("http://fonixmall.com/admin/upload/"+sabadkharid.getIma()).into(holder.imageView);
        holder.imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.data("A",position,sabadkharid);
            }
        });
        holder.imageView_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.data("B",position,sabadkharid);
            }
        });
        holder.Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.data("C",position,sabadkharid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class view extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView_add;
        ImageView imageView_mines;
        TextView textView_count;
        TextView textView_name;
        TextView textView_unit_price;
        TextView textView_off;
        TextView textView_sum;
        ImageView Close;
        public view(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.circleImageView);
            Close=itemView.findViewById(R.id.imageView19);
            imageView_add=itemView.findViewById(R.id.imageView17);
            imageView_mines=itemView.findViewById(R.id.imageView18);
            textView_count=itemView.findViewById(R.id.textView32);
            textView_name=itemView.findViewById(R.id.textView36);
            textView_unit_price=itemView.findViewById(R.id.textView35);
            textView_off=itemView.findViewById(R.id.textView37);
            textView_sum=itemView.findViewById(R.id.textView38);
        }

    }
    public interface  click{
        public void  data(String s,int d,modeldata_sabadkharid sabadkharid2);
    }
}
