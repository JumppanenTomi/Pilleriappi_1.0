package fi.mobsit.pilleriappi10.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentHomeBinding;

/**
 * @author Valtteri Rautauoma
 */
public class HomeFragment extends Fragment {

    public static final String SHARED_PREFS = "pendingMedicineData";//setting variable's value to value that is our preference file name
    public static final String MEDICINE_ARRAY = "medicineArray";//setting variable's value to value that is our preference tag's correct value
    public static final String SHARED_PREFS_SETTING = "Settings";//setting variable's value to value that is our preference file name
    public static final String MEDICINE_ARRAY_USERNAME = "firstname";//setting variable's value to value that is our preference tag's correct value
    List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
    private FragmentHomeBinding binding;
    private ListView listmed;
    private String json;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView homeTextBox = view.findViewById(R.id.text_home);//initializing variable to get hjdata from Textbox
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        String jsonData = sharedPreferences.getString(MEDICINE_ARRAY, "");//getting old data from shared preferences file so it wont be wiped after creating new arraylist

        SharedPreferences settings = getActivity().getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        String username = settings.getString(MEDICINE_ARRAY_USERNAME, "");//getting old data from shared preferences file so it wont be wiped after creating new arraylist

        TextView textview = view.findViewById(R.id.text_home);
        TextView welcomeText = view.findViewById(R.id.home_textbox);
        json = jsonData;
        textview.setText(getString(R.string.your_medicines));
        String welcomeMessage = getString(R.string.secondary_title_home);
        username =  welcomeMessage + username;
        welcomeText.setText(username);
        if (jsonData != null && jsonData.length() > 0) {
            initlist();
            ListView listmed = view.findViewById(R.id.listmed);
            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), lista, android.R.layout.simple_list_item_1, new String[]{"nimet"}, new int[]{android.R.id.text1});
            listmed.setAdapter(simpleAdapter);
        }else{
            Toast.makeText(getActivity(), getString(R.string.list_medicine_empty), Toast.LENGTH_SHORT).show();
            //Navigation.findNavController(view).navigate(R.id.navigation_home); //if this isnt users first time in application, then we lead him/her straight to home-fragment
        }

    }

    private void initlist() {

        try {

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("medicineName");
                String time = object.getString("time");
                String date = object.getString("date");
                String outPut = getString(R.string.medicine) + ": " + name + "\n" + getString(R.string.time) + ": " + time + "\n" + getString(R.string.date) + ": " + date;
                lista.add(createMed("nimet", outPut));
            }

        } catch (JSONException e) {
            Toast.makeText(getActivity(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String> createMed(String name, String time) {
        HashMap<String, String> mednro = new HashMap<String, String>();
        mednro.put(name, time);
        return mednro;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}