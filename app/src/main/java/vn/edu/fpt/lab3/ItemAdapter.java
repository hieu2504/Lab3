package vn.edu.fpt.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fpt.lab3.Model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>  {

    private Context context;
    private List<Item> itemList;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {

        holder.tvTitle.setText(itemList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+itemList.get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);

        }
    }

}
