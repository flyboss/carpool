package com.carpool.website.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Project: Carpool
 * Package: com.carpool.website.model
 * Author:  Novemser
 * 2016/12/11
 */
public class RoomSelection {
    @NotEmpty
    private String startDate;

    private String startTime;
    @NotEmpty
    private String startPoint;
    @NotEmpty
    private String endPoint;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
