package il.ac.tcb.st.secondhw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PL_RecyclerViewAdpater extends RecyclerView.Adapter<PL_RecyclerViewAdpater.MyViewHolder> {
    Context context;
    ArrayList<MyPicks> list;
    public  PL_RecyclerViewAdpater(Context context, ArrayList<MyPicks> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PL_RecyclerViewAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_holder,parent,false);
        return new PL_RecyclerViewAdpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PL_RecyclerViewAdpater.MyViewHolder holder, int position) {
        holder.imageView.setImageBitmap(list.get(position).picutreLoaded);
        holder.first.setText(list.get(position).name);
        holder.last.setText(list.get(position).last);
        holder.country.setText(list.get(position).country);
        holder.city.setText(list.get(position).city);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView first,last,country,city;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageHolder);
            first = itemView.findViewById(R.id.firstNameholder);
            last = itemView.findViewById(R.id.lastNameholder);
            country = itemView.findViewById(R.id.countryHolder);
            city = itemView.findViewById(R.id.cityHolder);
        }
    }
}
