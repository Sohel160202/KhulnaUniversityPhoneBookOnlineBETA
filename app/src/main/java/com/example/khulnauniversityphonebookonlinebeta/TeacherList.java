package com.example.khulnauniversityphonebookonlinebeta;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class TeacherList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference TeacherRef;
    private String discipline;
    private Button DisciplineTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        discipline = getIntent().getExtras().get("discipline").toString();
        TeacherRef = FirebaseDatabase.getInstance().getReference().child(discipline);

        DisciplineTab = (Button) findViewById(R.id.Discipline_NAME);
        DisciplineTab.setText(discipline);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Data Loading", Toast.LENGTH_SHORT).show();

        FirebaseRecyclerOptions<Teacher> options =
                new FirebaseRecyclerOptions.Builder<Teacher>().setQuery(TeacherRef,Teacher.class).build();

        FirebaseRecyclerAdapter <Teacher, TeacherAdapter.TeacherViewHolder> adapter =
                new FirebaseRecyclerAdapter<Teacher, TeacherAdapter.TeacherViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull TeacherAdapter.TeacherViewHolder holder, final int position, @NonNull Teacher model) {
                        holder.name.setText(model.getName());
                        holder.post.setText(model.getPost());
                        holder.discipline.setText(model.getDiscipline());
                        holder.email.setText(model.getEmail());
                        holder.phone.setText(model.getPhone());
                        Picasso.get().load(model.getProfilePic()).into(holder.profilePic);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String TeacherRef = getRef(position).getKey();

                                Intent intent = new Intent(TeacherList.this, TeacherProfile.class);
                                intent.putExtra("discipline", discipline);
                                intent.putExtra("TeacherRef", TeacherRef);
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public TeacherAdapter.TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher, parent, false);

                        return  new TeacherAdapter.TeacherViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    public static class TeacherViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, post, discipline, email, phone;
        ImageView profilePic ;

        public TeacherViewHolder(@NonNull View itemView)
        {
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