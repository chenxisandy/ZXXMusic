package com.example.zxxmusic.main;

import com.example.zxxmusic.base.BasePresenter;
import com.example.zxxmusic.model.DataRepo;

public class MainPresenter extends BasePresenter<MainContract.IView>
        implements MainContract.IPresenter {

    DataRepo repo = DataRepo.getInstance();

    @Override
    public void initListData() {
        mViewRef.get().setRecyclerList(repo.getCollectionList());
    }
}
