package com.carpool.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class RoomEntity implements Serializable{

    private int id;
    private String roomname;
    private String startPoint;
    private String endPoint;
    private int numberLimit;
    private int currentNums;
    private Timestamp createTime;
    private Date startTime;
    private RoomState state;

    private Collection<ChatRecordEntity> chatRecords;
    private UserEntity host;
    private UserEntity payer;
    private JourneyEntity journey;
    private Collection<UserEntity> userParticipate;

    @Column(name = "roomnote")
    public String getNote() {
        return note;
    }

    public void setNote(String roomNote) {
        this.note = roomNote;
    }

    private String note;

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
    @Column(name = "roomname", nullable = false, length = 10)
    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
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
    @Column(name = "endPoint", nullable = false, length = 10)
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Basic
    @Column(name = "numberLimit", nullable = false)
    public int getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(int numberLimit) {
        this.numberLimit = numberLimit;
    }

    @Basic
    @Column(name = "currentNums", nullable = false)
    public int getCurrentNums() {
        return currentNums;
    }

    public void setCurrentNums(int currentNums) {
        this.currentNums = currentNums;
    }

    @Basic
    @Column(name = "createTime", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "startTime", nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startime) {
        this.startTime = startime;
    }

    @Basic
    @Column(name = "state", nullable = false)

    @Enumerated(EnumType.STRING)
    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (numberLimit != that.numberLimit) return false;
        if (currentNums != that.currentNums) return false;
        if (state != that.state) return false;
        if (roomname != null ? !roomname.equals(that.roomname) : that.roomname != null) return false;
        if (startPoint != null ? !startPoint.equals(that.startPoint) : that.startPoint != null) return false;
        if (endPoint != null ? !endPoint.equals(that.endPoint) : that.endPoint != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomname != null ? roomname.hashCode() : 0);
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        result = 31 * result + numberLimit;
        result = 31 * result + currentNums;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "room")
    public Collection<ChatRecordEntity> getChatRecords() {
        return chatRecords;
    }

    public void setChatRecords(Collection<ChatRecordEntity> chatRecords) {
        this.chatRecords = chatRecords;
    }

    @ManyToOne
    @JoinColumn(name = "host", referencedColumnName = "id")
    public UserEntity getHost() {
        return host;
    }

    public void setHost(UserEntity host) {
        this.host = host;
    }

    @ManyToOne
    @JoinColumn(name = "payuserId", referencedColumnName = "id")
    public UserEntity getPayer() {
        return payer;
    }

    public void setPayer(UserEntity payer) {
        this.payer = payer;
    }

    @OneToOne(mappedBy = "room")
    public JourneyEntity getJourney() {
        return  journey;
    }


    public void setJourney(JourneyEntity journey) {
        this.journey = journey;
    }


    @ManyToMany(mappedBy = "userParticipateRooms", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<UserEntity> getUserParticipate() {
        return userParticipate;
    }

    public void setUserParticipate(Collection<UserEntity> userParticipate) {
        this.userParticipate = userParticipate;
    }


}
