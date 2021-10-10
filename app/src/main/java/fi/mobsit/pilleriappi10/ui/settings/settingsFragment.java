package fi.mobsit.pilleriappi10.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fi.mobsit.pilleriappi10.CreditsInfo;
import fi.mobsit.pilleriappi10.databinding.FragmentSettingsBinding;
import fi.mobsit.pilleriappi10.notifications.nofificationsManager;

public class settingsFragment extends Fragment {

    private settingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        settingsViewModel =
                new ViewModelProvider(this).get(settingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button buttonCredits = binding.buttonCredits;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //buttonCredits.setText(s);
                buttonCredits.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openActivity2();
                    }
                });
            }
        });

        final TextView textView2 = binding.textLanguage;
        settingsViewModel.getText2().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView2.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nofificationsManager notification = new nofificationsManager(getActivity());
        Boolean notificationPermission = notification.toBool();
        notification.newNotification("medicineRemind", 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openActivity2() {
        Intent intent = new Intent(this.getActivity(), CreditsInfo.class);
        startActivity(intent);

    }
}