package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class adapter_circle_slider extends ArrayAdapter {
    int i;
    Context context;

    public adapter_circle_slider(@NonNull Context context, int resource,int i) {
        super(context, resource,i);
        this.i=i;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       if (convertView==null)
       {
           convertView= LayoutInflater.from(context).inflate(R.layout.custome_circle_slider, parent,false);
       }
        ImageView imageView=convertView.findViewById(R.id.img_circle_slider);
       return convertView;
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }
}
