package com.carpool.domain;

import javax.persistence.*;

/**
 * Created by deado on 2016/12/20.
 */

@Entity
@Table
public class UserUnreceivedChatRecord {
    private Long id;
    private String userId;
    private ChatRecordEntity chatRecordEntity;

    public UserUnreceivedChatRecord() {
    }

    public UserUnreceivedChatRecord(Long id, String userId, ChatRecordEntity chatRecordEntity) {
        this.id = id;
        this.userId = userId;
        this.chatRecordEntity = chatRecordEntity;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "userid", nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @OneToOne
    @JoinColumn(name = "record", nullable = false, referencedColumnName = "id")
    public ChatRecordEntity getChatRecordEntity() {
        return chatRecordEntity;
    }

    public void setChatRecordEntity(ChatRecordEntity chatRecordEntity) {
        this.chatRecordEntity = chatRecordEntity;
    }
}
