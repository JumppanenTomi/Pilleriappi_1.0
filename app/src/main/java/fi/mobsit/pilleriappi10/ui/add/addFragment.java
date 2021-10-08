package fi.mobsit.pilleriappi10.ui.add;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentAddBinding;
import fi.mobsit.pilleriappi10.notifications.notificationManager;

/** @author Tomi Jumppanen */

public class addFragment extends Fragment {
    private FragmentAddBinding binding;
    private String oldDataJson;//initializing variable to store data from last fragment session

    public static final  String SHARED_PREFS = "pendingMedicineData";//setting variable's value to value that is our preference file name
    public static final String MEDICINE_ARRAY = "medicineArray";//setting variable's value to value that is our preference tag's correct value


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it

        oldDataJson = sharedPreferences.getString(MEDICINE_ARRAY, "");//getting old data from shared preferences file so it wont be wiped after creating new arraylist

        ArrayList<medicine> medicines = new ArrayList<>();//initializing new arraylist

        Button addMedicineButton = view.findViewById(R.id.addMedicineButton);//initializing variable to get data from button

        addMedicineButton.setOnClickListener(v -> {
            notificationManager notification = new notificationManager(getActivity());
            Boolean notificationPermission = notification.toBool();
            notification.newNotification("medicineNotification", 1);

            EditText medicineNameTextbox = view.findViewById(R.id.medicineNameTextbox);//initializing variable to get data from Textbox
            DatePicker medicineTakingDateTextbox = view.findViewById(R.id.medicineTakingDateTextbox);//initializing variable to get data from Calendar-widget
            TimePicker medicineTakingTimepicker = view.findViewById(R.id.medicineTakingTimepicker);//initializing variable to get data from clock-widget

            medicine newMedicine = new medicine(medicineNameTextbox.getText().toString(), medicineTakingTimepicker.getCurrentMinute(), medicineTakingTimepicker.getCurrentHour(), medicineTakingDateTextbox.getDayOfMonth(), medicineTakingDateTextbox.getMonth() + 1, medicineTakingDateTextbox.getYear());
            medicines.add(newMedicine);//Creating new object for added medicine

            SharedPreferences.Editor editor = sharedPreferences.edit();//starting sharedPreferences editor
            Gson gson = new Gson();//creating new object called gson
            String json = oldDataJson;//initializing old data to json variable
            json += gson.toJson(medicines);//telling to gson object to convert ArrayList called medicines and adding it to variable called json
            String trimmedJson = json.replaceAll("&quot", "");//trimming json file. Removing "&quot" from array. REMOVE IF NECESSARILY!!!!
            editor.putString(MEDICINE_ARRAY, trimmedJson);//adding value of variable "json" to sharedPreferences editor queue
            editor.apply();//applying changes to sharedPreferences from last editor line

            Toast.makeText(getActivity(),R.string.add_medicine_addded, Toast.LENGTH_SHORT).show();//creating nice popup for user to tell that data was sent
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}