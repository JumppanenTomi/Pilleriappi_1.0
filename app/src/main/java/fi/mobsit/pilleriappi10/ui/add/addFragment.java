package fi.mobsit.pilleriappi10.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        Button addMedicineButton = (Button) view.findViewById(R.id.addMedicineButton);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("VIESTI", "Lääke lisätty");
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