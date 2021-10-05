package fi.mobsit.pilleriappi10.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentAddBinding;

public class addFragment extends Fragment {

    private FragmentAddBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.v("VIESTI", "ADD-sivu avattu");
        super.onViewCreated(view, savedInstanceState);
        EditText medicineNameTextbox = (EditText) view.findViewById(R.id.medicineNameTextbox);
        DatePicker medicineTakingDateTextbox = (DatePicker) view.findViewById(R.id.medicineTakingDateTextbox);
        TimePicker medicineTakingTimepicker = (TimePicker) view.findViewById(R.id.medicineTakingTimepicker);

        Button addMedicineButton = (Button) view.findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int hour = medicineTakingTimepicker.getCurrentHour();
                int min = medicineTakingTimepicker.getCurrentMinute();
                int day = medicineTakingDateTextbox.getDayOfMonth();
                int month = medicineTakingDateTextbox.getMonth() + 1;
                int year = medicineTakingDateTextbox.getYear();
                Log.v("VIESTI", "Lääke lisätty " + medicineNameTextbox.getText() + ", " + day + "." + month + "." + year + ", " + hour + "." + min);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.v("VIESTI", "ADD-sivu suljettu");
    }
}