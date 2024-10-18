
package com.example.codedemo;
import java.util.List;

public class CameraIdWhiteList {

    public String cameraId;
    public List<String> whiteList;

    public CameraIdWhiteList() {
    }

    public CameraIdWhiteList(String cameraId, List<String> whiteList) {
        this.cameraId = cameraId;
        this.whiteList = whiteList;
    }

    @Override
    public String toString() {
        return "CameraIdWhiteList{" +
                "cameraId='" + cameraId + '\'' +
                ", whiteList=" + whiteList +
                '}';
    }
}
