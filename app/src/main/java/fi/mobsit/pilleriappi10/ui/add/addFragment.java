package fi.mobsit.pilleriappi10.ui.add;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
        Log.v("Message", "ADD-fragment opened");
        super.onViewCreated(view, savedInstanceState);
        EditText medicineNameTextbox = (EditText) view.findViewById(R.id.medicineNameTextbox);
        DatePicker medicineTakingDateTextbox = (DatePicker) view.findViewById(R.id.medicineTakingDateTextbox);
        TimePicker medicineTakingTimepicker = (TimePicker) view.findViewById(R.id.medicineTakingTimepicker);

        medicineTakingTimepicker.setIs24HourView(true); //converts our time picker to 24h format. By default its 12h format

        Button addMedicineButton = (Button) view.findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(v -> {
            int hour = medicineTakingTimepicker.getCurrentHour();
            int min = medicineTakingTimepicker.getCurrentMinute();
            int day = medicineTakingDateTextbox.getDayOfMonth();
            int month = medicineTakingDateTextbox.getMonth() + 1;
            int year = medicineTakingDateTextbox.getYear();
            if(TextUtils.isEmpty(medicineNameTextbox.getText().toString())){    //checks that is medicine namespace empty...
                Log.v("Error", "Some of values were empty, check form!");
            }
            else{   //...if not then we continue executing
                Log.v("Medicine added", medicineNameTextbox.getText() + ", " + day + "." + month + "." + year + ", " + hour + "." + min);
                Navigation.findNavController(view).navigate(R.id.navigation_home);  //return user back to home-fragment after submitting new form
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.v("Message", "ADD-fragment closed");
    }
}