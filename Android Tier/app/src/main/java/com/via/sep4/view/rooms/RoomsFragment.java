package com.via.sep4.view.rooms;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.via.sep4.R;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;

import java.util.ArrayList;

public class RoomsFragment extends Fragment {

    private DataViewModel mViewModel;
    private RecyclerView rooms;
    private FloatingActionButton addButton;
    private RoomsAdapter adapter;
    private ArrayList<Room> roomList = new ArrayList<>();
    private SharedPreferences listPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rooms_fragment, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        rooms = v.findViewById(R.id.roomListView);
        addButton = v.findViewById(R.id.rooms_add_button);


        loadData();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void loadData() {
        roomList.clear();
        roomList = mViewModel.getRooms();
        roomList.remove(0);
        initListAndClick();
    }

    private void initListAndClick(){
        adapter = new RoomsAdapter(roomList);
        rooms.setAdapter(adapter);

        adapter.setOnClickListener(new RoomsAdapter.OnClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                Log.d("click button", "delete");
            }

            @Override
            public void onDetailClick(View view, int position) {
                Log.d("click button", "detail");
            }

            @Override
            public void onNormsClick(View view, int position) {
                Log.d("click button", "norms");
            }

        });
    }

}



