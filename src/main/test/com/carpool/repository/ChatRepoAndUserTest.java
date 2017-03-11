package com.carpool.repository;

import com.carpool.domain.ChatRecordEntity;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import com.carpool.website.dao.ChatRecordRepository;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class ChatRepoAndUserTest {
    @Autowired
    ChatRecordRepository chatRecordRepository;
    @Autowired
    RoomEntityRepository roomEntityRepository;
    @Autowired
    UserEntityRepository userEntityRepository;

    Pageable pageable = new Pageable() {
        public int getPageNumber() {
            return 0;
        }

        public int getPageSize() {
            return 0;
        }

        public int getOffset() {
            return 0;
        }

        public Sort getSort() {
            return null;
        }

        public Pageable next() {
            return null;
        }

        public Pageable previousOrFirst() {
            return null;
        }

        public Pageable first() {
            return null;
        }

        public boolean hasPrevious() {
            return false;
        }
    };

    @Test
    public void saveTest(){

        //save userEntity
        UserEntity userEntity = new UserEntity("testuserid", "testname", "testpw", (byte)1, 5.0, "testalipay", 10, "testQQ", "testWechat", null);
        this.userEntityRepository.save(userEntity);

        //save room
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomname("test");
        roomEntity.setStartPoint("testStartPoint");
        roomEntity.setEndPoint("test");
        roomEntity.setNumberLimit(10);
        roomEntity.setCurrentNums(5);
        roomEntity.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        roomEntity.setStartTime(new java.sql.Date(new java.util.Date().getTime()));
        roomEntity.setState(RoomState.UNLOCKED);
        this.roomEntityRepository.save(roomEntity);


        ChatRecordEntity chatRecordEntity = new ChatRecordEntity();

        chatRecordEntity.setId(10);
        chatRecordEntity.setCommenttime(new Date( new Date().getTime()));
        chatRecordEntity.setCommenttext("testcomment");
        chatRecordEntity.setSender(userEntity);
        chatRecordEntity.setRoom(this.roomEntityRepository.findByRoomname("test"));
        this.chatRecordRepository.save(chatRecordEntity);

    }

    @Test
    @Transactional
    public void ChatRecordRepoTest(){
        //find ways test
        Page<ChatRecordEntity> ret = this.chatRecordRepository.findByCommenttime(new Date( new Date().getTime()),this.pageable);
        ChatRecordEntity cre = ret.iterator().next();
        int reTest = this.chatRecordRepository.getRoomById(cre.getId());
        String ueTest = this.chatRecordRepository.getSenderById(cre.getId());
//        Page<ChatRecordEntity> aceTest = this.chatRecordRepository.getAllChatOfRoom(1, this.pageable);
        Page<ChatRecordEntity> aceuTest = this.chatRecordRepository.getAllChatOfUser(cre.getSender().getId(),this.pageable);

        //delete
        //int deByIdRes=this.chatRecordRepository.deleteChatRecordById(cre.getId());
        //int deByRoomRes=this.chatRecordRepository.deleteChatRecordByRoom(reTest);
        //int deBySender=this.chatRecordRepository.deleteChatRecordBySenderId(cre.getSender().getId());
        System.out.println("a");

    }

    @Test
    @Transactional
    public void UserRepoTest(){
        UserEntity ueres=this.userEntityRepository.findById("testuserid");
        Page<UserEntity> ueresPageByName=this.userEntityRepository.findByUsername("testname",this.pageable);
        Page<UserEntity> ueresPageByQA=this.userEntityRepository.findByQqAccount("testQQ", this.pageable);
        Page<UserEntity> ueresPageByWC=this.userEntityRepository.findByWechatAccount("testWechat", this.pageable);

        //find ways
        Integer genders = this.userEntityRepository.countIdByGender((byte)1);
        Integer coins = this.userEntityRepository.getCoinsById("testuserid");
        String  alipay = this.userEntityRepository.getAlipayById("testuserid");
        String  qqAccount = this.userEntityRepository.getQQAccountById("testuserid");
        String  wechat = this.userEntityRepository.getWechatByid("testuserid");
        String  pw = this.userEntityRepository.getPwById("testuserid");
        Byte    gender = this.userEntityRepository.getGenderById("testuserid");
        Double  credit = this.userEntityRepository.getCreditById("testuserid");

        //delete
        //Integer deUser = this.userEntityRepository.deleteUserById("testuserid");
        Integer setUserName = this.userEntityRepository.setUsername("newname", "testuserid");
        Integer setUserAlipay = this.userEntityRepository.setUserAlipay("newAlipay", "testuserid");
        Integer  setUserPw    = this.userEntityRepository.setUserPw("newPw","testuserid");
        System.out.print("a");



    }



}