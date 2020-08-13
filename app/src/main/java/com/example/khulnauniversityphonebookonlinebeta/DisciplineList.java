package com.example.khulnauniversityphonebookonlinebeta;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class DisciplineList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  DatabaseReference DisciplineRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_list);

        DisciplineRef = FirebaseDatabase.getInstance().getReference().child("Discipline");

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Discipline> options =
                new FirebaseRecyclerOptions.Builder<Discipline>().setQuery(DisciplineRef, Discipline.class).build();

        FirebaseRecyclerAdapter<Discipline, DisciplineViewHolder > adapter =
                new FirebaseRecyclerAdapter<Discipline, DisciplineViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull DisciplineViewHolder holder, final int position, @NonNull Discipline model) {

                        holder.discipline_name.setText(model.getDiscipline_name());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String discipline = getRef(position).getKey();
                                Intent intent = new Intent(DisciplineList.this, TeacherList.class);
                                intent.putExtra("discipline", discipline);
                                startActivity(intent);

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public DisciplineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discipline, parent, false);
                        DisciplineViewHolder disciplineViewHolder = new DisciplineViewHolder(view);
                        return disciplineViewHolder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


    public static class DisciplineViewHolder extends RecyclerView.ViewHolder
    {

        TextView discipline_name;

        public DisciplineViewHolder(@NonNull View itemView) {
            super(itemView);

            discipline_name = itemView.findViewById(R.id.discipline_name);
        }
    }
}