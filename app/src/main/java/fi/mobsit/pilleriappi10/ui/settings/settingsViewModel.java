package fi.mobsit.pilleriappi10.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Andreas Lang
 */

public class settingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mText2;

    public settingsViewModel() {
        mText = new MutableLiveData<>();
        mText2 = new MutableLiveData<>();
        mText.setValue("KREDIITIT");
        mText2.setValue("KIELI/LANGUAGE");
    }


    public LiveData<String> getText() {

        return mText;

    }

    public LiveData<String> getText2() {

        return mText2;

    }
}