package com.dijkstra.commonrecyclerview;

/**
 * @Description: 感受器信息
 * @Author: maoshenbo
 * @Date: 2018/11/6 11:46
 * @Version: 1.0
 */
public class SensorInfo {

    public String type;//类型
    public String name;//名字
    public int icon;//图片

    public SensorInfo(String type, String name, int icon) {
        this.type = type;
        this.name = name;
        this.icon = icon;
    }
}
