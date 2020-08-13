package com.example.khulnauniversityphonebookonlinebeta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class TeacherAdapter extends FirebaseRecyclerAdapter<Teacher, TeacherAdapter.TeacherViewHolder> {


    public TeacherAdapter(@NonNull FirebaseRecyclerOptions<Teacher> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherViewHolder holder, final int position, @NonNull Teacher model) {

        holder.name.setText(model.getName());
        holder.post.setText(model.getPost());
        holder.discipline.setText(model.getDiscipline());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());
        Picasso.get().load(model.getProfilePic()).into(holder.profilePic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String visit_user_id =  getRef(position).getKey();
            }
        });

    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher, parent, false);

        return  new TeacherViewHolder(view);
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder{

        TextView name, post, discipline, email, phone;
        ImageView profilePic ;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            post = itemView.findViewById(R.id.post);
            discipline = itemView.findViewById(R.id.discipline);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            profilePic = itemView.findViewById(R.id.profilePic);
        }
    }
}
