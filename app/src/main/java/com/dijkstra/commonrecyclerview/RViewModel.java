package com.dijkstra.commonrecyclerview;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * @Description: ViewModel
 * @Author: maoshenbo
 * @Date: 2018/11/6 11:42
 * @Version: 1.0
 */
public class RViewModel extends ViewModel {

    RRepository mRepository = new RRepository();

    private MutableLiveData<Boolean> mLiveData = new MutableLiveData<>();

    public LiveData<List<SensorInfo>> getCenterLiveData = Transformations.switchMap(mLiveData, new Function<Boolean, LiveData<List<SensorInfo>>>() {
        @Override
        public LiveData<List<SensorInfo>> apply(Boolean input) {
            return mRepository.getSensorData();
        }
    });

    public MutableLiveData<Boolean> getLiveData() {
        return mLiveData;
    }
}
