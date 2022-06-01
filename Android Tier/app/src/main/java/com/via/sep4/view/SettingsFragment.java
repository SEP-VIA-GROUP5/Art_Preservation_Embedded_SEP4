package com.via.sep4.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;

public class SettingsFragment extends Fragment {

    private DataViewModel viewModel;
    private Room room;
    private TextView roomId;
    private int id;
    private Metrics[] metrics;

    private Button setBtn;
    private EditText maxTemp, maxHum, maxCO2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getInt("roomId");
        Log.d("bundle id", String.valueOf(id));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        roomId = v.findViewById(R.id.settingsRoom_id);
        room = viewModel.getSingleRoom(id);
        roomId.setText(String.valueOf(id));
        metrics = room.getMetrics();
        initView(v);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("metrics", String.valueOf(metrics.length));
                if (metrics.length > 0) {
                    //temperature
                    metrics[3].getTemperature().setMax(toInt(maxTemp));
                    viewModel.addTempNorm(metrics[3].getTemperature(), toInt(maxTemp));

                    //humidity
                    metrics[3].getHumidity().setMax(toInt(maxHum));
                    viewModel.addHumNorm(metrics[3].getHumidity(), toInt(maxHum));

                    //co2
                    metrics[3].getCO2().setMax(toInt(maxCO2));
                    viewModel.addCO2Norm(metrics[3].getCO2(), toInt(maxCO2));

                    Toast.makeText(getContext(), R.string.settings_saveChange, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.set_error, Toast.LENGTH_SHORT).show();
                }
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
        setBtn = view.findViewById(R.id.settings_save);

        maxCO2.setText(String.valueOf(metrics[3].getCO2().getMax()));
        maxHum.setText(String.valueOf(metrics[3].getHumidity().getMax()));
        maxTemp.setText(String.valueOf(metrics[3].getTemperature().getMax()));
    }

    public int toInt(EditText edtext) {
        String value = edtext.getText().toString();
        int finalRes = Integer.parseInt(value);
        return finalRes;
    }


}



