package com.android.transitapp.main.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.transitapp.base.data.entity.Route;
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

    /**
     <p>
     * ViewModel should not know about which method to retrieve routes either local or remotely
     * he should only ask the repository and it is responsibility to retrieve the routes from which data source
     */
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

