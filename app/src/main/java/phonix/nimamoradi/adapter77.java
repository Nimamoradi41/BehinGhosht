package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import phonix.nimamoradi.search_Api.product;

public class adapter77 extends RecyclerView.Adapter<adapter77.view> {
     ArrayList<product> resuilts;
     Context context;
     int d;
    send_info send_info;
    sell sell;

    public adapter77(ArrayList<product> resuilts, Context context, int cd,send_info send_info) {
        this.resuilts = resuilts;
        this.send_info= send_info;
        this.context = context;
        this.d=cd;
    }
public void click(sell sell)
{
    this.sell=sell;
}
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custome_item_spl, parent, false);
//        int sss=  (int) (d / Resources.getSystem().getDisplayMetrics().density);
//        if (isTablet(context))
//        {
//            view.setLayoutParams(new ConstraintLayout.LayoutParams(d/4, ViewGroup.LayoutParams.WRAP_CONTENT));
//            view.requestLayout();
//        }
          view.setLayoutParams(new LinearLayout.LayoutParams(d/2,d-(d*1/8)));
          view.requestLayout();
          return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {


        holder.Bind(position);
    }
    @Override
    public int getItemCount() {
        return resuilts.size();
    }

    public class  view extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView img_item_spl;
        ImageView imageView;
        TextView Off;
        TextView  Price_main;
        TextView Off_befor;
        TextView txt_price_off34;
        TextView Star;
        ConstraintLayout Con;
        LinearLayout linearLayout;
        Button txtbtnadd;
        public view(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.txt_name_item);
            txtbtnadd=itemView.findViewById(R.id.txtbtnadd);
            Off=itemView.findViewById(R.id.txt_off);
            Price_main=itemView.findViewById(R.id.txt_price);
            Off_befor=itemView.findViewById(R.id.txt_price_off);
            txt_price_off34=itemView.findViewById(R.id.txt_price_off34);
            Star=itemView.findViewById(R.id.txt_star);
            Con=itemView.findViewById(R.id.Con);
            Name=itemView.findViewById(R.id.txt_name_item);
            img_item_spl=itemView.findViewById(R.id.img_item_spl);
        }
        public void Bind(int i)
        {
            product search_resuilt=resuilts.get(i);
            Name.setText(search_resuilt.getName().trim());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send_info.info(search_resuilt);
                }
            });
            Off.setText(search_resuilt.getPercentageOffer().trim()+"% off");
            Price_main.setText(search_resuilt.getPriceAfterOffer().trim());
            Off_befor.setText(search_resuilt.getPrice().trim());
            txt_price_off34.setText(search_resuilt.getPrice().trim());
            Star.setText(search_resuilt.getStar().trim());
            Picasso.with(context).load("http://fonixmall.com/admin/upload/"+search_resuilt.getImg()).into(img_item_spl);
            txtbtnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sell.info(false,search_resuilt);
                }
            });
        }
    }
    public interface send_info{
        public void info(product search_resuilt);
    }
    public interface  sell{
        public void info(Boolean aBoolean,product items);
    }
}
