package com.softrunapps.paginatedrecyclerviewapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softrunapps.paginatedrecyclerview.PaginatedAdapter;

public class PaginationAdapter extends PaginatedAdapter<User, PaginationAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.text_view_username);
            email = itemView.findViewById(R.id.text_view_email);
        }

        public void render(User user) {
            username.setText(user.getUsername());
            email.setText(user.getUsername());
        }
    }
}
