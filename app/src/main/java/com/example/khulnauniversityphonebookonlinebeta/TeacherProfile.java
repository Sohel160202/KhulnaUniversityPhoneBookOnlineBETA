package com.example.khulnauniversityphonebookonlinebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TeacherProfile extends AppCompatActivity {

    private String TeacherRef, discipline_name;
    private TextView Nname, Npost, Ndiscipline, Nphone, Nemail;
    private ImageView profilePic;
    private DatabaseReference databaseReference;
    private Button CallButton;
    private Intent callIntent ;
    private Context context;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        discipline_name = getIntent().getExtras().get("discipline").toString();
        TeacherRef = getIntent().getExtras().get("TeacherRef").toString();

        Nname = findViewById(R.id.name);
        Npost = findViewById(R.id.post);
        Ndiscipline = findViewById(R.id.discipline);
        Nphone = findViewById(R.id.phone);
        Nemail = findViewById(R.id.email);
        profilePic = findViewById(R.id.profilePic);
        CallButton = findViewById(R.id.CALL);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(discipline_name);

        RetrieveUserInfo();
    }

    private void RetrieveUserInfo()
    {
        databaseReference.child(TeacherRef).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String post = snapshot.child("post").getValue().toString();
                    String discipline = snapshot.child("discipline").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    String phone = snapshot.child("phone").getValue().toString();
                    String profilePicLink = snapshot.child("profilePic").getValue().toString();

                    Picasso.get().load(profilePicLink).into(profilePic);
                    Nname.setText(name);
                    Npost.setText(post);
                    Ndiscipline.setText(discipline);
                    Nphone.setText(phone);
                    Nemail.setText(email);

                    CallButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (ContextCompat.checkSelfPermission(TeacherProfile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(TeacherProfile.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                            }

                                String phonenumber = Nphone.getText().toString();
                                String dial = "tel:" + phonenumber;
                                callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse(dial));
                                startActivity(callIntent);


                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(TeacherProfile.this, "Permission Accepted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TeacherProfile.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}