package com.carpool.website.service;

import com.carpool.configuration.GlobalConstants;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import com.carpool.exception.*;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import com.carpool.website.model.Room;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project: Carpool
 * Package: com.carpool.website.service
 * Author:  Novemser
 * 2016/11/29
 */

@Service
public class RoomService {

    @Autowired
    private RoomEntityRepository roomEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Transactional
    public RoomEntity createRoom(String roomName,
                                 String startPoint,
                                 String endPoint,
                                 int numberLimit,
                                 Date startTime,
                                 String hostId,
                                 String note) throws Exception {

        UserEntity userEntity = userEntityRepository.findOne(hostId);
        if (null == userEntity)
            throw new UserNullException("createRoom", "用户不存在！");

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomname(roomName);
        roomEntity.setEndPoint(endPoint);
        roomEntity.setNumberLimit(numberLimit);
        // 目前只有房主一个人
        roomEntity.setCurrentNums(1);
        roomEntity.setStartPoint(startPoint);
        roomEntity.setStartTime(startTime);
        roomEntity.setState(RoomState.UNLOCKED);
        roomEntity.setNote(note);

        // 获取系统当前时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        roomEntity.setCreateTime(timestamp);

        roomEntity.setHost(userEntity);
        // 把房主加到房间里
        // 因为是UserEntity维护多对多表
        // 所以只用修改User里的多方表就可以了
        userEntity.getUserParticipateRooms().add(roomEntity);

        roomEntityRepository.saveAndFlush(roomEntity);

        return roomEntity;
    }

    public List<RoomEntity> listUserRooms(String userId) {
        UserEntity userEntity = userEntityRepository.findOne(userId);

        // 删除所有无用的房间
        List<RoomEntity> entities = new ArrayList<>(roomEntityRepository.findUserNotEndRooms());

        List<RoomEntity> roomsToAdd = new ArrayList<>();
        for (RoomEntity entity : entities) {
            if (entity.getUserParticipate().contains(userEntity)) {
                roomsToAdd.add(entity);
            }
        }

        return roomsToAdd;
    }

    public Integer getRoomPageCount() {
        return (int) Math.ceil(roomEntityRepository.getRoomCount() * 1.0 / GlobalConstants.HOME_CARPOOL_PAGE_SIZE);
    }

    public Integer getRoomsCount() {
        return roomEntityRepository.getRoomCount();
    }

    public RoomEntity findById(int id) {
        RoomEntity entity = roomEntityRepository.findOne(id);

        if (null == entity)
            throw new ResourceNotFoundException();

        return entity;
    }

    @Transactional
    public synchronized Room addUserToRoom(int roomId, String userId) throws Exception {
        UserEntity user = userEntityRepository.findOne(userId);
        if (null == user)
            throw new UserNullException("addUserToRoom", "用户不存在！");

        RoomEntity room = roomEntityRepository.findOne(roomId);
        if (null == room)
            throw new RoomNullException("addUserToRoom", "房间不存在！");

        if (user.getUserParticipateRooms().contains(room))
            throw new DuplicateJoiningRoomException("加入失败", "您已加入房间，请勿重复加入");

        int currentNum = room.getCurrentNums();
        int maxNum = room.getNumberLimit();
        if (currentNum >= maxNum)
            throw new RoomFullException("加入失败", "房间已满");

        room.setCurrentNums(currentNum + 1);
        user.getUserParticipateRooms().add(room);
        roomEntityRepository.save(room);

        Room roomModel = new Room();
        roomModel.setNote(room.getNote());
        roomModel.setStartPoint(room.getStartPoint());
        roomModel.setEndPoint(room.getEndPoint());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        roomModel.setStartTime(sf.format(room.getStartTime()));
        roomModel.setNumberLimit(room.getNumberLimit());

        return roomModel;
    }

