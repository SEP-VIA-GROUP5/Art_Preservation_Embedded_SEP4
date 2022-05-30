package com.via.sep4.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Notification;
import com.via.sep4.model.Room;
import com.via.sep4.viewModel.DataViewModel;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;
    private Button toNormsSettings, toDashboard;
    private TextView temperature, humidity, CO2;
    private Room room;
public static final String CHANNEL_1_ID="tempChannel";
    public static final String CHANNEL_2_ID="humChannel";
    public static final String CHANNEL_3_ID="CO2Channel";



    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        checkUser(user, getContext());
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        //notification channel is not available on low api levels ( here i check for it)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel tempChannel = new NotificationChannel(
                    CHANNEL_1_ID, "Temperature is rising", NotificationManager.IMPORTANCE_HIGH
            );
            tempChannel.setDescription("Temperature is high");

            NotificationChannel humChannel = new NotificationChannel(
            CHANNEL_2_ID, "Humidity is rising", NotificationManager.IMPORTANCE_HIGH
            );
            humChannel.setDescription("Humidity is high");

            NotificationChannel CO2Channel = new NotificationChannel(
                    CHANNEL_3_ID, "C02 is rising", NotificationManager.IMPORTANCE_HIGH
            );
            CO2Channel.setDescription("C02 is high");

            sensorManager = (SensorManager)
                    requireActivity().getSystemService(Context.SENSOR_SERVICE);

            SensorManager sm = (SensorManager) getLayoutInflater().getContext().getSystemService(Context.SENSOR_SERVICE);
          NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannels(tempChannel);
            manager.createNotificationChannels(humChannel);
            manager.createNotificationChannels(CO2Channel);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_home, container, false);

        temperature = v.findViewById(R.id.dbtemperature);
        humidity = v.findViewById(R.id.dbhumidity);
        CO2 = v.findViewById(R.id.dbCO2);
        toNormsSettings = v.findViewById(R.id.toNorms);
        toDashboard = v.findViewById(R.id.toDashboradGraph);

// here i am selecting that the object room to be sent
        String tempS = "N/A";
        String humS = "N/A";
        String co2S = "N/A";
        if (viewModel.getSingleRoom(1) == null) {
            Toast.makeText(getContext(), R.string.fail_connectServer, Toast.LENGTH_LONG).show();
            toNormsSettings.setVisibility(View.GONE);
            toDashboard.setVisibility(View.GONE);
        } else {
            room = viewModel.getSingleRoom(1);
            Metrics[] metrics = room.getMetrics();
            if (metrics.length != 0) {
                tempS = String.valueOf(metrics[0].getTemperature().getTemperature());
                humS = String.valueOf(metrics[0].getHumidity().getHumidity());
                co2S = String.valueOf(metrics[0].getCO2().getCo2());
            }
            toNormsSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle id = new Bundle();
                    id.putInt("roomId", room.getId());
                    NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_settingsFragment, id);
                }
            });
        }

        temperature.setText(tempS);
        humidity.setText(humS);
        CO2.setText(co2S);
        return v;

    }



    private void checkUser(FirebaseUser user, Context context) {
        if (user == null) {
            Toast.makeText(context, R.string.main_login_info, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_signIn_fragment);
        }
    }

}
