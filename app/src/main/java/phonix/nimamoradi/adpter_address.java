package phonix.nimamoradi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adpter_address extends RecyclerView.Adapter<adpter_address.view> {
      ArrayList<modeldata_item_address> addresses;
      Context context;
    Boolean Flag;
click click;
    int A;
public void setonclick(click click)
{
    this.click=click;
}

    public adpter_address(ArrayList<modeldata_item_address> addresses, Context context) {
        this.context = context;
        this.addresses = addresses;
        A=0;
        Flag=false;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custome_address, parent, false);
        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
        modeldata_item_address address23=addresses.get(position);
        holder.txt_place.setText(address23.getProvince()+"."+address23.getCity()+"."+address23.getAddress());
//        if  (address23.isImageChanged()){
//            holder.imageView21.setImageResource(R.drawable.place_ok);
//            address23.setImageChanged(false);
//        }else{
//            address23.setImageChanged(true);
//        }

          if (address23.isFlg())
          {
              holder.imageView21.setImageResource(R.drawable.place_ok);
          }else {
              holder.imageView21.setImageResource(R.drawable.place_not);
          }
//        if (!Flag)
//        {
//            Flag=true;
//
//            address23.setImageChanged(true);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               click.data(position);
//                if (A==0)
//                {
//                    A=position;
//
//                }else {
//                  get
//                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }


    public static  class view extends RecyclerView.ViewHolder {
        TextView txt_place;
        ImageView imageView21;
        public view(@NonNull View itemView) {
            super(itemView);
            txt_place=itemView.findViewById(R.id.txt_place);
            imageView21=itemView.findViewById(R.id.imageView21);

        }
    }
    public interface  click{
        public  void data(int pos);
    }
}
