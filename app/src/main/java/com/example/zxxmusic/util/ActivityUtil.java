package com.example.zxxmusic.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.zxxmusic.R;
import com.example.zxxmusic.bottomLayout.BottomLayoutFragment;

public class ActivityUtil {
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(frameId, fragment)
                .commit();
    }

    public static void changeFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment,
                                                @NonNull Fragment mFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.hide(fragment);
        transaction.show(mFragment);
        transaction.commit();
    }

    public static void addBottomFragment(int uiType, FragmentManager fragmentManager) {
        BottomLayoutFragment bottomLayoutFragment = new BottomLayoutFragment();
        bottomLayoutFragment.setType(uiType);
        ActivityUtil.addFragmentToActivity(fragmentManager,
                bottomLayoutFragment, R.id.bottom_layout);
    }

    public static void GoActivity(Context context, Class cls) {
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

}
