package com.example.zxxmusic.bottomLayout;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zxxmusic.R;
import com.example.zxxmusic.base.BaseFragment;
import com.example.zxxmusic.musicService.MusicService;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.BIND_AUTO_CREATE;

public class BottomLayoutFragment extends BaseFragment<BottomLayoutContract.IView,
        BottomLayoutPresenter> implements BottomLayoutContract.IView, View.OnClickListener{

    //activity/UI type
    private int type;

    //view
    private CircleImageView titleImv;

    private ImageView playOrPauseImv;

    private ImageView playListImv;

    private TextView titleTv;

    private TextView artistTv;

    private static final String TAG = "BottomLayoutFragment";

    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.bottom_layout_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindService();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (getActivity() == null) {
                Log.i(TAG, "onServiceConnected: getActivity error");
                return;
            }
            context = getActivity().getApplicationContext();
            BottomLayoutPresenter bottomLayoutPresenter =
                    new BottomLayoutPresenter(context, (MusicService.MusicPlayer) service, type);
            //attach view and presenter
            bottomLayoutPresenter.attachView(BottomLayoutFragment.this);
            setPresenter(bottomLayoutPresenter);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private void bindService() {
        if (getActivity() != null) {
            context = getActivity().getApplicationContext();
            Intent bindIntent = new Intent(context, MusicService.class);
            context.bindService(bindIntent, connection, BIND_AUTO_CREATE);
        }
    }

    //as IView
    @Override
    public void setTitle(String title) {
        titleTv.setText(title);
    }

    @Override
    public void setArtist(String artist) {
        artistTv.setText(artist);
    }

    @Override
    public CircleImageView getTitleImv() {
        return titleImv;
    }

    @Override
    public void play() {
        playOrPauseImv.setImageResource(R.drawable.notification_play);
    }

    @Override
    public void pause() {
        playOrPauseImv.setImageResource(R.drawable.notification_pause);
    }

    private void initView() {
        Activity activity = getActivity();
        if (activity != null) {
            titleImv = activity.findViewById(R.id.civ_bottom);
            playOrPauseImv = activity.findViewById(R.id.bottom_play);
            playListImv = activity.findViewById(R.id.bottom_text);
            artistTv = activity.findViewById(R.id.bottom_artist);
            titleTv = activity.findViewById(R.id.bottom_title);
            playOrPauseImv.setOnClickListener(this);
            playListImv.setOnClickListener(this);
            LinearLayout parentLayout = activity.findViewById(R.id.bottom_layout_fragment);
            parentLayout.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_layout_fragment:
                mPresenter.openMusicPlay();
                break;
            case R.id.bottom_play:
                mPresenter.playOrPause();
                break;
            case R.id.bottom_text:
                mPresenter.openListDialog();
                break;
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}
