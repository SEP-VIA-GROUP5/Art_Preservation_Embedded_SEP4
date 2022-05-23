package com.via.sep4.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.via.sep4.R;


public class LogOutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(LogOutFragment.this).navigate(R.id.action_logOutFragment_to_signIn_fragment);
        return inflater.inflate(R.layout.fragment_log_out, container, false);
    }
}