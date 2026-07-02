package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    Context context;
    List<Worker> list;
    private OnRemoveClickListener removeClickListener;

    public interface OnRemoveClickListener {
        void onRemoveClick(int position);
    }

    public WorkerAdapter(Context context, List<Worker> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnRemoveClickListener(OnRemoveClickListener listener) {
        this.removeClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_worker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Worker worker = list.get(position);
        holder.name.setText(worker.name);
        holder.rating.setText(worker.rating);
        holder.exp.setText(worker.experience);
        holder.image.setImageResource(worker.image != 0 ? worker.image : R.drawable.profile);

        if (removeClickListener != null) {
            holder.removeBtn.setVisibility(View.VISIBLE);
            holder.removeBtn.setOnClickListener(v -> {
                removeClickListener.onRemoveClick(position);
            });
        } else {
            holder.removeBtn.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WorkerDetailsActivity.class);
            intent.putExtra("worker", worker);
            context.startActivity(intent);
        });

        // Update card width based on LayoutManager
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            if (context instanceof CategoryActivity || context instanceof MoreCategoriesActivity) {
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                // If it's in HomeFragment's horizontal scroll, use fixed width
                params.width = (int) (250 * context.getResources().getDisplayMetrics().density);
            }
            holder.itemView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, removeBtn;
        TextView name, rating, exp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.workerImage);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            name = itemView.findViewById(R.id.workerName);
            rating = itemView.findViewById(R.id.workerRating);
            exp = itemView.findViewById(R.id.workerExp);
        }
    }
}