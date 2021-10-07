package fi.mobsit.pilleriappi10.ui.add;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        ArrayList<medicine> medicines = new ArrayList<>();
        super.onViewCreated(view, savedInstanceState);
        Button addMedicineButton = (Button) view.findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(v -> {
            EditText medicineNameTextbox = (EditText) view.findViewById(R.id.medicineNameTextbox);
            DatePicker medicineTakingDateTextbox = (DatePicker) view.findViewById(R.id.medicineTakingDateTextbox);
            TimePicker medicineTakingTimepicker = (TimePicker) view.findViewById(R.id.medicineTakingTimepicker);

            medicine newMedicine = new medicine(medicineNameTextbox.getText().toString(), medicineTakingTimepicker.getCurrentMinute(), medicineTakingTimepicker.getCurrentHour(), medicineTakingDateTextbox.getDayOfMonth(), medicineTakingDateTextbox.getMonth() + 1, medicineTakingDateTextbox.getYear());
            medicines.add(newMedicine);


            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(medicines);
            json.parse
            editor.putString(MEDICINE_NAME, json);
            editor.apply();

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