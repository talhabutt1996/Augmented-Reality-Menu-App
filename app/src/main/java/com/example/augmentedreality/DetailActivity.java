package com.example.augmentedreality;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DetailActivity extends AppCompatActivity {


    TextView foodDescription;
    ImageView foodImage;
    TextView foodName;
    String key="";
    String imageUrl="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        foodName = (TextView)findViewById(R.id.txtName);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            //foodImage.setImageResource(mBundle.getInt("Image"));

            foodDescription.setText(mBundle.getString("Description"));
            foodName.setText(mBundle.getString("Name"));

            key= mBundle.getString("keyValue");
            imageUrl= mBundle.getString("Image");

            Glide.with(this).load(mBundle.getString("Image")).into(foodImage);


        }


    }

    public void btnDeleteRecipe(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipe");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(DetailActivity.this,"Dish Deleted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),AdminPanel.class));
                finish();
            }
        });

    }

    public void btnCamera(View view) {
        Intent i = getPackageManager().getLaunchIntentForPackage("com.ArMenu.ar");
        startActivity(i);
        finish();

    }
}
