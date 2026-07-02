package com.example.kaamsetuog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_BOT = 1;
    private static final int VIEW_TYPE_USER = 2;

    private List<ChatItem> chatList;

    public ChatAdapter(List<ChatItem> chatList) {
        this.chatList = chatList;
    }

    @Override
    public int getItemViewType(int position) {
        return chatList.get(position).isBot() ? VIEW_TYPE_BOT : VIEW_TYPE_USER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_bot_v2, parent, false);
            return new BotViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_user_v2, parent, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatItem item = chatList.get(position);
        if (holder instanceof BotViewHolder) {
            ((BotViewHolder) holder).msgText.setText(item.getMessage());
        } else if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).msgText.setText(item.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView msgText;
        BotViewHolder(@NonNull View itemView) {
            super(itemView);
            msgText = itemView.findViewById(R.id.msgText);
        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView msgText;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            msgText = itemView.findViewById(R.id.msgText);
        }
    }

    public static class ChatItem {
        private String message;
        private boolean isBot;

        public ChatItem(String message, boolean isBot) {
            this.message = message;
            this.isBot = isBot;
        }

        public String getMessage() { return message; }
        public boolean isBot() { return isBot; }
        
        // Compatibility methods for old fragment code if needed
        public String getName() { return isBot ? "Bot" : "User"; }
        public String getLastMsg() { return message; }
        public String getTime() { return ""; }
    }
}