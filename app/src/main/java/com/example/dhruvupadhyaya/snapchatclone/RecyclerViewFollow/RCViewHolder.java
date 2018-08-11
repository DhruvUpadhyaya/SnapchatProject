package com.example.dhruvupadhyaya.snapchatclone.RecyclerViewFollow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dhruvupadhyaya.snapchatclone.R;

public class RCViewHolder extends RecyclerView.ViewHolder{

    public TextView mEmail;
    public Button mFollow;

    public RCViewHolder(View viewItem){
        super(viewItem);
        mEmail = viewItem.findViewById(R.id.email);
        mFollow = viewItem.findViewById(R.id.follow);
    }

}
