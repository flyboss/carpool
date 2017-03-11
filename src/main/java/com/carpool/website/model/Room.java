package com.carpool.website.model;

import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Project: Carpool
 * Package: com.carpool.website.model
 * Author:  Novemser
 * 2016/12/1
 */

public class Room implements Serializable {

    private int id;

    @NotEmpty
    private String roomname;
    @NotEmpty
    private String startPoint;
    @NotEmpty
    private String endPoint;

    @Min(2)
    @Max(10)
    private Integer numberLimit;

    private Integer currentNums;

    private String note;

    @NotEmpty
    private String startDate;
    @NotEmpty
    private String startTime;

    private String createTime;

    private RoomState state;

    private UserEntity host;

    private UserEntity payer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
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

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }

    public int getCurrentNums() {
        return currentNums;
    }

    public void setCurrentNums(int currentNums) {
        this.currentNums = currentNums;
    }

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

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public UserEntity getHost() {
        return host;
    }

    public void setHost(UserEntity host) {
        this.host = host;
    }

    public UserEntity getPayer() {
        return payer;
    }

    public void setPayer(UserEntity payer) {
        this.payer = payer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCurrentNums(Integer currentNums) {
        this.currentNums = currentNums;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
