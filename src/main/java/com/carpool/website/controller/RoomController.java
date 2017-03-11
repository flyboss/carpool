package com.carpool.website.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carpool.configuration.GlobalConstants;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import com.carpool.exception.InternalErrorException;
import com.carpool.exception.PermissionDeniedException;
import com.carpool.exception.RoomNullException;
import com.carpool.exception.UserNullException;
import com.carpool.website.model.Room;
import com.carpool.website.model.RoomSelection;
import com.carpool.website.service.RoomService;
import com.carpool.website.service.RoomWebSocketHandler;
import com.carpool.website.service.UnreadWebSocketHandler;
import com.carpool.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Project: Carpool
 * Package: com.carpool.website.controller
 * Author:  Novemser
 * 2016/11/30
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    private UserService userService;

    @Resource
    private RoomWebSocketHandler roomWebSocketHandler;

    @Resource
    private UnreadWebSocketHandler unreadWebSocketHandler;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/select")
    public String selectMethod(ModelMap modelMap) {
        if (!modelMap.containsKey("room")) {
            Room room = new Room();
            modelMap.addAttribute("room", room);
        }
        return "home.select";
    }

    @GetMapping("/unlock/{roomId}")
    public String unlock(@PathVariable Integer roomId, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        if (null == entity)
            throw new RoomNullException("解锁失败", "房间不存在");

        // 只有房主可以锁定房间
        String userId = userService.getUserIdByCookie(request.getCookies());
        if (userId != null && userId.equals(entity.getHost().getId())) {
            roomService.unLockRoomById(entity.getId());
            return "redirect:/room/detail?roomId=" + entity.getId();
        } else
            return "main";
    }

    @GetMapping("/lock/{roomId}")
    public String lock(@PathVariable Integer roomId, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        if (null == entity)
            throw new RoomNullException("锁定失败", "房间不存在");

        // 只有房主可以锁定房间
        String userId = userService.getUserIdByCookie(request.getCookies());
        if (userId != null && userId.equals(entity.getHost().getId())) {
            roomService.lockRoomById(entity.getId());
            return "redirect:/room/detail?roomId=" + entity.getId();
        } else
            return "main";
    }

    @GetMapping("/detail")
    public String showDetail(@RequestParam int roomId, ModelMap modelMap, HttpServletRequest request) {

        RoomEntity entity = roomService.findById(roomId);

        // 验证是否超出总人数
        int currentNum = entity.getUserParticipate().size();
//        entity.setCurrentNums(currentNum);
        if (currentNum >= entity.getNumberLimit())
            modelMap.addAttribute("reachLimit", true);
        else
            modelMap.addAttribute("reachLimit", false);

        roomService.editCurrentUserNum(currentNum, entity);

        modelMap.addAttribute("room", entity);

        String userId = userService.getUserIdByCookie(request.getCookies());
        if (null == userId)
            throw new UserNullException("抱歉", "请按照正常流程登录");

        // 验证当前用户是不是房间的房主
        if (userId.equals(entity.getHost().getId()))
            modelMap.addAttribute("roomOwner", true);
        else
            modelMap.addAttribute("roomOwner", false);

        Collection<UserEntity> roomUsers = entity.getUserParticipate();

        modelMap.addAttribute("roomUsers", new ArrayList<>(roomUsers));
        // 已经加入
        if (entity.getUserParticipate().contains(userService.getUserById(userId)))
            modelMap.addAttribute("inRoom", true);
        else
            modelMap.addAttribute("inRoom", false);

        return "room.detail";
    }

    @GetMapping("/count/room")
    public
    @ResponseBody
    Integer getRoomCount() {
        return roomService.getRoomsCount();
    }

    @GetMapping("/count/page")
    public
    @ResponseBody
    Integer getRoomPageCount() {
        return roomService.getRoomPageCount();
    }

    @GetMapping("/create")
    public String saveNewRoom(ModelMap modelMap) {
        Room room = new Room();
        modelMap.addAttribute("room", room);
        return "room.create";
    }

    @GetMapping("/join")
    public String joinRoom(ModelMap modelMap) {
        RoomSelection roomSelection = new RoomSelection();
        modelMap.addAttribute("roomSelection", roomSelection);
        return "room.join";
    }

    @PostMapping("/join/search")
    public String joinRoom(@Valid RoomSelection room,
                           BindingResult bindingResult,
                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = GlobalConstants.HOME_CARPOOL_PAGE_SIZE_STR) Integer size,
                           ModelMap modelMap,
                           HttpServletRequest request) {
        request.setAttribute("id", "2");

        if (bindingResult.hasErrors()) {
            return "room.join";
        }
        RoomSelection selection = (RoomSelection) modelMap.get("roomSelection");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(selection.getStartDate());
            Page<RoomEntity> roomEntities = roomService.listRoomsInDays(selection.getStartPoint(), selection.getEndPoint(), date, 1
                    , page, size);

            modelMap.addAttribute("roomPage", roomEntities);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("pageCount", roomEntities.getTotalPages());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "room.search";
    }

    @GetMapping("/edit")
    public String editRoomInfo(@RequestParam Integer roomId, ModelMap modelMap, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        String userId = userService.getUserIdByCookie(request.getCookies());
        // 当前用户不是房主 拒绝修改 返回细节界面
        if (null == userId || !userId.equals(entity.getHost().getId()))
            return "room.detail";
        Room model = new Room();
        model.setId(roomId);
        model.setRoomname(entity.getRoomname());
        model.setCurrentNums(entity.getCurrentNums());
        Date startDate = entity.getStartTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.setStartDate(dateFormat.format(startDate));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        model.setStartTime(timeFormat.format(startDate));
        model.setNote(entity.getNote());
        model.setHost(entity.getHost());
        model.setState(entity.getState());
        Date createDate = new Date(entity.getCreateTime().getTime());
        SimpleDateFormat createTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.setCreateTime(createTimeFormat.format(createDate));
        model.setNumberLimit(entity.getNumberLimit());
        model.setStartPoint(entity.getStartPoint());
        model.setEndPoint(entity.getEndPoint());

        modelMap.addAttribute("room", model);

        return "room.edit";
    }

    @PostMapping("/edit")
    public String editRoomInfo(@Valid Room room, BindingResult result, HttpServletRequest request, ModelMap modelMap) {
        request.setAttribute("id", "2");


        if (result.hasErrors())
            return "room.edit";

        roomService.editRoom(room);
//        modelMap.addAttribute("room", entity);

        request.setAttribute("roomId", room.getId());
        return "redirect:/room/detail?roomId=" + room.getId();
    }


    @PostMapping("/create")
    public String saveNewRoomPost(@Valid Room room, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest request) {
        request.setAttribute("id", "2");
//        modelMap.addAttribute("room", room);

        if (bindingResult.hasErrors()) {
            return "room.create";
        } else {
            Date startTime = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                startTime = format.parse(room.getStartDate() + " " + room.getStartTime());

                String userId = userService.getUserIdByCookie(request.getCookies());

                if (userId == null || userId.equals(""))
                    throw new PermissionDeniedException(HttpStatus.FORBIDDEN + "", "你没有权限");

                RoomEntity entity = roomService.createRoom(room.getRoomname(),
                        room.getStartPoint(),
                        room.getEndPoint(),
                        room.getNumberLimit(),
                        startTime,
                        userId,
                        room.getNote()
                );
                Room roomModel = (Room) modelMap.get("room");
                roomModel.setId(entity.getId());
//                modelMap.addAttribute("room", entity);
            } catch (Exception e) {
                e.printStackTrace();
                throw new InternalErrorException("parse", e.getMessage());
            }
        }

        return "room.addSucceed";
    }

    @PostMapping("/unlock")
    @ResponseBody
    public ResponseEntity<?> unlockRoom(@RequestParam Integer roomId, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        String userId = userService.getUserIdByCookie(request.getCookies());
        ResponseEntity<String> responseEntity = new ResponseEntity<>("no", HttpStatus.UNAUTHORIZED);
        // 当前用户不是房主 拒绝修改 返回细节界面
        if (null == userId || !userId.equals(entity.getHost().getId()))
            return responseEntity;

        roomService.unLockRoomById(roomId);
        responseEntity = new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PostMapping("/end")
    @ResponseBody
    public ResponseEntity<?> deleteRoom(@RequestParam Integer roomId, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        String userId = userService.getUserIdByCookie(request.getCookies());
        ResponseEntity<String> responseEntity = new ResponseEntity<>("no", HttpStatus.UNAUTHORIZED);
        // 当前用户不是房主 拒绝修改 返回细节界面
        if (null == userId || !userId.equals(entity.getHost().getId()))
            return responseEntity;

        roomService.endRoomById(roomId);
        responseEntity = new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PostMapping("/leave")
    @ResponseBody
    public ResponseEntity<?> leaveRoom(@RequestParam Integer roomId, HttpServletRequest request) {
        RoomEntity entity = roomService.findById(roomId);
        String userId = userService.getUserIdByCookie(request.getCookies());
        ResponseEntity<String> responseEntity = new ResponseEntity<>("no", HttpStatus.UNAUTHORIZED);
        // 当前用户是房主 拒绝修改 返回细节界面
        if (userId.equals(entity.getHost().getId()))
            return responseEntity;

        roomService.removeUserFromRoom(roomId, userId);

        this.quitNotify(entity,userId, false);

        responseEntity = new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        return responseEntity;
    }


    private void quitNotify(RoomEntity entity, String userId, boolean add){
        String content;
        if(add){
            content = "加入了房间";
        }else{
            content = "退出了房间";
        }

        //向当前房间的剩余参与者发送消息
        //将系统消息存入消息表
        UserEntity quitUser = this.userService.getUserById(userId);
        Date time = new Date();

        //利用RoomWebSocketHandler发送系统消息
        //生成消息内容
        String year = Integer.toString(time.getYear());
        String month = Integer.toString(time.getMonth());
        String day = Integer.toString(time.getDay());
        String hour = Integer.toString(time.getHours());
        String minute = Integer.toString(time.getMinutes());
        String userProfileImg = userService.getUserProfileImgSrc("0000001");
        String msg = "{ \"type\":1, \"userid\":\""+"0000001" + "\", \"username\":\"" + "系统"
                + "\", \"room\":" + entity.getId() + ", \"year\":"+year + ", \"month\":"+ month + ",\"day\":" +day+ ",\"hour\":"
                + hour + ",\"minute\":" + minute + ",\"chatContent\":\"" + quitUser.getUsername() + content  + "\"," +
                "\"src\":\"" + userProfileImg.replace("_","") + "\"}";

        JSONObject msgJson = JSON.parseObject(msg);
        roomWebSocketHandler.handleMessageTypeOne(msgJson);
        unreadWebSocketHandler.pushMsgToOnlineRoomMembers(entity.getId());

    }

    @PostMapping("/user/join")
    public String addUserToRoom(@RequestParam Integer roomId, HttpServletRequest request, ModelMap modelMap) throws Exception {
        String userId = userService.getUserIdByCookie(request.getCookies());

        this.quitNotify(roomService.findById(roomId),userId,true);

        Room entity = roomService.addUserToRoom(roomId, userId);


        modelMap.addAttribute("room", entity);

        return "room.user.joinSucceed";
    }

    @GetMapping("/chat")
    public String joinChatRoom(@RequestParam Integer roomId, HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("room", roomService.findById(roomId));
        String userId = request.getRemoteUser();
        String username = this.userService.getUserById(userId).getUsername();

        modelMap.addAttribute("userid", userId);
        modelMap.addAttribute("username", username);

        return "room.chat";
    }



}
