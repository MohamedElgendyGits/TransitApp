package com.android.transitapp.main.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.transitapp.R;
import com.android.transitapp.base.view.BaseActivity;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.details.DetailActivity;
import com.android.transitapp.main.view.adapter.RouteListAdapter;
import com.android.transitapp.main.viewmodel.MainActivityViewModel;
import com.android.transitapp.main.viewmodel.MainViewModelFactory;
import com.android.transitapp.utils.InjectorUtils;
import com.android.transitapp.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.transitapp.application.TransitConstants.DETAIL_KEY;

public class MainActivity extends BaseActivity implements LifecycleOwner , RouteListAdapter.RouteClickListener{

    @BindView(R.id.routes_recycler_view)
    RecyclerView routesRecyclerView;
    @BindView(R.id.progressBar_loading_indicator)
    ProgressBar mLoadingIndicator;

    // declare routes list components
    private RecyclerView.Adapter routesAdapter;
    private RecyclerView.LayoutManager routesLayoutManager;
    private ArrayList<Route> routeModelList;

    // declare the viewModel for this activity
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeViews();
        initializeViewModel();
    }

    /**
     * This method is for initializing all the views.
     */
    private void initializeViews() {

        // initialize recyclerView
        routesRecyclerView.setHasFixedSize(true);
        routesLayoutManager = new LinearLayoutManager(this);
        routesRecyclerView.setLayoutManager(routesLayoutManager);
        routeModelList = new ArrayList<>();
        routesAdapter = new RouteListAdapter(routeModelList, this);
        routesRecyclerView.setAdapter(routesAdapter);
    }

    /**
     * This method is for initializing the View Model by using the injector class factory and start observer on any changes.
     */
    private void initializeViewModel() {

        showLoading();
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        mViewModel.getRoutes().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> routeModels) {
                showRoutesDataView();
                loadRoutes(routeModels);
            }
        });
    }

    /**
     * This method is for load routes and update the recycler view.
     */
    private void loadRoutes(List<Route> routeModels) {
        routeModelList.clear();
        routesAdapter.notifyDataSetChanged();

        routeModelList.addAll(routeModels);
        routesAdapter.notifyDataSetChanged();
    }

    /**
     * This method is for responding to clicks from route list.
     */
    @Override
    public void onRouteClick(int position, View v) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Route route = routeModelList.get(position);
        detailIntent.putExtra(DETAIL_KEY, JsonUtil.objectToString(route));
        startActivity(detailIntent);
    }

    /**
     * This method will make the View for the routes data visible and hide the loading indicator.
     */
    private void showRoutesDataView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        routesRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the loading indicator visible and hide the route View
     */
    private void showLoading() {
        routesRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }
}