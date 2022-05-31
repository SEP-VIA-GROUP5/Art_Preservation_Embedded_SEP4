package com.via.sep4.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.via.sep4.R;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;
import com.via.sep4.viewModel.SettingsViewModel;

public class SettingsFragment extends Fragment {

    private DataViewModel viewModel;
    private SettingsViewModel viewSmodel;
    private Room room;
    private TextView roomId;
    private int id, finalValue;

    private Button setBtn;
    private EditText maxTemp, minTemp, maxHum, minHum,maxCO2, minCO2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getInt("roomId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);



        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        roomId = v.findViewById(R.id.settingsRoom_id);
        room = viewModel.getSingleRoom(id);
        roomId.setText(String.valueOf(id));
        Log.d("room", room.toString());

         initView(v);

         setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addTempNorm(toInt(maxTemp) );
                viewModel.addHumNorm(toInt(maxHum) );
                viewModel.addCO2Norm(toInt(maxCO2));

            }
        });

        return v;
    }


    private void initView(View view) {
        maxTemp = view.findViewById(R.id.MaxT);
   //     minTemp = view.findViewById(R.id.MinT);
        maxHum = view.findViewById(R.id.MaxH);
     //   minHum = view.findViewById(R.id.MinH);
        maxCO2 = view.findViewById(R.id.MaxC);
    //    minCO2 = view.findViewById(R.id.MinC);
        setBtn= view.findViewById(R.id.settings_save);
    }

public int toInt(EditText edtext)
{
    String value = edtext.getText().toString();
    int finalRes= Integer.parseInt(value);
    return finalRes;
}




}



