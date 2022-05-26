package com.via.sep4.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.via.sep4.R;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;

public class SettingsFragment extends Fragment {

    private DataViewModel viewModel;
    private Room room;
    private TextView roomId;
    private int id;

    /*   @Override
     public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
          setPreferencesFromResource(R.xml.root_preferences, rootKey);
      }*/
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
        return v;
    }


}



