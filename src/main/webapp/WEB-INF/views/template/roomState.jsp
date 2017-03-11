<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.carpool.domain.RoomState" %>
<c:set var="ROOM_STATE_END" value='<%=RoomState.END%>'/>
<c:set var="ROOM_STATE_UNLOCKED" value='<%=RoomState.UNLOCKED%>'/>
<c:set var="ROOM_STATE_LOCKED" value='<%=RoomState.LOCKED%>'/>
<c:set var="ROOM_STATE_STARTED" value='<%=RoomState.STARTED%>'/>