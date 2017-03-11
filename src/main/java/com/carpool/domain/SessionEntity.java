package com.carpool.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by deado on 2016/12/18.
 */

@Entity
@Table
public class SessionEntity implements Serializable {

    private String seriesId;
    private String userId;
    private String token;
    private Timestamp last_used;

    public SessionEntity() {
    }

    public SessionEntity(String seriesId, String userId, String token, Timestamp timestamp) {
        this.seriesId = seriesId;
        this.userId = userId;
        this.token = token;
        this.last_used = timestamp;
    }

    @Id
    @Column(name = "seriesid", nullable = false)
    public String getSeriesId() {
        return seriesId;
    }


    public void setSeriesId(String seriedId) {
        this.seriesId = seriedId;
    }

    @Column(name = "userid", nullable = false)
    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "tokenid", nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "last_used", nullable = false)
    public Timestamp getLast_used() {
        return last_used;
    }

    public void setLast_used(Timestamp last_used) {
        this.last_used = last_used;
    }
}
