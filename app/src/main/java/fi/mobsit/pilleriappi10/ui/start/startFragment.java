package fi.mobsit.pilleriappi10.ui.start;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import fi.mobsit.pilleriappi10.databinding.FragmentStartBinding;
import fi.mobsit.pilleriappi10.R;

/** @author Tomi Jumppanen */

public class startFragment extends Fragment {
    public static final  String SHARED_PREFS = "Settings";//setting variable's value to value that is our preference file name
    public static final String FIRST_LAUNCH = "firstLaunch";//setting variable's value to value that is our preference tag's correct value
    public static final String NOTIFICATIONS = "notifications";//setting variable's value to value that is our preference tag's correct value
    public static final String FIRST_NAME = "firstname";//setting variable's value to value that is our preference tag's correct value

    private FragmentStartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        boolean firstLaunch = sharedPreferences.getBoolean(FIRST_LAUNCH, true);
        if(!firstLaunch){
            Navigation.findNavController(view).navigate(R.id.navigation_home); //if this isnt users first time in application, then we lead him/her straight to home-fragment
        }else{
            Button continueButton = view.findViewById(R.id.start_continue);//initializing variable to get data from button
            continueButton.setOnClickListener(v -> {
                EditText usernametextbox = view.findViewById(R.id.usernametextbox);//initializing variable to get data from Textbox
                Switch notificationSwitch = view.findViewById(R.id.switch1);

                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putBoolean(FIRST_LAUNCH, false);
                prefEditor.putBoolean(NOTIFICATIONS, notificationSwitch.getShowText());
                prefEditor.putString(FIRST_NAME, usernametextbox.getText().toString());
                prefEditor.apply();
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}