package com.via.sep4.view.rooms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private ArrayList<Room> rooms;
    private OnClickListener listener;

    RoomsAdapter(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_single_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = rooms.get(position);
        Metrics[] metrics = room.getMetrics();
        Log.d("metrics rooms", metrics[0].toString());
        Log.d("metrics rooms", metrics[1].toString());
        Log.d("metrics rooms", metrics[2].toString());
        String idS = String.valueOf(room.getId());

        String tS;
        String hS;
        String co2S;
        if (metrics.length == 0) {
            tS = "N/A";
            hS = "N/A";
            co2S = "N/A";
        } else {
            tS = String.valueOf(metrics[0].getTemperature().getTemperature());
            hS = String.valueOf(metrics[0].getHumidity().getHumidity());
            co2S = String.valueOf(metrics[0].getCO2().getCo2());
        }

        holder.id.setText(idS);
        holder.name.setText(room.getName());
        holder.t.setText(tS);
        holder.h.setText(hS);
        holder.co2.setText(co2S);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onDeleteClick(view, position);
                }
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onDetailClick(view, position);
                }
            }
        });

        holder.norms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onNormsClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView t;
        private final TextView h;
        private final TextView co2;
        private final TextView name;
        private final Button detail;
        private final Button delete;
        private final Button norms;

        ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.singleRoom_id);
            t = itemView.findViewById(R.id.single_t);
            h = itemView.findViewById(R.id.single_humidity);
            co2 = itemView.findViewById(R.id.single_co2);
            name = itemView.findViewById(R.id.single_name);
            delete = itemView.findViewById(R.id.single_remove);
            detail = itemView.findViewById(R.id.single_detail);
            norms = itemView.findViewById(R.id.single_norm);

        }
    }

    public interface OnClickListener {
        void onDeleteClick(View view, int position);
        void onDetailClick(View view, int position);
        void onNormsClick(View view, int position);
    }
}
