package com.carpool.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class CommentEntity {
    private int id;
    private String commentText;
    private double credit;
    private Date commentTime;
    private UserEntity sourceUser;
    private UserEntity targetUser;
    private JourneyEntity journey;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "commentText",nullable = false)
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Basic
    @Column(name = "credit", nullable = false, precision = 0)
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "commentTime", nullable = false)
    public Date getCommentTime() {
        return (commentTime==null)?new Date():commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.credit, credit) != 0) return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        temp = Double.doubleToLongBits(credit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sourceUserid", referencedColumnName = "id")
    public UserEntity getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(UserEntity sourceUserd) {
        this.sourceUser =  sourceUserd;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "targetUserid", referencedColumnName = "id")
    public UserEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserEntity targetUser) {
        this.targetUser = targetUser;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "journeyid", referencedColumnName = "id", nullable = false)
    public JourneyEntity getJourney() {
        return journey;
    }

    public void setJourney(JourneyEntity journey) {
        this.journey = journey;
    }
}
