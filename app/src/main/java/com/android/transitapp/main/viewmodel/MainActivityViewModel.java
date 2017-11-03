package com.android.transitapp.main.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.transitapp.data.entity.Route;
import com.android.transitapp.main.model.RouteRepository;
import com.android.transitapp.main.view.MainActivity;

import java.util.List;

/**
 * Created by Mohamed Elgendy.
 */

/**
 * {@link ViewModel} for {@link MainActivity}
 */
public class MainActivityViewModel extends ViewModel {

    private final RouteRepository mRepository;
    private final LiveData<List<Route>> mRoutes;

    public MainActivityViewModel(RouteRepository repository) {
        mRepository = repository;
        mRoutes = mRepository.getAllRoutes();
    }

    public LiveData<List<Route>> getRoutes() {
        return mRoutes;
    }
}

