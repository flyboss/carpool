-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2016-11-29 03:45:18
-- 服务器版本： 5.7.16
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carpool`
--

-- --------------------------------------------------------

--
-- 表的结构 `chatrecord`
--

CREATE TABLE `chatrecord` (
  `id` int(10) NOT NULL,
  `senderid` varchar(20) DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment` text NOT NULL,
  `roomid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` int(10) NOT NULL,
  `sourceUserid` varchar(10) DEFAULT NULL,
  `targetUserid` varchar(10) DEFAULT NULL,
  `journeyid` int(10) NOT NULL,
  `comment` text,
  `credit` float NOT NULL,
  `commentTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `sourceUserid`, `targetUserid`, `journeyid`, `comment`, `credit`, `commentTime`) VALUES
(1, '1452778', '1452779', 2, NULL, 5, '2016-11-27 16:04:56'),
(4, '1452778', '1452779', 2, NULL, 5, '2016-11-27 16:34:29');

-- --------------------------------------------------------

--
-- 表的结构 `journey`
--

CREATE TABLE `journey` (
  `id` int(11) NOT NULL,
  `startPoint` varchar(20) NOT NULL,
  `endPoint` varchar(20) NOT NULL,
  `roomid` int(10) NOT NULL,
  `peerNums` int(5) NOT NULL,
  `startTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `journey`
--

