package com.android.transitapp.details;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.transitapp.R;
import com.android.transitapp.application.TransitConstants;
import com.android.transitapp.base.view.BaseActivity;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.details.adapter.SegmentListAdapter;
import com.android.transitapp.main.view.adapter.RouteListAdapter;
import com.android.transitapp.utils.JsonUtil;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements LifecycleOwner , SegmentListAdapter.SegmentClickListener {

    @BindView(R.id.segments_recycler_view)
    RecyclerView segmentsRecyclerView;

    private Route mRoute;

    // declare segments list components
    private RecyclerView.Adapter segmentsAdapter;
    private RecyclerView.LayoutManager segmentsLayoutManager;
    private ArrayList<Segment> segmentModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);



        if(getIntent().getExtras() != null) {
            String routeString = getIntent().getExtras().getString(TransitConstants.DETAIL_KEY);
            mRoute = JsonUtil.parseJson(routeString,Route.class);
        }

        initializeViews();
    }

    private void initializeViews() {

        // initialize recyclerView
        segmentsRecyclerView.setHasFixedSize(true);
        segmentsLayoutManager = new LinearLayoutManager(this);
        //segmentsRecyclerView.setLayoutManager(segmentsLayoutManager);
        // ** replacing classical layout manager with Carouse layout manager
        // CarouselLayoutManager integration with recycler view
        final CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(5);
        segmentsRecyclerView.setLayoutManager(carouselLayoutManager);


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
}
