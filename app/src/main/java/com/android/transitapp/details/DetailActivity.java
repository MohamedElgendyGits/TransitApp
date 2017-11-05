package com.android.transitapp.details;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.transitapp.R;
import com.android.transitapp.application.TransitConstants;
import com.android.transitapp.base.view.BaseActivity;
import com.android.transitapp.custom.SegmentCustomView;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.details.adapter.SegmentListAdapter;
import com.android.transitapp.main.view.adapter.RouteListAdapter;
import com.android.transitapp.utils.JsonUtil;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements LifecycleOwner,
        SegmentListAdapter.SegmentClickListener,
        DiscreteScrollView.ScrollStateChangeListener<SegmentListAdapter.DataObjectHolder>,
        DiscreteScrollView.OnItemChangedListener<SegmentListAdapter.DataObjectHolder>,
        View.OnClickListener {

    @BindView(R.id.segments_recycler_view)
    DiscreteScrollView segmentsRecyclerView;

    @BindView(R.id.segment_custom_view)
    SegmentCustomView segmentCustomView;

    @BindView(R.id.home)
    AppCompatImageView homeImageView;

    private Route mRoute;

    // declare segments list components
    private RecyclerView.Adapter segmentsAdapter;
    private ArrayList<Segment> segmentModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        if (getIntent().getExtras() != null) {
            String routeString = getIntent().getExtras().getString(TransitConstants.DETAIL_KEY);
            mRoute = JsonUtil.parseJson(routeString, Route.class);
        }

        initializeViews();
    }

    private void initializeViews() {

        homeImageView.setOnClickListener(this);

        // initialize recyclerView
        segmentsRecyclerView.setSlideOnFling(true);
        segmentsRecyclerView.addOnItemChangedListener(this);
        segmentsRecyclerView.addScrollStateChangeListener(this);
        int middlePosition = mRoute.getSegments().size() / 2;
        segmentsRecyclerView.scrollToPosition(middlePosition);
        segmentsRecyclerView.setItemTransitionTimeMillis(150);
        segmentsRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        segmentCustomView.setSegment(mRoute.getSegments().get(0));

        segmentModelList = new ArrayList<>();
        fillAdapterWithData();
        segmentsAdapter = new SegmentListAdapter(segmentModelList, this);
        segmentsRecyclerView.setAdapter(segmentsAdapter);
    }

    private void fillAdapterWithData() {
        segmentModelList.addAll(mRoute.getSegments());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSegmentClick(int position, View v) {
        //handle on segment click
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable SegmentListAdapter.DataObjectHolder holder, int position) {
        if (holder != null) {
            segmentCustomView.setSegment(segmentModelList.get(position));
            holder.showText();
        }
    }

    @Override
    public void onScrollStart(@NonNull SegmentListAdapter.DataObjectHolder holder, int position) {
        holder.hideText();
    }

    @Override
    public void onScroll(float position, int currentIndex, int newIndex, @Nullable SegmentListAdapter.DataObjectHolder currentHolder,
            @Nullable SegmentListAdapter.DataObjectHolder newHolder) {

        Segment current = segmentModelList.get(currentIndex);
        if (newIndex >= 0 && newIndex < segmentsRecyclerView.getAdapter().getItemCount()) {
            Segment next = segmentModelList.get(newIndex);
            segmentCustomView.onScroll(1f - Math.abs(position), current, next);
        }
    }

    @Override
    public void onScrollEnd(@NonNull SegmentListAdapter.DataObjectHolder currentItemHolder, int adapterPosition) {
    }
}
