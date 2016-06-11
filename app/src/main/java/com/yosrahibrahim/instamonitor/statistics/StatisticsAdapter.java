package com.yosrahibrahim.instamonitor.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yosrahibrahim.instamonitor.R;

import java.util.List;

import instamonitor.yosrahibrahim.com.instamonitorlib.InstaMonitor;
import instamonitor.yosrahibrahim.com.instamonitorlib.models.InstaSession;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 */
public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {

    private Context context;
    private List<InstaSession> sessions;

    public StatisticsAdapter(Context context, List<InstaSession> sessions){
        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_statistic, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(sessions.get(position).getName());
        holder.duration.setText(InstaMonitor.formatTime(sessions.get(position).getDuration() ));

    }



    @Override
    public int getItemCount() {
        return (sessions == null) ? 0: sessions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, duration;

        public ViewHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.name);
            duration = (TextView) v.findViewById(R.id.duration);

        }

    }


}


