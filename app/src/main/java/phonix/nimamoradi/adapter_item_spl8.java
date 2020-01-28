package phonix.nimamoradi;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;
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

public class adapter_item_spl8 extends RecyclerView.Adapter<adapter_item_spl8.view> {
      Context context;
      send_info send_info;
      int d;
      int c;
     Enter enter;

    static ArrayList<modeldate_mainpage_items> items;

    public adapter_item_spl8(Context context, send_info send_info, ArrayList<modeldate_mainpage_items> items2, int s, int c) {
        this.context = context;
        this.send_info = send_info;
        this.items = items2;
        this.d = s;
        this.c = c;
    }

    public void setonclick(Enter enter)
    {
        this.enter=enter;
    }
    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custome_item_spl, parent, false);
        int sss=  (int) (d / Resources.getSystem().getDisplayMetrics().density);
        if (isTablet(context))
        {
            view.setLayoutParams(new ConstraintLayout.LayoutParams((d/2)-(d/10), ((d/2)-(d/10))*2));
            view.requestLayout();
        }
        else {
            view.setLayoutParams(new LinearLayout.LayoutParams(d/2, d));
            view.requestLayout();
        }

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
        ConstraintLayout Con;
        LinearLayout linearLayout;
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
            Con=itemView.findViewById(R.id.Con);
            Off.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
//            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(d/2,d/2));
//            linearLayout.requestLayout();
//            Con.setLayoutParams(new ConstraintLayout.LayoutParams(sss/2,sss/2));
//            imageView.setBackgroundResource(R.drawable.undraw_romantic_getaway_k2mf);
        }
        public void Bind(int Pos)
        {
              modeldate_mainpage_items modeldate_mainpage_items=items.get(Pos);
              if (isTablet(context))
              {
                  Picasso.with(context).
                          load("http://fonixmall.com/admin/upload/"+
                                  modeldate_mainpage_items.getIamge_link())
                          .resize((d/2)-(d/10),(d/2)-(d/10)).into(imageView);
              }else {
                  Picasso.with(context).
                          load("http://fonixmall.com/admin/upload/"+
                                  modeldate_mainpage_items.getIamge_link())
                          .resize(d/2,d/2).into(imageView);
              }
             Name.setText(modeldate_mainpage_items.getName().trim());
            Off.setText(modeldate_mainpage_items.getOff().trim()+"% off");
            Price_main.setText(modeldate_mainpage_items.getAfter_off().trim()+" ریال");
            Off_befor.setText(modeldate_mainpage_items.getPrice_first().trim());
            txt_price_off34.setText(modeldate_mainpage_items.getPrice_first().trim());
            Star.setText(modeldate_mainpage_items.getStar().trim());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send_info.info("true",modeldate_mainpage_items);
                }
            });
            txtbtnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enter.data(false,modeldate_mainpage_items);
                }
            });
        }
    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
public interface send_info{
        public void info(String s, modeldate_mainpage_items items);
}
public interface Enter{
        public void data(Boolean aBoolean, modeldate_mainpage_items items);
}
}
