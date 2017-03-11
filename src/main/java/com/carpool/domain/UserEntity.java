package com.carpool.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;


/**
 * Created by qi on 2016/11/26.
 */
@Entity
@Table
public class UserEntity implements Serializable {
    private String id;
    private String username;
    private String password;
    private byte gender;
    private double credit;
    private String alipay;
    private int coins;
    private Integer receivedComments;
    private String photo;
    private Integer carpoolingCount;
    private String qqAccount;
    private String wechatAccount;
    private Collection<ChatRecordEntity> sendedChatRecord;
    private Collection<CommentEntity> commentsRecieved;
    private Collection<CommentEntity> commentsSended;
    private Collection<PaymentRecordEntity> paymentRecievedRecords;
    private Collection<PaymentRecordEntity> paymentSendRecords;
    private Collection<RoomEntity> ownRoom;
    private Collection<RoomEntity> hasPaysRoom;
    private Collection<RoomEntity> userParticipateRooms;

    public UserEntity() {
    }

    public UserEntity(String id, String username, String password, byte gender, double credit, String alipay,
                      int coins, String qqAccount, String wechatAccount, String photo) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.credit = credit;
        this.alipay = alipay;
        this.coins = coins;
        this.qqAccount = qqAccount;
        this.wechatAccount = wechatAccount;
        //pw encryption
        this.password = password;
        if (photo.equals("") || photo == null)
            this.photo = "/static/images/default.jpg";
        else
            this.photo = photo;
    }

    @Column(nullable = false)
    public Integer getReceivedComments() {
        return receivedComments;
    }

    public void setReceivedComments(Integer receivedComments) {
        this.receivedComments = receivedComments;
    }

    @Column
    public Integer getCarpoolingCount() {
        return carpoolingCount;
    }

    public void setCarpoolingCount(Integer carpoolingCount) {
        this.carpoolingCount = carpoolingCount;
    }

    @Id
    @Column(name = "id", nullable = false, length = 10, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 10)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Basic
    @Column(name = "password", nullable = false, length = 225)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    @Basic
    @Column(name = "gender", nullable = false)
    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
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
    //支付功能取消所以可以为空
    @Column(name = "alipay", nullable = false, length = 20)
    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    @Basic
    @Column(name = "coins", nullable = false)
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Basic
    @Column(name = "QQ_account", nullable = false, length = 20)
    public String getQqAccount() {
        return qqAccount;
    }

    public void setQqAccount(String qqAccount) {
        this.qqAccount = qqAccount;
    }

    @Basic
    @Column(name = "wechat_account", nullable = false, length = 20)
    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    @Basic
    @Column(name = "photo", nullable = false, length = 200)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (gender != that.gender) return false;
        if (Double.compare(that.credit, credit) != 0) return false;
        if (coins != that.coins) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (alipay != null ? !alipay.equals(that.alipay) : that.alipay != null) return false;
        if (qqAccount != null ? !qqAccount.equals(that.qqAccount) : that.qqAccount != null) return false;
        if (wechatAccount != null ? !wechatAccount.equals(that.wechatAccount) : that.wechatAccount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) gender;
        temp = Double.doubleToLongBits(credit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (alipay != null ? alipay.hashCode() : 0);
        result = 31 * result + coins;
        result = 31 * result + (qqAccount != null ? qqAccount.hashCode() : 0);
        result = 31 * result + (wechatAccount != null ? wechatAccount.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sender")
    public Collection<ChatRecordEntity> getSendedChatRecord() {
        return sendedChatRecord;
    }

    public void setSendedChatRecord(Collection<ChatRecordEntity> chatrecordsById) {
        this.sendedChatRecord = chatrecordsById;
    }


    @OneToMany(mappedBy = "targetUser")
    public Collection<CommentEntity> getCommentsRecieved() {
        return commentsRecieved;
    }

    public void setCommentsRecieved(Collection<CommentEntity> commentsById) {
        this.commentsRecieved = commentsById;
    }

    @OneToMany(mappedBy = "sourceUser")
    public Collection<CommentEntity> getCommentsSended() {
        return commentsSended;
    }

    private void setCommentsSended(Collection<CommentEntity> commentsSended) {
        this.commentsSended = commentsSended;
    }

    @OneToMany(mappedBy = "targetUser")
    public Collection<PaymentRecordEntity> getPaymentRecievedRecords() {
        return paymentRecievedRecords;
    }

    public void setPaymentRecievedRecords(Collection<PaymentRecordEntity> paymentrecordsById) {
        this.paymentRecievedRecords = paymentrecordsById;
    }

    @OneToMany(mappedBy = "sourceUser")
    public Collection<PaymentRecordEntity> getPaymentSendRecords() {
        return paymentSendRecords;
    }

    public void setPaymentSendRecords(Collection<PaymentRecordEntity> paymentrecordsById_0) {
        this.paymentSendRecords = paymentrecordsById_0;
    }

    @OneToMany(mappedBy = "host")
    public Collection<RoomEntity> getOwnRoom() {
        return ownRoom;
    }

    public void setOwnRoom(Collection<RoomEntity> roomsById) {
        this.ownRoom = roomsById;
    }

    @OneToMany(mappedBy = "payer")
    public Collection<RoomEntity> getHasPaysRoom() {
        return hasPaysRoom;
    }

    public void setHasPaysRoom(Collection<RoomEntity> hasPaysRoom) {
        this.hasPaysRoom = hasPaysRoom;

    }

    @ManyToMany
    @JoinTable(name = "user_rooms",
            joinColumns = {@JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "rid")})
    public Collection<RoomEntity> getUserParticipateRooms() {
        return userParticipateRooms;
    }

    public void setUserParticipateRooms(Collection<RoomEntity> userParticipateRooms) {
        this.userParticipateRooms = userParticipateRooms;
    }
}