    @Transactional
    public synchronized void removeUserFromRoom(int roomId, String userId) {
        UserEntity user = userEntityRepository.findOne(userId);
        if (null == user)
            throw new UserNullException("removeUserFromRoom", "用户不存在！");

        RoomEntity room = roomEntityRepository.findOne(roomId);
        if (null == room)
            throw new RoomNullException("removeUserFromRoom", "房间不存在！");

        int currentNum = room.getCurrentNums();

        if (currentNum <= 0)
            throw new RoomFullException("退出失败", "房间已空");

        room.setCurrentNums(currentNum - 1);
        user.getUserParticipateRooms().remove(room);
        room.getUserParticipate().remove(user);
        userEntityRepository.saveAndFlush(user);
        roomEntityRepository.saveAndFlush(room);
    }

    /***
     * 模糊搜索房间
     *
     * @param startPoint 起点，模糊搜索
     * @param endPoint 终点，模糊搜索
     * @param from 开始时间
     * @param offset 以开始时间为基准，显示往后多少天的结果
     * @return 找到的结果
     * @throws ParseException 解析错误
     */
    @SuppressWarnings("deprecation")
    public Page<RoomEntity> listRoomsInDays(String startPoint, String endPoint,
                                            Date from, int offset, int page, int size) throws ParseException {
        // TODO:
        // 使用LocationUtil去检查point是否合法
        // 会调用百度API
        Pageable p = new PageRequest(page, size);
        from = new Date(from.getYear(), from.getMonth(), from.getDate());
        Date to = DateUtils.addDays(from, offset);
        return roomEntityRepository.findRoomStartEndPointLikeInDays(startPoint,
                endPoint,
                from, to, p);
    }

    @Transactional
    public int editCurrentUserNum(int num, RoomEntity entity) {
        if (entity.getNumberLimit() < num)
            return -1;

        entity.setCurrentNums(num);
        roomEntityRepository.saveAndFlush(entity);
        return num;
    }

    @Transactional
    public RoomEntity editRoom(Room room) {
        RoomEntity entity = roomEntityRepository.findOne(room.getId());
        if (null == entity)
            throw new RoomNullException("修改房间信息错误", "房间不存在");

        entity.setStartPoint(room.getStartPoint());
        entity.setEndPoint(room.getEndPoint());
        entity.setRoomname(room.getRoomname());
        entity.setNumberLimit(room.getNumberLimit());
        entity.setNote(room.getNote());
        String startDate = room.getStartDate();
        String startTime = room.getStartTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateStartTime = null;
        try {
            dateStartTime = format.parse(startDate + " " + startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        entity.setStartTime(dateStartTime);
        entity.setState(room.getState());
        return entity;
    }

    public Page<RoomEntity> findRoom(Pageable pageable) {
        return roomEntityRepository.findAll(pageable);
    }

    public Page<RoomEntity> findRoom(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size,
                new Sort(
                        new Sort.Order(Sort.Direction.DESC, "state"),
                        new Sort.Order(Sort.Direction.DESC, "createTime")
                )
        );
        Page<RoomEntity> roomEntities = roomEntityRepository.findAll(pageRequest);
        Date now = new Date();
        // 检查房间是否过期
        // 过期自动设置成END
        for (RoomEntity roomEntity : roomEntities) {
            if (roomEntity.getStartTime().before(now)) {
                roomEntity.setState(RoomState.END);
                roomEntityRepository.save(roomEntity);
            }
        }

        return roomEntityRepository.findAll(pageRequest);
    }

    public void lockRoomById(int roomId) {
        changeRoomState(roomId, RoomState.LOCKED);
    }

    public void unLockRoomById(int roomId) {
        changeRoomState(roomId, RoomState.UNLOCKED);
    }

    public void startRoomById(int roomId) {
        changeRoomState(roomId, RoomState.STARTED);
    }

    public void endRoomById(int roomId) {
        changeRoomState(roomId, RoomState.END);
    }

    private void changeRoomState(int roomId, RoomState locked) {
        RoomEntity entity = roomEntityRepository.findOne(roomId);
        entity.setState(locked);
        roomEntityRepository.save(entity);
    }
}
