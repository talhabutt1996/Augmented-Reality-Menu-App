package com.example.augmentedreality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    ViewPager viewPager;

    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    SearchView txt_SearchText;
    MyAdapter myAdapter;


    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        mRecyclerView= (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Menu.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        viewPager = (ViewPager)findViewById(R.id.slider);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        txt_SearchText = (SearchView) findViewById(R.id.txt_search);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Items..... :)");

        myFoodList = new ArrayList<>();


        myAdapter = new MyAdapter(Menu.this, myFoodList);

        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              myFoodList.clear();
              for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {

                  FoodData foodData = itemSnapshot.getValue(FoodData.class);
                  foodData.setKey(itemSnapshot.getKey());

                  myFoodList.add(foodData);

              }

                  myAdapter.notifyDataSetChanged();
                  progressDialog.dismiss();

           }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
              progressDialog.dismiss();

          }
      });

        txt_SearchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                filter(s.toString());

                return false;
            }
        });




    }


    private void filter(String text) {

        ArrayList<FoodData> filterList = new ArrayList<>();
        for (FoodData item: myFoodList){

            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item) ;
            }
        }

        myAdapter.filteredList(filterList);

    }


    public void btn_Back(View view) {

        Intent i = new Intent(Menu.this, MainActivity.class);

        finish();
        startActivity(i);
    }
}
