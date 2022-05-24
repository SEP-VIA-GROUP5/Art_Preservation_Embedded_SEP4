package com.via.sep4.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        checkUser(user, getContext());
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getSingleRoom(2);
                //viewModel.getMetricsSingleRoom(viewModel.getSingleRoom(2).getNumber());
            }
        });
        return v;
    }

    private void checkUser(FirebaseUser user, Context context) {
        if (user == null) {
            Toast.makeText(context, R.string.main_login_info, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_signIn_fragment);
        }
    }
}