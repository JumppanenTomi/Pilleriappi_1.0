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

/**
 * @author Tomi Jumppanen
 */

public class addFragment extends Fragment {
    public static final String SHARED_PREFS = "pendingMedicineData";//setting variable's value to value that is our preference file name
    public static final String MEDICINE_ARRAY = "medicineArray";//setting variable's value to value that is our preference tag's correct value
    private FragmentAddBinding binding;
    private String oldDataJson;//initializing variable to store data from last fragment session

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);//Telling Dalvik VM to execute all classes in current Activity, instead of just execute just this class



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it

        oldDataJson = sharedPreferences.getString(MEDICINE_ARRAY, "");//getting old data from shared preferences file so it wont be wiped after creating new arraylist

        ArrayList<addMedicine> medicines = new ArrayList<>();//initializing new arraylist

        Button addMedicineButton = view.findViewById(R.id.addMedicineButton);//initializing variable to get data from button

        TimePicker timePicker = view.findViewById(R.id.medicineTakingTimepicker);
        timePicker.setIs24HourView(true);

        addMedicineButton.setOnClickListener(v -> {
            EditText medicineNameTextbox = view.findViewById(R.id.medicineNameTextbox);//initializing variable to get data from Textbox
            DatePicker medicineTakingDateTextbox = view.findViewById(R.id.medicineTakingDateTextbox);//initializing variable to get data from Calendar-widget
            TimePicker medicineTakingTimepicker = view.findViewById(R.id.medicineTakingTimepicker);//initializing variable to get data from clock-widget

            addMedicine newMedicine = new addMedicine(medicineNameTextbox.getText().toString(), medicineTakingTimepicker.getCurrentMinute(), medicineTakingTimepicker.getCurrentHour(), medicineTakingDateTextbox.getDayOfMonth(), medicineTakingDateTextbox.getMonth() + 1, medicineTakingDateTextbox.getYear());//Creating new object for added addMedicine
            medicines.add(newMedicine);//adding just created object to our arraylist

            SharedPreferences.Editor editor = sharedPreferences.edit();//starting sharedPreferences editor
            Gson gson = new Gson();//creating new object called gson
            String newJson = gson.toJson(medicines);//converting our arraylist into json-string
            String json;//creating string variable that in the end will store all of our medicine data
            if (oldDataJson != null && oldDataJson.length() > 0) {//if there is old data
                oldDataJson = oldDataJson.substring(0, oldDataJson.length() - 1);//then we remove last letter from old json string..
                newJson = newJson.substring(1);//..and first letter of new json string..
                json = oldDataJson + "," + newJson;//..and then we combine them and also add ","-letter between strings so it finalize combining
            } else {//if there wasn't old data..
                json = newJson;//..then we just paste new data to json variable
            }
            editor.putString(MEDICINE_ARRAY, json);//adding value of variable "json" to sharedPreferences editor queue
            editor.apply();//applying changes to sharedPreferences from last editor line

            Toast.makeText(getActivity(), R.string.add_medicine_addded, Toast.LENGTH_SHORT).show();//creating nice popup for user to tell that data was sent
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}