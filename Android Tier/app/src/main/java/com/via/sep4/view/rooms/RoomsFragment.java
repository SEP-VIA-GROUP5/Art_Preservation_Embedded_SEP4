package com.via.sep4.view.rooms;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
        initListAndClick();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setDialog(AlertDialog.Builder deleteAlert) {

    }

    private void loadData() {
        roomList.clear();
        roomList = mViewModel.getRooms();
        roomList.remove(0);
    }

    private void initListAndClick() {
        adapter = new RoomsAdapter(roomList);
        rooms.setAdapter(adapter);

        adapter.setOnClickListener(new RoomsAdapter.OnClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                Room room = roomList.get(position);

                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(getContext());
                deleteAlert.setTitle(R.string.singleroom_delete_dialog_title);
                deleteAlert.setIcon(R.drawable.alert_icon);
                deleteAlert.setMessage(R.string.singleroom_delete_dialog_message);
                deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int code = mViewModel.deleteRoom(room.getId());
                        if (code == 200) {
                            Snackbar.make(view, getString(R.string.single_delete_ok), Snackbar.LENGTH_SHORT).show();
                            loadData();
                            adapter = new RoomsAdapter(roomList);
                            rooms.setAdapter(adapter);
                        } else {
                            Snackbar.make(view, "Error: " + code, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                deleteAlert.setNegativeButton("Let me think", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nothing here, just cancel the operation
                    }
                });

                deleteAlert.show();
            }

            @Override
            public void onDetailClick(View view, int position) {
                Log.d("click button", "detail");
                Log.d("click button position", String.valueOf(position));
                Room room = roomList.get(position);
                Log.d("click room", String.valueOf(room.getId()));
            }

            @Override
            public void onNormsClick(View view, int position) {
                Log.d("click button", "norms");
                Log.d("click button position", String.valueOf(position));
                Room room = roomList.get(position);
                Log.d("click room", String.valueOf(room.getId()));
            }

        });
    }

}



