package com.carpool.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class JourneyEntity implements Serializable{
    private int id;
    private Date startTime;
    private String startPoint;
    private String endPoint;
    private int peerNums;
    private Collection<CommentEntity> comments;
    private RoomEntity room;

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
    @Column(name = "startPoint", nullable = false, length = 20)
    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    @Basic
    @Column(name = "endPoint", nullable = false, length = 20)
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Basic
    @Column(name = "peerNums", nullable = false)
    public int getPeerNums() {
        return peerNums;
    }

    public void setPeerNums(int peerNums) {
        this.peerNums = peerNums;
    }


    @Basic
    @Column(name = "startTime", nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JourneyEntity that = (JourneyEntity) o;

        if (id != that.id) return false;
        if (peerNums != that.peerNums) return false;
        if (startPoint != null ? !startPoint.equals(that.startPoint) : that.startPoint != null) return false;
        if (endPoint != null ? !endPoint.equals(that.endPoint) : that.endPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        result = 31 * result + peerNums;
        return result;
    }

    @OneToMany(mappedBy = "journey")
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentEntity> comments) {
        this.comments = comments;
    }

    @OneToOne
    @JoinColumn(name = "roomid", referencedColumnName = "id", nullable = false)
    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
