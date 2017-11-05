package com.android.transitapp.main.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.transitapp.R;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.data.modes.TravelModes;
import com.android.transitapp.utils.TextUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Elgendy.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.DataObjectHolder> {

    private ArrayList<Route> mDataSet;
    private Context context;
    private static RouteClickListener mRouteClickListener;


    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textView_list_item_route_type)
        TextView routeTypeTextView;

        @BindView(R.id.layout_list_item_segments_container)
        LinearLayout segmentContainerLayout;

        @BindView(R.id.textView_list_item_price)
        TextView priceTextView;

        @BindView(R.id.textView_list_item_total_duration)
        TextView totalRouteDurationTextView;


        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //adding listener...
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRouteClickListener.onRouteClick(getAdapterPosition(), v);
        }
    }

    public RouteListAdapter(ArrayList<Route> mDataSet, RouteClickListener mRouteClickListener) {
        this.mDataSet = mDataSet;
        this.mRouteClickListener = mRouteClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_route, parent, false);
        context = parent.getContext();

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Route routeModel = mDataSet.get(position);
        holder.routeTypeTextView.setText(routeModel.getType());

        for (Segment segment : routeModel.getSegments()) {
            if(holder.segmentContainerLayout.getChildCount() <= routeModel.getSegments().size() - 1){
                holder.segmentContainerLayout.addView(createSegmentView(segment));
            }
        }

        if (routeModel.getPrice() != null) {
            holder.priceTextView.setText(routeModel.getPrice().toString());
        }

        holder.totalRouteDurationTextView.setText(routeModel.getRouteDuration()+" "+TextUtils.getString(R.string.minutes));
    }

    private View createSegmentView(Segment segment) {

        AppCompatImageView image = new AppCompatImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(150, 150);
        lp.setMargins(10,10,10,10);
        image.setLayoutParams(lp);
        image.setColorFilter(Color.parseColor(segment.getColor()));
        image.setImageResource(getIcon(segment));

        /* //most of icons urls that attached to the json response are broken so i will load them locally
        Log.d("IMAGE-URL",segment.getIconUrl());

        Picasso.with(context)
                .load(segment.getIconUrl())
                .fit()
                .into(image);
                */

        return image;
    }

    private int getIcon(Segment segment) {

        if (segment.getTravelMode().equals(TravelModes.bus.name())) {
            return R.drawable.route_bus;
        } else if (segment.getTravelMode().equals(TravelModes.change.name())) {
            return R.drawable.route_change;
        } else if (segment.getTravelMode().equals(TravelModes.cycling.name())) {
            return R.drawable.route_cycling;
        } else if (segment.getTravelMode().equals(TravelModes.driving.name())) {
            return R.drawable.route_driving;
        } else if (segment.getTravelMode().equals(TravelModes.parking.name())) {
            return R.drawable.route_parking;
        } else if (segment.getTravelMode().equals(TravelModes.setup.name())) {
            return R.drawable.route_setup;
        } else if (segment.getTravelMode().equals(TravelModes.subway.name())) {
            return R.drawable.route_subway;
        } else if (segment.getTravelMode().equals(TravelModes.walking.name())) {
            return R.drawable.route_walking;
        }

        return R.mipmap.ic_launcher;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface RouteClickListener {
        void onRouteClick(int position, View v);
    }
}
