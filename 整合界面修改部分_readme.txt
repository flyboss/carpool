

对了，我的查看行程的URL为/journey/getMyJourneyAsHost/1452779,其中1452779为用户的id，等session处理好了之后，进行整合后，就不再是这种形式了。


在将我的界面与master合并时，修改了一些部分，如下：
我在UserEntity中添加了一个字段为receivedComments,
表示收到的评论数，在写触发器的时候发现是需要这个字段的,定义的逻辑为用户的信誉评分
评论积分相加/评论数
还写了一个触发器，当生成一个行程时，行程中的用户的carpoolingCount加1

将RoomEntity中的fetch = FetchType.LAZY改成了
 @ManyToMany(mappedBy = "userParticipateRooms", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
然后其他部分好像都没有什么改动，只是加上了我的那部分而已了。

同时，遇到了一个小问题，分享一下。
在生成一条评论时，这个时候触发器会更改用户的信誉积分，但是我在网页中测试的时候怎么也
改不了用户的信誉积分，但我测试发现这个触发器是一定执行了的，手动在数据库里加也没问题，后来才发现了如果我将添加
评论的函数addComment 的Transaction注解去掉的话，就可以了。

触发器的脚本如下：

//更新用户信誉积分的触发器
CREATE TRIGGER `updateUserCredits` AFTER INSERT ON `commententity`
 FOR EACH ROW BEGIN
DECLARE targetid varchar(10);
DECLARE newcredits double;
DECLARE creditsnums int;
DECLARE finalcredits double;
DECLARE averageCredit double;

SET targetid = new.targetUserid;
SET newcredits = new.credit;
SELECT userentity.receivedComments INTO creditsnums FROM userentity WHERE userentity.id = targetid;
SELECT userentity.credit INTO averageCredit FROM userentity WHERE userentity.id = targetid;

UPDATE userentity SET userentity.receivedComments = userentity.receivedComments+1
where userentity.id = targetid;
SET finalcredits = (creditsnums*averageCredit+newcredits)/(creditsnums+1);
UPDATE userentity set userentity.credit = finalcredits where userentity.id = targetid;
END


//更新用户出行数的触发器
CREATE TRIGGER `increaseCarPoolingNums` AFTER INSERT ON `journeyentity`
 FOR EACH ROW BEGIN
DECLARE roomid int;
set roomid = new.roomid;
UPDATE userentity set userentity.carpoolingCount = userentity.carpoolingCount+1 WHERE userentity.id IN (SELECT ur.uid
 FROM roomentity r ,user_rooms ur WHERE roomid = r.id AND ur.rid = r.id) ;
END


