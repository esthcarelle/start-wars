package com.example.starwars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter  extends RecyclerView.Adapter<HeroAdapter.HeroHolder> {
   private ArrayList<Datum> mData;
   private Context mActivity;

    public HeroAdapter(ArrayList<Datum> mData, Context mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public HeroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_hero,parent,false);
      return new HeroHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroHolder holder, final int position) {

        Datum hero=mData.get(position);

        holder.heroName.setText(hero.getName());

        holder.heroId.setText(Integer.toString(hero.getId()));
        Glide.with(mActivity)
                .load(hero.getImage())
                .into(holder.heroImage);
//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClickListener(View view, int position) {
//
//
//                String name=hero.getName();
//                String price=models.get(position).getPrice();
//
//
//
//                Intent intent=new Intent(context, anotherActivity.class);
//                intent.putExtra("Menu",name);
//                intent.putExtra("price",price);
//                intent.putExtra("imagez",bytes);
//                context.startActivity(intent);
//
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mData.get(position).getName();
                String image=mData.get(position).getImage();
                String id=Integer.toString(mData.get(position).getId());
                List<String> aff=mData.get(position).getAffiliations();


                Intent intent=new Intent(mActivity,Details.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("image",image);
                intent.putStringArrayListExtra("aff",(ArrayList<String>)aff);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        if(this.mData==null)

        return 0;
        return mData.size();
    }

    public class HeroHolder extends RecyclerView.ViewHolder{


        ImageView heroImage;
        TextView heroName;
        TextView heroId;
        public HeroHolder(@NonNull View itemView) {
            super(itemView);
            heroImage=(ImageView) itemView.findViewById(R.id.heroImage);
            heroName=(TextView) itemView.findViewById(R.id.heroName);
            heroId=(TextView)itemView.findViewById(R.id.heroId);
        }
    }
}