INSERT INTO `journey` (`id`, `startPoint`, `endPoint`, `roomid`, `peerNums`, `startTime`) VALUES
(1, '同济大学', '虹桥火车站', 1, 1, '2016-11-27 14:34:54'),
(2, '虹桥火车站', '同济大学', 2, 2, '2016-11-30 00:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `paymentrecord`
--

CREATE TABLE `paymentrecord` (
  `id` int(11) NOT NULL,
  `sourceUserid` varchar(10) DEFAULT NULL,
  `targetUserid` varchar(10) DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `payments` float NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `room`
--

CREATE TABLE `room` (
  `id` int(10) NOT NULL,
  `roomname` varchar(10) NOT NULL,
  `host` varchar(20) DEFAULT NULL,
  `startPoint` varchar(20) NOT NULL,
  `endPoint` varchar(10) NOT NULL,
  `payuserId` varchar(10) DEFAULT NULL,
  `numberLimit` int(5) NOT NULL,
  `currentNums` int(5) NOT NULL,
  `startDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(5) NOT NULL DEFAULT '0',
  `startTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `room`
--

INSERT INTO `room` (`id`, `roomname`, `host`, `startPoint`, `endPoint`, `payuserId`, `numberLimit`, `currentNums`, `startDate`, `state`, `startTime`) VALUES
(1, 'lyx‘s room', '1452779', '同济大学', '虹桥火车站', NULL, 5, 1, '2016-11-27 14:17:01', 0, '2016-11-27 14:34:54'),
(2, '出行', '1452779', '虹桥火车站', '同济大学', NULL, 4, 2, '2016-11-27 15:09:55', 0, '2016-11-30 00:00:00');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `gender` tinyint(1) NOT NULL DEFAULT '0',
  `credit` float NOT NULL DEFAULT '0',
  `alipay` varchar(20) DEFAULT NULL,
  `coins` int(10) NOT NULL DEFAULT '0',
  `QQ_account` varchar(20) NOT NULL,
  `wechat_account` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `gender`, `credit`, `alipay`, `coins`, `QQ_account`, `wechat_account`) VALUES
('', '', '', 0, 0, NULL, 0, '', ''),
('1452778', '宇侠', 'lyx', 0, 0, NULL, 0, '', ''),
('1452779', '罗宇侠', '1452779', 0, 0, NULL, 0, '', '');

-- --------------------------------------------------------

--
-- 表的结构 `user_participate_room`
--

CREATE TABLE `user_participate_room` (
  `userid` varchar(10) NOT NULL,
  `roomid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_participate_room`
--

INSERT INTO `user_participate_room` (`userid`, `roomid`) VALUES
('1452778', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chatrecord`
--
ALTER TABLE `chatrecord`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKim026wcpiu9m82trmfh74jauj` (`roomid`),
  ADD KEY `FKimpx7mroc2elqcfgv720ga58o` (`senderid`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp96a6m9bcg1gs3wmkk7gf2in5` (`journeyid`),
  ADD KEY `FKc2b70pvh59xlyhl8pgyfhlh0i` (`sourceUserid`),
  ADD KEY `FK42xssu30h5t2xguybohxwsarr` (`targetUserid`);

--
-- Indexes for table `journey`
--
ALTER TABLE `journey`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKre7lbtxgw5yyw11g3cs6sggfq` (`roomid`);

--
-- Indexes for table `paymentrecord`
--
ALTER TABLE `paymentrecord`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi8fdj4cdvqbp3l47j9bmwoabx` (`sourceUserid`),
  ADD KEY `FKqgi94gjoqv88ude9i18mdkvat` (`targetUserid`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK73u33lq4sbm44dlxqmd70iv91` (`host`),
  ADD KEY `FKrqwfd3rapc8t6a8wv7rxenkwm` (`payuserId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_participate_room`
--
ALTER TABLE `user_participate_room`
  ADD PRIMARY KEY (`userid`,`roomid`),
  ADD KEY `FKhngk9hvknrmm8bkkwbecrh5kw` (`roomid`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `chatrecord`
--
ALTER TABLE `chatrecord`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- 使用表AUTO_INCREMENT `journey`
--
ALTER TABLE `journey`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `paymentrecord`
--
ALTER TABLE `paymentrecord`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `room`
--
ALTER TABLE `room`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 限制导出的表
--

--
-- 限制表 `chatrecord`
--
ALTER TABLE `chatrecord`
  ADD CONSTRAINT `FKim026wcpiu9m82trmfh74jauj` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `FKimpx7mroc2elqcfgv720ga58o` FOREIGN KEY (`senderid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `chatrecord_roomid_fk` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chatrecord_sourceuserid_fk` FOREIGN KEY (`senderid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK42xssu30h5t2xguybohxwsarr` FOREIGN KEY (`targetUserid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKc2b70pvh59xlyhl8pgyfhlh0i` FOREIGN KEY (`sourceUserid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKp96a6m9bcg1gs3wmkk7gf2in5` FOREIGN KEY (`journeyid`) REFERENCES `journey` (`id`),
  ADD CONSTRAINT `comment_journeyId_fk` FOREIGN KEY (`journeyid`) REFERENCES `journey` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_sourceUserid_fk` FOREIGN KEY (`sourceUserid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_targetUserid_fk` FOREIGN KEY (`targetUserid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- 限制表 `journey`
--
ALTER TABLE `journey`
  ADD CONSTRAINT `FKre7lbtxgw5yyw11g3cs6sggfq` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `journey_roomid_fk` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- 限制表 `paymentrecord`
--
ALTER TABLE `paymentrecord`
  ADD CONSTRAINT `FKi8fdj4cdvqbp3l47j9bmwoabx` FOREIGN KEY (`sourceUserid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqgi94gjoqv88ude9i18mdkvat` FOREIGN KEY (`targetUserid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `paymentrecord__targetUserid_fk` FOREIGN KEY (`targetUserid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `paymentrecord_sourceuseridfk` FOREIGN KEY (`targetUserid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- 限制表 `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FK73u33lq4sbm44dlxqmd70iv91` FOREIGN KEY (`host`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKrqwfd3rapc8t6a8wv7rxenkwm` FOREIGN KEY (`payuserId`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `room_hostid_fk` FOREIGN KEY (`host`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `room_payuserid_fk` FOREIGN KEY (`payuserId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- 限制表 `user_participate_room`
--
ALTER TABLE `user_participate_room`
  ADD CONSTRAINT `FKa1a4a6qkh2df6ahg4u35cbpmu` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKhngk9hvknrmm8bkkwbecrh5kw` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `user_participate_room_room_fk` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_participate_room_userfk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


CREATE TABLE `carpool`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`series`));