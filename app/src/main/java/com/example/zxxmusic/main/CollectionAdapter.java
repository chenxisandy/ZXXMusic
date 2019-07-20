package com.example.zxxmusic.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zxxmusic.R;
import com.example.zxxmusic.bean.MusicCollection;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<MusicCollection> collectionList;

    private Context mContext;

    public CollectionAdapter(List<MusicCollection> collectionList) {
        this.collectionList = collectionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.collection_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")    //?
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MusicCollection collection = collectionList.get(i);
        viewHolder.countTv.setText(Integer.toString(collection.getMusicList().size()));
        viewHolder.titleTv.setText(collection.getCollectionFace().getTitle());
        Glide.with(mContext).load(collection.getCollectionFace().getImgPath()).into(viewHolder.imgImv);
        viewHolder.moreImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019/7/20 to open bottom dialog
            }
        });
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgImv;

        ImageView moreImv;

        TextView titleTv;

        TextView countTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImv = itemView.findViewById(R.id.collection_img_title);
            moreImv = itemView.findViewById(R.id.more_choice_collection_iv);
            titleTv = itemView.findViewById(R.id.collection_item_title);
            countTv = itemView.findViewById(R.id.collection_music_size);
        }
    }
}
