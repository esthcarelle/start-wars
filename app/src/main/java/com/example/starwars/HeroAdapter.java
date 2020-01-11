package com.example.starwars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter  extends RecyclerView.Adapter<HeroAdapter.HeroHolder> implements Filterable {
   private ArrayList<Datum> mData;
    private ArrayList<Datum> mData2;
   private Context mActivity;

    public HeroAdapter(ArrayList<Datum> mData, Context mActivity) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.mData2=mData;
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

//        holder.heroId.setText(Integer.toString(hero.getId()));
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
                List<String> masters=mData.get(position).getMasters();
                List<String> app=mData.get(position).getApprentices();
                String gender=mData.get(position).getGender();
                String height=Integer.toString(mData.get(position).getHeight());
                String birthplace=mData.get(position).getBornLocation();
                String wiki=(mData.get(position).getWiki());
                String homeWorld=mData.get(position).getHomeworld();
                String eyeColor=mData.get(position).getEyeColor();


                Intent intent=new Intent(mActivity,Details.class);
                intent.putExtra("image",image);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("height",height);
                intent.putExtra("birthPlace",birthplace);
                intent.putExtra("wiki",wiki);
                intent.putExtra("homeWorld",homeWorld);
                intent.putExtra("eyeColor",eyeColor);
                intent.putStringArrayListExtra("aff",(ArrayList<String>)aff);
                intent.putStringArrayListExtra("mast",(ArrayList<String>)masters);
                intent.putStringArrayListExtra("app",(ArrayList<String>)app);
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
//            heroId=(TextView)itemView.findViewById(R.id.heroId);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mData = mData2;
                } else {

                    ArrayList<Datum> filterList = new ArrayList<>();

                    for (Datum data : mData2) {

                        if (data.getName().toLowerCase().contains(charString)) {
                            filterList.add(data);
                        }
                    }

                    mData = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mData = (ArrayList<Datum>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
