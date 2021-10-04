package fi.mobsit.pilleriappi10.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add");
    }

    public LiveData<String> getText() {
        return mText;
    }
}