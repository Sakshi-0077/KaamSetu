package com.example.kaamsetuog;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class MsgFragment extends Fragment {

    public MsgFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ChatAdapter.ChatItem> chatList = new ArrayList<>();
        chatList.add(new ChatAdapter.ChatItem("Hello! How can I help you today?", true));
        chatList.add(new ChatAdapter.ChatItem("Help me to understand this App", false));
        chatList.add(new ChatAdapter.ChatItem("Sure! Please tell me what do you want to know?", true));
        chatList.add(new ChatAdapter.ChatItem("How can i add my details as Worker in this App?", false));
        chatList.add(new ChatAdapter.ChatItem("Thanks for the details.", true));

        ChatAdapter adapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}