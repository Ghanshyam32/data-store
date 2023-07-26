package com.ghanshyam.datastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailEmail, detailPhone;
    ImageView detailImage;
    FloatingActionButton deleteBtn;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detailName);
        detailEmail = findViewById(R.id.detailEmail);
        detailPhone = findViewById(R.id.detailPhone);
        detailImage = findViewById(R.id.detailImage);
        deleteBtn = findViewById(R.id.delete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailName.setText(bundle.getString("Name"));
            detailEmail.setText(bundle.getString("Email"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            detailPhone.setText(bundle.getString("Phone"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data Store");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                // Check if key is not null before proceeding with delete
                if (key != null) {
                    StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(DetailActivity.this, "Error: Key is null", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}