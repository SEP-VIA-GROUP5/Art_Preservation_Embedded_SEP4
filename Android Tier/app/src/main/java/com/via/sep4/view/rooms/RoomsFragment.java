package com.via.sep4.view.rooms;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.via.sep4.R;
import com.via.sep4.model.Room;
import com.via.sep4.view.HomeFragment;
import com.via.sep4.view.LogInFragment;
import com.via.sep4.view.SettingsFragment;
import com.via.sep4.viewModel.DataViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoomsFragment extends Fragment {

    private DataViewModel mViewModel;
    private RecyclerView rooms;
    private FloatingActionButton addButton;
    private RoomsAdapter adapter;
    private ArrayList<Room> roomList = new ArrayList<>();

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
                Bundle id = new Bundle();
                id.putInt("roomId", room.getId());
            }

            @Override
            public void onNormsClick(View view, int position) {
                Log.d("click button", "norms");
                Log.d("click button position", String.valueOf(position));
                Room room = roomList.get(position);
                Bundle id = new Bundle();
                id.putInt("roomId", room.getId());
                NavHostFragment.findNavController(RoomsFragment.this).navigate(R.id.action_roomsFragment_to_settingsFragment, id);
            }

        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = new EditText(view.getContext());
                nameText.setHint(R.string.singleroom_add_name);
                EditText numberText = new EditText(view.getContext());
                numberText.setInputType(InputType.TYPE_CLASS_NUMBER);
                numberText.setHint(R.string.singleroom_add_number);
                AlertDialog.Builder addRoomDialog = new AlertDialog.Builder(getContext());
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.addView(nameText);
                linearLayout.addView(numberText);
                addRoomDialog.setView(linearLayout);
                addRoomDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String roomName = nameText.getText().toString();
                        String roomNumber = numberText.getText().toString();
                        if (roomName.equals("") || roomNumber.equals("")) {
                            dialogInterface.dismiss();
                        } else {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("name", roomName);
                                jsonObject.put("number", Integer.parseInt(roomNumber));
                                int roomId = mViewModel.addARoom(jsonObject);
                                mViewModel.addMetricsToRoom(roomId);
                                loadData();
                                adapter = new RoomsAdapter(roomList);
                                rooms.setAdapter(adapter);
                            } catch (JSONException e) {
                                Log.d("json e", e.toString());
                            }
                        }
                    }
                });
                addRoomDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                addRoomDialog.create().show();
            }
        });
    }

}



