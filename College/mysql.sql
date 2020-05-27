-- 管理员信息表
CREATE TABLE `admininfo` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(100) COLLATE utf8_bin NOT NULL,
  `pwd` varchar(100) COLLATE utf8_bin NOT NULL,
  `sex` int(2), --1男2女
  `tel` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `profession` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `note` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL, -- 账号状态
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aname` (`aname`),
  UNIQUE KEY `tel` (`tel`)
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 用户基本信息表
CREATE TABLE `memberinfo` (
  `mno` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(100) COLLATE utf8_bin NOT NULL, -- 用户昵称
  `realName` varchar(100) COLLATE utf8_bin DEFAULT NULL, -- 用户真实姓名
  `pwd` varchar(100) COLLATE utf8_bin NOT NULL, -- 用户密码
  `sex` varchar(10) DEFAULT NULL, -- 用户性别
  `academic` varchar(15) COLLATE utf8_bin DEFAULT NULL, -- 文理科
  `province` varchar(10) COLLATE utf8_bin DEFAULT NULL, -- 地区
  `score` int(2) DEFAULT NULL, -- 分数
  `ranking` int(2) DEFAULT NULL, -- 排名
  `tel` varchar(15) COLLATE utf8_bin DEFAULT NULL, -- 用户电话
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL, -- 用户邮箱
  `regDate` datetime DEFAULT NULL, -- 用户注册日期
  `status` int(11) DEFAULT NULL, -- 账号状态
  PRIMARY KEY (`mno`),
  UNIQUE KEY `nickName` (`nickName`),
  UNIQUE KEY `tel` (`tel`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into memberinfo (nickName, pwd, area) values ('zlp', md5('123456'), '广东省');

-- 权重表
CREATE TABLE `weight` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `mno` int(11) NOT NULL,
  `interest` int(11) DEFAULT NULL, -- 兴趣权重
  `location` int(11) DEFAULT NULL, -- 地理权重
  `ranking` int(11) DEFAULT NULL, -- 排名权重
  `major` int(11) DEFAULT NULL, -- 专业权重
  PRIMARY KEY (`wid`),
  CONSTRAINT `FK_memberinfo_mno` FOREIGN KEY (`mno`) REFERENCES `memberinfo` (`mno`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 地区表
CREATE TABLE `address` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `mno` int(11) NOT NULL, -- 用户ID
  `province` varchar(20) COLLATE utf8_bin DEFAULT NULL, -- 省
  `city` varchar(20) COLLATE utf8_bin DEFAULT NULL, -- 市
  `area` varchar(20) COLLATE utf8_bin DEFAULT NULL, -- 区
  PRIMARY KEY (`aid`),
  CONSTRAINT `FK_memberinfo_mno` FOREIGN KEY (`mno`) REFERENCES `memberinfo` (`mno`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
