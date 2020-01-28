package phonix.nimamoradi;

import android.content.Context;
import android.content.res.Configuration;
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

import butterknife.OnClick;
import phonix.nimamoradi.openFestival_api.productList;

public class adapter_item_spl7 extends RecyclerView.Adapter<adapter_item_spl7.view> {
    Context context;
      send_info send_info;
      ArrayList<productList> items;
    int h;
    int d;
      sell sell;

    public adapter_item_spl7(Context context, send_info send_info, ArrayList<productList> items2, int w, int h) {
        this.h = h;
        this.d = w;
        this.context = context;
        this.send_info = send_info;
        this.items = items2;
    }
  public void click(sell sell)
  {
      this.sell=sell;
  }
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custome_item_spl, parent, false);
        if (isTablet(context)) {
            view.setLayoutParams(new ConstraintLayout.LayoutParams(d / 4, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.requestLayout();
        }
        view.setLayoutParams(new LinearLayout.LayoutParams(d / 2, d - (d * 1 / 8)));
        view.requestLayout();

        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
        holder.Bind(position);
    }
    @Override
    public int getItemCount() {
        return items.size();
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

    public class view extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Name;
        TextView Off;
        TextView  Price_main;
        TextView Off_befor;
        TextView txt_price_off34;
        TextView Star;
        Button txtbtnadd;
        public view(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_spl);
            txtbtnadd=itemView.findViewById(R.id.txtbtnadd);
            Name=itemView.findViewById(R.id.txt_name_item);
            Off=itemView.findViewById(R.id.txt_off);
            Price_main=itemView.findViewById(R.id.txt_price);
            Off_befor=itemView.findViewById(R.id.txt_price_off);
            txt_price_off34=itemView.findViewById(R.id.txt_price_off34);
            Star=itemView.findViewById(R.id.txt_star);

        }
        public void Bind(int Pos)
        {
            productList modeldate_mainpage_items=items.get(Pos);
            Picasso.with(context).load("http://fonixmall.com/admin/upload/"+modeldate_mainpage_items.getImg()).into(imageView);
            Name.setText(modeldate_mainpage_items.getName());
            Off.setText(modeldate_mainpage_items.getPercentageOffer()+"% off");
            Price_main.setText(modeldate_mainpage_items.getPriceAfterOffer().trim()+" ریال");
            Off_befor.setText(modeldate_mainpage_items.getPrice());
            txt_price_off34.setText(modeldate_mainpage_items.getPrice());
            Star.setText(modeldate_mainpage_items.getStar());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send_info.info("true",modeldate_mainpage_items);
                }
            });
            txtbtnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sell.data(false,modeldate_mainpage_items);
                }
            });
        }
    }

    public interface send_info{
        public void info(String s, productList items);
    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    public interface sell{
        public void data(Boolean aBoolean,productList items);
    }
}
