package com.carpool.website.model;

import com.carpool.domain.JourneyEntity;
import com.carpool.domain.UserEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by qi on 2016/12/1.
 */
public class Comment implements Serializable{
    private int id;
    private String commentText;
    private double credit;
    private Date commentTime;
    private UserEntity targetUser;
    private UserEntity sourceUser;
    private JourneyEntity journey;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public UserEntity getSourceUser() {
        
        return sourceUser;
    }

    public void setSourceUser(UserEntity sourceUser) {
        this.sourceUser = sourceUser;
    }

    public UserEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserEntity targetUser) {
        this.targetUser = targetUser;
    }

    public JourneyEntity getJourney() {
        return journey;
    }

    public void setJourney(JourneyEntity journey) {
        this.journey = journey;
    }

}
