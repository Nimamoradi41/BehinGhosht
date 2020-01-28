package phonix.nimamoradi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import phonix.nimamoradi.showProduct_api.img;

public class Viewpager_adapter2 extends PagerAdapter {
    Context context;
    ArrayList<img> strings;
    View view_lavout;
    LayoutInflater   inflater;
    int with;
    int height;
    public Viewpager_adapter2(Context context, ArrayList<img> strings, int with, int height)
    {
        this.context=context;
        this.strings=strings;
        this.with=with;
        this.height=height;
    }
    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
         View view=LayoutInflater.from(context).inflate(R.layout.layout_custom_slider2,container,false);
         ImageView imageView=view.findViewById(R.id.img_slider_new2);
//        Toast.makeText(context, String.valueOf(), Toast.LENGTH_SHORT).show();
        Log.i("SKVMSDLJBDJLBN",String.valueOf(strings.get(position).getImg()));
         Picasso.with(context).load("http://fonixmall.com/admin/upload/"+strings.get(position).getImg()).into(imageView);
//        try {
//            Picasso.get().load(strings.get(position))
//                    .fit()
//                    .error(R.drawable.shpe_slider)
//                    .centerCrop()
//                    .into(imageView);
//        }catch (Exception e)
//        {
//
//        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
