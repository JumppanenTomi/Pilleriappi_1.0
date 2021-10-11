package fi.mobsit.pilleriappi10.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

import fi.mobsit.pilleriappi10.BuildConfig;
import fi.mobsit.pilleriappi10.CreditsInfo;
import fi.mobsit.pilleriappi10.LocaleHelper;
import fi.mobsit.pilleriappi10.MainActivity;
import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentSettingsBinding;
import fi.mobsit.pilleriappi10.notifications.nofificationsManager;

public class settingsFragment extends Fragment {

    TextView language_dialog,helloworldtxt;
    boolean lang_selected = true;
    Context context;
    Resources resources;

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

        final TextView language_dialog = binding.dialogLanguage;
        settingsViewModel.getText2().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                language_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String[] language = {"ENGLISH", "SUOMI"};

                        int checkedItem;

                        if(lang_selected)
                        {
                            checkedItem = 0;
                        }
                        else
                        {
                            checkedItem=1;
                        }

                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setTitle("Select a language")
                                .setSingleChoiceItems(language, checkedItem, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        language_dialog.setText(language[i]);
                                        if(language[i].equals("ENGLISH"))
                                        {

                                            context = LocaleHelper.setLocale(getActivity(), "en");
                                            resources = context.getResources();

                                         //   helloworldtxt.setText(resources.getString(R.string.language));
                                        }
                                        if(language[i].equals("SUOMI"))
                                        {
                                            context = LocaleHelper.setLocale(getActivity(), "fi");
                                            resources = context.getResources();

                                           // helloworldtxt.setText(resources.getString(R.string.language));

                                        }

                                    }
                                })
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create().show();
                    }
                });
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