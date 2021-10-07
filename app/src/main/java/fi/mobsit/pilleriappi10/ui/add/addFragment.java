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
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentAddBinding;//

public class addFragment extends Fragment {
    private FragmentAddBinding binding;
    private String oldDataJson;

    public static final  String SHARED_PREFS = "pendingMedicineData";
    public static  final String MEDICINE_ARRAY = "medicineArray";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        oldDataJson = sharedPreferences.getString(MEDICINE_ARRAY, "");

        ArrayList<medicine> medicines = new ArrayList<>();;
        super.onViewCreated(view, savedInstanceState);
        Button addMedicineButton = view.findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(v -> {
            EditText medicineNameTextbox = view.findViewById(R.id.medicineNameTextbox);
            DatePicker medicineTakingDateTextbox = view.findViewById(R.id.medicineTakingDateTextbox);
            TimePicker medicineTakingTimepicker = view.findViewById(R.id.medicineTakingTimepicker);

            medicine newMedicine = new medicine(medicineNameTextbox.getText().toString(), medicineTakingTimepicker.getCurrentMinute(), medicineTakingTimepicker.getCurrentHour(), medicineTakingDateTextbox.getDayOfMonth(), medicineTakingDateTextbox.getMonth() + 1, medicineTakingDateTextbox.getYear());
            medicines.add(newMedicine);//Creating new object for added medicine
            SharedPreferences.Editor editor = sharedPreferences.edit();//starting sharedPreferences editor
            Gson gson = new Gson();//creating new object called gson
            String json = oldDataJson;
            json += gson.toJson(medicines);//telling to gson object to convert ArrayList called medicines to string variable called "json"
            String trimmedJson = json.replaceAll("&quot", "");//trimming json file. Removing "&quot" from array. REMOVE IF NECESSARILY!!!!
            editor.putString(MEDICINE_ARRAY, trimmedJson);//adding value of variable "json" to sharedPreferences editor queue
            editor.apply();//applying changes to sharedPreferences from last editor line

            Toast.makeText(getActivity(),"Lääke lisätty",Toast.LENGTH_SHORT).show();
            //Navigation.findNavController(view).navigate(R.id.navigation_home);  //return user back to home-fragment after submitting new form
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}