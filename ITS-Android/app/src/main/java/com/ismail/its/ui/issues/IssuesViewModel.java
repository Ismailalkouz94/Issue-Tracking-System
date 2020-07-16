package com.ismail.its.ui.issues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IssuesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IssuesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Issues fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}