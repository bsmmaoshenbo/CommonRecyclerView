package com.dijkstra.commonrecyclerview;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 获取数据
 * @Author: maoshenbo
 * @Date: 2018/11/6 11:45
 * @Version: 1.0
 */
public class RRepository {

    public LiveData<List<SensorInfo>> getSensorData() {
        MutableLiveData<List<SensorInfo>> liveData = new MutableLiveData<>();
        final List<SensorInfo> infoList = new ArrayList<>();
        final SensorInfo info = new SensorInfo("orientation", "方向传感器", R.mipmap.ic_launcher_round);
        infoList.add(info);
        SensorInfo gyroscopeInfo = new SensorInfo("gyroscope", "陀螺仪", R.mipmap.ic_launcher_round);
        infoList.add(gyroscopeInfo);
        SensorInfo magnetic_fieldInfo = new SensorInfo("magnetic_field", "磁场强度", R.mipmap.ic_launcher_round);
        infoList.add(magnetic_fieldInfo);
        SensorInfo gravityInfo = new SensorInfo("gravity", "重力", R.mipmap.ic_launcher_round);
        infoList.add(gravityInfo);
        SensorInfo accelerationInfo = new SensorInfo("acceleration", "线性加速度", R.mipmap.ic_launcher_round);
        infoList.add(accelerationInfo);
        SensorInfo ambient_temperatureInfo = new SensorInfo("ambient_temperature", "温度", R.mipmap.ic_launcher_round);
        infoList.add(ambient_temperatureInfo);
        SensorInfo lightInfo = new SensorInfo("light", "光线强度", R.mipmap.ic_launcher_round);
        infoList.add(lightInfo);
        SensorInfo pressureInfo = new SensorInfo("type_pressure", "压力传感器", R.mipmap.ic_launcher_round);
        infoList.add(pressureInfo);
        liveData.setValue(infoList);
        return liveData;
    }
}
