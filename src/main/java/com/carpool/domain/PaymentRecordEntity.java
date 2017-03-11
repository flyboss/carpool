package com.carpool.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class PaymentRecordEntity {
    private int id;
    private byte state;
    private double payments;
    private Timestamp time;
    private UserEntity targetUser;
    private UserEntity sourceUser;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "payments", nullable = false, precision = 0)
    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentRecordEntity that = (PaymentRecordEntity) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (Double.compare(that.payments, payments) != 0) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (int) state;
        temp = Double.doubleToLongBits(payments);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "targetUserid", referencedColumnName = "id")
    public UserEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserEntity targetUser) {
        this.targetUser = targetUser;
    }

    @ManyToOne
    @JoinColumn(name = "sourceUserid", referencedColumnName = "id")
    public UserEntity getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(UserEntity sourceUser) {
        this.sourceUser = sourceUser;
    }
}
