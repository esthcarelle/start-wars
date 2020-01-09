package com.example.starwars;

import android.app.Activity;
import android.content.Context;
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

public class HeroAdapter  extends RecyclerView.Adapter<HeroAdapter.HeroHolder> {
   private ArrayList<Hero> mData;
   private Context mActivity;

    public HeroAdapter(ArrayList<Hero> mData, Context mActivity) {
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
    public void onBindViewHolder(@NonNull HeroHolder holder, int position) {

        Hero hero=mData.get(position);

        holder.heroName.setText(hero.getName());
        holder.heroId.setText(hero.getId());
        Glide.with(mActivity)
                .load(hero.getImageUrl())
                .into(holder.heroImage);
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
