package fi.mobsit.pilleriappi10.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class addViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public addViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add");
    }

    public LiveData<String> getText() {
        return mText;
    }
}