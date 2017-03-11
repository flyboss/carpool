package com.carpool.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class ChatRecordEntity {
    private int id;
    private Date commenttime;
    private String commenttext;
    private UserEntity sender;
    private RoomEntity room;

    public ChatRecordEntity() {
    }

    public ChatRecordEntity(Date commenttime, String commenttext, UserEntity sender, RoomEntity room) {
        this.commenttime = commenttime;
        this.commenttext = commenttext;
        this.sender = sender;
        this.room = room;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "commenttime", nullable = false)
    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commentTime) {
        this.commenttime = commentTime;
    }

    @Basic
    @Column(name = "commenttext", nullable = false)
    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commentText) {
        this.commenttext = commentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatRecordEntity that = (ChatRecordEntity) o;

        if (id != that.id) return false;
        if (commenttime != null ? !commenttime.equals(that.commenttime) : that.commenttime != null) return false;
        if (commenttext != null ? !commenttext.equals(that.commenttext) : that.commenttext != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (commenttime != null ? commenttime.hashCode() : 0);
        result = 31 * result + (commenttext != null ? commenttext.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "senderid", referencedColumnName = "id")
    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sourceUser) {
        this.sender = sourceUser;
    }



    @ManyToOne
    @JoinColumn(name = "roomid", referencedColumnName = "id")
    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

}
