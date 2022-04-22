package com.example.augmentedreality;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder>{


    private Context mContext;
    private List <FoodData> myFoodList;
    private int lastPosition = -1;

    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_row_item, viewGroup,false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {

        Glide.with(mContext)
                .load(myFoodList.get(i).getItemimage())
                .into(foodViewHolder.imageView);


        foodViewHolder.mtitle.setText(myFoodList.get(i).getItemName());
        foodViewHolder.mdescription.setText(myFoodList.get(i).getItemDescription());
        foodViewHolder.mprice.setText(myFoodList.get(i).getItemprice());

        foodViewHolder.mcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Image", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemimage());
                intent.putExtra("Description", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Name", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("keyValue", myFoodList.get(foodViewHolder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);
            }
        });

        setAnimation(foodViewHolder.imageView,i);

    }

    public void setAnimation (View viewToAnimate, int position){

        if (position > lastPosition){

            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {

        myFoodList = filterList;
        notifyDataSetChanged();
    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView  mtitle, mdescription, mprice;
    CardView mcardView;

    public FoodViewHolder( View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mtitle = itemView.findViewById(R.id.tvTitle);
        mdescription = itemView.findViewById(R.id.tvDescription);
        mprice = itemView.findViewById(R.id.tvPrice);

        mcardView = itemView.findViewById(R.id.myCardView);



    }
}