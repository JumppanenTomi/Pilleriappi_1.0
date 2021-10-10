package fi.mobsit.pilleriappi10.ui.home;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.mobsit.pilleriappi10.R;
import fi.mobsit.pilleriappi10.databinding.FragmentHomeBinding;
/** @author Valtteri Rautauoma */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static final String SHARED_PREFS = "pendingMedicineData";//setting variable's value to value that is our preference file name
    public static final String MEDICINE_ARRAY = "medicineArray";//setting variable's value to value that is our preference tag's correct value
    private ListView listmed;
    private String json;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView homeTextBox = view.findViewById(R.id.text_home);//initializing variable to get data from Textbox
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        String jsonData = sharedPreferences.getString(MEDICINE_ARRAY, getResources().getString(R.string.add_medicine_name));//getting old data from shared preferences file so it wont be wiped after creating new arraylist
        TextView textview = view.findViewById(R.id.text_home);
        json = jsonData;
        textview.setText("Lääkkeesi");
        initlist();
        ListView listmed = view.findViewById(R.id.listmed);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), lista,
                android.R.layout.simple_list_item_1, new String[] {"nimet"}, new int[] {android.R.id.text1});
        listmed.setAdapter(simpleAdapter);
            }

        List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
        private void initlist() {


            try {

                JSONArray jsonArray = new JSONArray(json);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String name = object.getString("name");
                    String time = object.getString("time");
                    String outPut = name+"-"+time;
                    lista.add(createMed("nimet", outPut));
                }
            }
            catch (JSONException e) {
                Toast.makeText(getActivity(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        private HashMap<String, String>createMed(String name,String time){
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