package com.android.transitapp.main.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.transitapp.main.model.RouteRepository;

/**
 * Created by Mohamed Elgendy.
 */

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RouteRepository}
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RouteRepository mRepository;

    public MainViewModelFactory(RouteRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
