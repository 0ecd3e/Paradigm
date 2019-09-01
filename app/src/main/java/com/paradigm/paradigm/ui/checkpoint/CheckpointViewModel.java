package com.paradigm.paradigm.ui.checkpoint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckpointViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CheckpointViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is checkpoint fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
