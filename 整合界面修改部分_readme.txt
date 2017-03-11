

���ˣ��ҵĲ鿴�г̵�URLΪ/journey/getMyJourneyAsHost/1452779,����1452779Ϊ�û���id����session�������֮�󣬽������Ϻ󣬾Ͳ�����������ʽ�ˡ�


�ڽ��ҵĽ�����master�ϲ�ʱ���޸���һЩ���֣����£�
����UserEntity�������һ���ֶ�ΪreceivedComments,
��ʾ�յ�������������д��������ʱ��������Ҫ����ֶε�,������߼�Ϊ�û�����������
���ۻ������/������
��д��һ����������������һ���г�ʱ���г��е��û���carpoolingCount��1

��RoomEntity�е�fetch = FetchType.LAZY�ĳ���
 @ManyToMany(mappedBy = "userParticipateRooms", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
Ȼ���������ֺ���û��ʲô�Ķ���ֻ�Ǽ������ҵ��ǲ��ֶ����ˡ�

ͬʱ��������һ��С���⣬����һ�¡�
������һ������ʱ�����ʱ�򴥷���������û����������֣�����������ҳ�в��Ե�ʱ����ôҲ
�Ĳ����û����������֣����Ҳ��Է��������������һ��ִ���˵ģ��ֶ������ݿ����Ҳû���⣬�����ŷ���������ҽ����
���۵ĺ���addComment ��Transactionע��ȥ���Ļ����Ϳ����ˡ�

�������Ľű����£�

//�����û��������ֵĴ�����
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


//�����û��������Ĵ�����
CREATE TRIGGER `increaseCarPoolingNums` AFTER INSERT ON `journeyentity`
 FOR EACH ROW BEGIN
DECLARE roomid int;
set roomid = new.roomid;
UPDATE userentity set userentity.carpoolingCount = userentity.carpoolingCount+1 WHERE userentity.id IN (SELECT ur.uid
 FROM roomentity r ,user_rooms ur WHERE roomid = r.id AND ur.rid = r.id) ;
END


