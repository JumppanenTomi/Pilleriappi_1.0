package fi.mobsit.pilleriappi10.ui.add;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentAddBinding;

public class addFragment extends Fragment {
    private FragmentAddBinding binding;
    Context context;

    public static final  String SHARED_PREFS = "pendingMedicineData";
    public static  final String MEDICINE_NAME = "name";

    private String medicineName;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button addMedicineButton = (Button) view.findViewById(R.id.addMedicineButton);
        checkSaveState();

        addMedicineButton.setOnClickListener(v -> {
            EditText medicineNameTextbox = (EditText) view.findViewById(R.id.medicineNameTextbox);
            DatePicker medicineTakingDateTextbox = (DatePicker) view.findViewById(R.id.medicineTakingDateTextbox);
            TimePicker medicineTakingTimepicker = (TimePicker) view.findViewById(R.id.medicineTakingTimepicker);
            medicine newMedicine = new medicine(medicineNameTextbox.getText().toString(), medicineTakingTimepicker.getCurrentMinute(), medicineTakingTimepicker.getCurrentHour(), medicineTakingDateTextbox.getDayOfMonth(), medicineTakingDateTextbox.getMonth() + 1, medicineTakingDateTextbox.getYear());

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(newMedicine);
            editor.putString(MEDICINE_NAME, json);
            editor.apply();

            //saveData(medicineNameTextbox.getText().toString());
            Toast.makeText(getActivity(),checkSaveState(),Toast.LENGTH_SHORT).show();
            //Navigation.findNavController(view).navigate(R.id.navigation_home);  //return user back to home-fragment after submitting new form
        });
    }

    public void saveData(String medicineNameTextbox){
        if (medicineNameTextbox.isEmpty()){

        }else{
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MEDICINE_NAME, medicineNameTextbox);
            editor.apply();
        }
    }

    public String checkSaveState(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        medicineName = sharedPreferences.getString(MEDICINE_NAME, "");
        if (medicineName.isEmpty()){
            return "Jokin kentistä oli tyhjä. Tarkista kentät.";
        }else{
            return medicineName + " on lisätty.";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.v("Message", "ADD-fragment closed");
    }
}