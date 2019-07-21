package com.example.zxxmusic.list;

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

import com.example.zxxmusic.R;
import com.example.zxxmusic.bean.Music;
import com.example.zxxmusic.play.MusicPlayActivity;
import com.example.zxxmusic.util.ValueUtil;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<Music> musicList;

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.music_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Music music = musicList.get(i);
        viewHolder.musicTitle.setText(music.getTitle());
        viewHolder.musicArtist.setText(music.getArtist());
        viewHolder.clickMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicPlayActivity.class);
                intent.putExtra(ValueUtil.INDEX_OF_LIST, i);
                // TODO: 2019/7/20 因为还有改播放服务里的list还没有实现，对index的判空还没有再播放activity实现
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView clickMore;

        TextView musicTitle;

        TextView musicArtist;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            clickMore = itemView.findViewById(R.id.music_more_choices);
            musicTitle = itemView.findViewById(R.id.music_item_title);
            musicArtist = itemView.findViewById(R.id.music_artist);
        }
    }

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }
}
