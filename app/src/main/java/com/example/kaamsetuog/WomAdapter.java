package com.example.kaamsetuog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.List;

public class WomAdapter extends RecyclerView.Adapter<WomAdapter.ViewHolder> {

    Context context;
    List<Worker> list;

    public WomAdapter(Context context, List<Worker> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Worker worker = list.get(position);

        holder.name.setText(worker.name);
        holder.subInfo.setText(worker.work + " • " + worker.experience);
        holder.ratingText.setText(worker.rating + " (" + worker.jobsCount + " jobs)");
        holder.ratingBar.setRating(Float.parseFloat(worker.rating));
        holder.status.setText(worker.status);
        holder.reviewText.setText("\"" + worker.reviewText + "\"");
        holder.reviewerName.setText("— " + worker.reviewerName + ", your contact");

        // Hired by summary - making it clickable
        holder.socialProof.setText(worker.hiredBySummary);
        holder.socialProof.setOnClickListener(v -> {
            showHiredByDialog(worker);
        });

        // Initials
        if (worker.name != null && worker.name.length() >= 2) {
            String[] parts = worker.name.split(" ");
            if (parts.length >= 2) {
                holder.initials.setText(("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase());
            } else {
                holder.initials.setText(worker.name.substring(0, 2).toUpperCase());
            }
        }

        holder.verifiedBadge.setVisibility(worker.isVerified ? View.VISIBLE : View.GONE);

        // Tags
        holder.tagsGroup.removeAllViews();
        if (worker.tags != null) {
            for (String tag : worker.tags) {
                Chip chip = new Chip(context);
                chip.setText(tag);
                chip.setChipMinHeight(0f);
                chip.setTextSize(10f);
                holder.tagsGroup.addView(chip);
            }
        }

        holder.whatsapp.setOnClickListener(v -> {
            String url = "https://wa.me/" + worker.phone;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        });

        holder.call.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + worker.phone));
            context.startActivity(intent);
        });
    }

    private void showHiredByDialog(Worker worker) {
        StringBuilder builder = new StringBuilder();
        if (worker.hiredByFullList != null) {
            for (String contact : worker.hiredByFullList) {
                builder.append("• ").append(contact).append("\n");
            }
        }

        new AlertDialog.Builder(context)
                .setTitle("Contacts who hired " + worker.name)
                .setMessage(builder.toString())
                .setPositiveButton("Close", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, subInfo, ratingText, status, initials, reviewText, reviewerName, socialProof;
        RatingBar ratingBar;
        View verifiedBadge;
        ChipGroup tagsGroup;
        Button whatsapp, call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.workerName);
            subInfo = itemView.findViewById(R.id.workerSubInfo);
            ratingText = itemView.findViewById(R.id.workerRatingText);
            ratingBar = itemView.findViewById(R.id.workerRatingBar);
            status = itemView.findViewById(R.id.workerStatus);
            initials = itemView.findViewById(R.id.workerInitials);
            verifiedBadge = itemView.findViewById(R.id.verifiedBadge);
            reviewText = itemView.findViewById(R.id.reviewText);
            reviewerName = itemView.findViewById(R.id.reviewerName);
            socialProof = itemView.findViewById(R.id.socialProof);
            tagsGroup = itemView.findViewById(R.id.tagsGroup);
            whatsapp = itemView.findViewById(R.id.btnWhatsapp);
            call = itemView.findViewById(R.id.btnCall);
        }
    }
}
