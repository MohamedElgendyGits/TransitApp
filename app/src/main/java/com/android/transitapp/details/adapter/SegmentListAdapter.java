package com.android.transitapp.details.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.transitapp.R;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.data.modes.TravelModes;
import com.android.transitapp.utils.TextUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Elgendy.
 */

public class SegmentListAdapter extends RecyclerView.Adapter<SegmentListAdapter.DataObjectHolder> {

    private ArrayList<Segment> mDataSet;
    private Context context;
    private static SegmentListAdapter.SegmentClickListener mSegmentClickListener;


    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textView_list_item_segment_stops)
        TextView segmentStopsTextView;

        @BindView(R.id.imageView_list_item_segment_image)
        AppCompatImageView segmentImageView;


        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //adding listener...
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSegmentClickListener.onSegmentClick(getAdapterPosition(), v);
        }

        public void showText() {
            int parentHeight = ((View) segmentImageView.getParent()).getHeight();
            float scale = (parentHeight - segmentStopsTextView.getHeight()) / (float) segmentImageView.getHeight();
            segmentImageView.setPivotX(segmentImageView.getWidth() * 0.5f);
            segmentImageView.setPivotY(0);
            segmentImageView.animate().scaleX(scale)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            segmentStopsTextView.setVisibility(View.VISIBLE);
                            segmentImageView.setColorFilter(Color.BLACK);
                        }
                    })
                    .scaleY(scale).setDuration(200)
                    .start();
        }

        public void hideText() {
            segmentImageView.setColorFilter(ContextCompat.getColor(segmentImageView.getContext(), R.color.blackIconTint));
            segmentStopsTextView.setVisibility(View.INVISIBLE);
            segmentImageView.animate().scaleX(1f).scaleY(1f)
                    .setDuration(200)
                    .start();
        }
    }

    public SegmentListAdapter(ArrayList<Segment> mDataSet, SegmentListAdapter.SegmentClickListener mSegmentClickListener) {
        this.mDataSet = mDataSet;
        this.mSegmentClickListener = mSegmentClickListener;
    }

    @Override
    public SegmentListAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_segment, parent, false);
        context = parent.getContext();

        SegmentListAdapter.DataObjectHolder dataObjectHolder = new SegmentListAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(SegmentListAdapter.DataObjectHolder holder, int position) {

        Segment segmentModel = mDataSet.get(position);
        holder.segmentStopsTextView.setText(segmentModel.getStops().size() + " " + TextUtils.getString(R.string.stops));

        holder.segmentImageView.setColorFilter(R.color.blackIconTint);
        holder.segmentImageView.setImageResource(getIcon(segmentModel));
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

    public interface SegmentClickListener {
        void onSegmentClick(int position, View v);
    }
}
