package com.example.dhruvupadhyaya.snapchatclone.RecyclerViewFollow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhruvupadhyaya.snapchatclone.R;

import java.util.List;

public class RCadapter extends RecyclerView.Adapter<RCViewHolder> {

    private List<UsersObject> usersList;
    private Context context;

    public RCadapter(List<UsersObject> usersList,Context context){
        this.usersList = usersList;
        this.context = context;

    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_followers_item,null);
        RCViewHolder rcv = new RCViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        holder.mEmail.setText(usersList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
