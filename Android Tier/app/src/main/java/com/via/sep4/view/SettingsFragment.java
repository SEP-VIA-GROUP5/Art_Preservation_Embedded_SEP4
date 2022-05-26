package com.via.sep4.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.User;
import com.via.sep4.viewModel.DataViewModel;

public class SettingsFragment extends Fragment {

    private DataViewModel viewModel;
    private Room room;
    private TextView temperature;
    /*   @Override
     public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
          setPreferencesFromResource(R.xml.root_preferences, rootKey);
      }*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            Room receivedcar = bundle.getParcelable("keyforroom"); // Key

        }
        else if(bundle==null)
        {  Log.d("click button", "detail");}





    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);


        View v = inflater.inflate(R.layout.fragmeny_settings, container, false);









        return v;
    }





    }



