/*==============================================================*/
/* Table: `lc_level_config` 等级配置表                          */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_level_config`;
CREATE TABLE `lc_level_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`levelName` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `sort` int(11) DEFAULT NULL COMMENT '等级排序',
  `directRecommendNum` int(11) DEFAULT '0' COMMENT '直推荐人数',
  `nextRecommendNum` int(11) DEFAULT '0' COMMENT '下级及以上用户数',
  `teamPerformance` decimal(20,10) DEFAULT NULL COMMENT '团队业绩(USDT)',
	`levelAward` decimal(10,2) DEFAULT NULL COMMENT '等级奖励(%)',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='等级配置表';

/*==============================================================*/
/* Table: `lc_team_level_config` 社区等级配置表                 */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_team_level_config`;
CREATE TABLE `lc_team_level_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`teamLevelName` varchar(64) DEFAULT NULL COMMENT '社区等级名称',
  `teamSort` int(11) DEFAULT NULL COMMENT '社区等级排序',
  `directRecommendNum` int(11) DEFAULT '0' COMMENT '直推荐人数',
  `nextRecommendNum` int(11) DEFAULT '0' COMMENT '下级及以上用户数',
  `ownAsset` decimal(20,10) DEFAULT NULL COMMENT '个人资产(USDT)',
  `teamPerformance` decimal(20,10) DEFAULT NULL COMMENT '团队业绩(USDT)',
	`everyMonthTeamRatio` decimal(10,2) DEFAULT NULL COMMENT '每月团队新增业绩(%)',
	`teamAwardNum` decimal(20,10) DEFAULT NULL COMMENT '社区奖励数量(平台币)',
  `weekGrantRatio` decimal(10,2) DEFAULT NULL COMMENT '周发放比例(%)',
  `monthGrantRatio` decimal(10,2) DEFAULT NULL COMMENT '月发放比例(%)',
  `yearGrantRatio` decimal(10,2) DEFAULT NULL COMMENT '年发放比例(%)',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社区等级配置表';

/*==============================================================*/
/* Table: `lc_customer_level` 会员等级表                        */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_customer_level`;
CREATE TABLE `lc_customer_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
	`levelId` bigint(20) DEFAULT NULL COMMENT '等级id',
  `levelName` varchar(20) DEFAULT NULL COMMENT '个人等级名称',
  `sort` int(11) DEFAULT '0' COMMENT '个人等级排序',
	`teamLevelId` bigint(20) DEFAULT NULL COMMENT '社区等级id',
  `teamLevelName` varchar(20) DEFAULT NULL COMMENT '社区等级名称',
  `teamSort` int(11) DEFAULT '0' COMMENT '社区等级排序',
  `isTeamAward` int(1) DEFAULT '0' COMMENT '是否发放社区奖励',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员等级表';


/*==============================================================*/
/* Table: `lc_apply_team_award` 社区奖励申请表                  */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_apply_team_award`;
CREATE TABLE `lc_apply_team_award` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `email` varchar(255) DEFAULT null COMMENT '用户邮箱',
  `mobilePhone` varchar(255) DEFAULT null COMMENT '用户手机号',
	`socialType` int(1) DEFAULT null COMMENT '社交类型: 1 QQ 2 微信 3 facebook',
  `socialAccount` varchar(255) COLLATE utf8_bin COMMENT '社交账户',
	`socialGroupImg` varchar(255) COLLATE utf8_bin COMMENT '社交群图片',
  `applyStatus` int(1) DEFAULT '1' COMMENT '申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成',
  `auditStatus` int(1) DEFAULT '0' COMMENT '审核状态：0 审核中 1 审核通过 2审核拒绝',
  `auditExplain` varchar(500) DEFAULT NULL COMMENT '审核说明',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社区奖励申请表';

/*==============================================================*/
/* Table: `lc_apply_team_award` 社区奖励申请记录                 */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_apply_record`;
CREATE TABLE `lc_apply_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `applyId` bigint(20) DEFAULT NULL COMMENT '社区奖励申请Id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `email` varchar(255) DEFAULT null COMMENT '用户邮箱',
  `mobliePhone` varchar(255) DEFAULT null COMMENT '用户手机号',
	`socialType` int(1) DEFAULT null COMMENT '社交类型: 1 QQ 2 微信 3 facebook',
  `socialAccount` varchar(255) COLLATE utf8_bin COMMENT '社交账户',
	`socialGroupImg` varchar(255) COLLATE utf8_bin COMMENT '社交群图片',
  `applyStaus` int(1) DEFAULT '1' COMMENT '申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成',
  `auditStaus` int(1) DEFAULT NULL COMMENT '审核状态：1 申请通过 2申请拒绝',
  `auditExplain` int(1) DEFAULT NULL COMMENT '审核说明',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社区奖励申请记录';


/*==============================================================*/
/* Table: `lc_exception_log` 定时任务异常日志                  */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exception_log`;
CREATE TABLE `lc_exception_log` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL,
  `functionName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '方法名称',
  `exceptionText` varchar(255) COLLATE utf8_bin DEFAULT '0' COMMENT '异常原因',
  `ipaddress` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `customerId` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '持续时间',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT NULL COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT NULL COMMENT '{name:''修改时间''}',
  `saasid` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时任务异常日志';


/*==============================================================*/
/* Table: `lc_out_info` 出局信息表                              */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_out_info`;
CREATE TABLE `lc_out_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `accountId` bigint(20) DEFAULT NULL COMMENT '数币账户Id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `coinCode` varchar(20) DEFAULT null COMMENT '币种代码',
  `baseMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '投资额',
  `baseMaxMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '最大投资额',
  `outMultiple` int(9) DEFAULT '0.0000000000' COMMENT '出局倍数',
  `allMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '出局总额度',
  `coldMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '冻结总额度',
  `hotMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '可用总额度',
	`status` int(1) DEFAULT '0' COMMENT '是否出局 0: 未出局 1:已出局',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='出局信息表';


/*==============================================================*/
/* Table: `lc_deal_record` 交易记录表                              */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_deal_record`;
CREATE TABLE `lc_deal_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `outInfoId` bigint(20) DEFAULT NULL COMMENT '出局信息表Id',
  `accountId` bigint(20) DEFAULT NULL COMMENT '数币账户Id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `transactionNum` varchar(255) DEFAULT null COMMENT '流水号',
  `coinCode` varchar(20) DEFAULT null COMMENT '币种代码',
  `dealType` int(1) DEFAULT NULL COMMENT '//交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑入 12:兑出 8：投入 13:理财 9:周释放10:月释放 11:年释放 14:提币 15:充币',
  `ratio` decimal(20,10) DEFAULT null COMMENT '比例',
  `dealMoney` decimal(20,10) DEFAULT null COMMENT '交易金额',
  `codeValue` decimal(20,10) DEFAULT null COMMENT '当前币价值（USDT）',
  `predictExpendLimit` decimal(20,10) DEFAULT null COMMENT '预计消耗额度',
  `actualExpendLimit` decimal(20,10) DEFAULT null COMMENT '实际消耗额度',
  `predictMoney` decimal(20,10) DEFAULT null COMMENT '预计释放基数金额',
  `actualMoney` decimal(20,10) DEFAULT NULL COMMENT '实际基数',
  `serviceCharge` decimal(20,10) DEFAULT '0.0000000000' COMMENT '手续费',
  `dealStatus` int(1) unsigned DEFAULT '2' COMMENT '1：交易中（待审核）2：交易完成 3：交易失败',
  `remark` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='交易记录表';

/*==============================================================*/
/* Table: `lc_months_record` 月统计记录                         */
/*==============================================================*/
CREATE TABLE `lc_months_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `accountId` bigint(20) DEFAULT NULL COMMENT '数币账户Id',
  `coinCode` varchar(20) DEFAULT null COMMENT '币种代码',
  `dealType` int(1) DEFAULT NULL COMMENT '//交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑入 12:兑出 8：投入 13:理财 9:周释放10:月释放 11:年释放 14:提币 15:充币',
  `totalDealMoney` decimal(20,10) DEFAULT null COMMENT '交易金额',
  `months` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '月份',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='月统计记录';

/*==============================================================*/
/* Table: `lc_deal_total` 其它月统计                         */
/*==============================================================*/
CREATE TABLE `lc_deal_total` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `coinCode` varchar(20) DEFAULT null COMMENT '币种代码',
  `dealType` int(1) DEFAULT NULL COMMENT '//交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑入 12:兑出 8：投入 13:理财 9:周释放10:月释放 11:年释放 14:提币 15:充币',
  `dealMoney` decimal(20,10) DEFAULT null COMMENT '交易金额',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='其它月统计';

/*==============================================================*/
/* Table: `lc_task_log` 定时器执行日志                         */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_task_log`;
CREATE TABLE `lc_task_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '方法名称',
  `isException` int(3) DEFAULT '0' COMMENT '是否出现异常 0否 1是',
  `ipaddress` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `lasttime` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '持续时间',
  `startDate` datetime DEFAULT NULL COMMENT '开始时间',
  `endDate` datetime DEFAULT NULL COMMENT '结束时间',
  `created` datetime DEFAULT NULL COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT NULL COMMENT '{name:''修改时间''}',
  `saasid` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时器执行日志';

/*==============================================================*/
/* Table: `app_team_level` 用户推荐关系记录表                         */
/*==============================================================*/
DROP TABLE IF EXISTS `app_team_level`;
CREATE TABLE `app_team_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `parentId` bigint(20) DEFAULT NULL COMMENT '推荐人Id',
  `level` int(10) DEFAULT NULL COMMENT '层级',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  `parentInvitationCode` varchar(10) DEFAULT NULL COMMENT '推荐人邀请码',
  `customerInvitationCode` varchar(100) DEFAULT NULL COMMENT '用户邀请码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户推荐关系记录表';

/*==============================================================*/
/* Table: `lc_customer_level_record` 用户等级变化记录表                         */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_customer_level_record`;
CREATE TABLE `lc_customer_level_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `oldLevelId` bigint(20) DEFAULT NULL COMMENT '旧等级Id',
  `oldLevelName` varchar(255) DEFAULT NULL COMMENT '旧等级名称',
  `oldSort` int(1) DEFAULT NULL COMMENT '旧等级排序',
  `newLevelId` bigint(20) DEFAULT NULL COMMENT '新等级Id',
  `newLevelName` varchar(20) DEFAULT NULL COMMENT '新等级名称',
  `newSort` int(1) DEFAULT NULL COMMENT '新等级排序',
  `levelType` int(1) DEFAULT NULL COMMENT '等级类别 1：个人等级 2：社区等级',
  `teamAwardNum` decimal(20,10) DEFAULT null COMMENT '社区奖励收益',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户等级变化记录表';

/*==============================================================*/
/* Table: `lc_release_rule_details` 释放规则详情                */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_release_rule_details`;
CREATE TABLE `lc_release_rule_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `customerLevelId` bigint(20) DEFAULT NULL COMMENT '用户等级Id',
  `levelId` bigint(20) DEFAULT NULL COMMENT '等级Id',
  `levelName` varchar(255) DEFAULT NULL COMMENT '等级名称',
  `sort` int(1) DEFAULT NULL COMMENT '等级排序',
  `startTeamAward` decimal(20,10) DEFAULT null COMMENT '起始团队奖励',
  `startTime` datetime DEFAULT NULL COMMENT '奖励开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '奖励结束时间',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='释放规则详情';


/*==============================================================*/
/* Table: `lc_bound_record` 用户绑定记录                        */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_bound_record`;
CREATE TABLE `lc_bound_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `oAccountNum` varchar(255) DEFAULT null COMMENT '用户原账号',
	`nAccountNum` varchar(255) DEFAULT null COMMENT '用户新账号',
	`accountNumType` int(1) DEFAULT null COMMENT '账号类型: 1 手机号 2 邮箱',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户绑定记录（邮箱or手机号）';

/*==============================================================*/
/* Table: `lc_platform_total` 平台币统计                        */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_platform_total`;
CREATE TABLE `lc_platform_total` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `coinCode` varchar(20) DEFAULT null COMMENT '币种代码',
  `todayAddNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '今日新增量',
	`ayerAllNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '昨日总量',
	`ayerConvertNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '昨日兑换量',
	`ayerSurplusNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '昨日剩余量（昨日总量-昨日已兑换量）',
	`todayAllNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '今日总量（今日新增+昨日剩余量）',
  `saasid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '{name:''修改时间''}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='平台币统计';



/*==============================================================*/
/* Table: `lc_ecofund` 生态基金          												*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_ecofund`;
CREATE TABLE `lc_ecofund` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
    `orderNum` varchar(255) DEFAULT NULL COMMENT '单号',
	`activityName` varchar(255) DEFAULT NULL  COMMENT '活动名称',
	`activityDate` datetime DEFAULT NULL  COMMENT '活动时间',
	`activityAddress` varchar(255) DEFAULT NULL  COMMENT '活动地址',
    `peopleCount` int(9) DEFAULT NULL  COMMENT '活动人数',
	`activityIntro` varchar(800) DEFAULT NULL  COMMENT '活动简介',
    `activityImage` varchar(500) DEFAULT NULL  COMMENT '活动图片',
	`activityStatus` int(1) DEFAULT NULL  COMMENT '1 申请中 2 后台审核拒绝 3 后台审核通过 4 用户拒绝 5 用户通过（资料待补充） 6 补充材料待审核 7 后台通过补充材料审核 8申请删除',
	`activityReply` varchar(800) DEFAULT NULL  COMMENT '活动平台回复',
	`againCreated` datetime DEFAULT NULL  COMMENT '补充时间',
	`againActivityDate` datetime DEFAULT NULL  COMMENT '补充活动时间',
	`againActivityAddress` varchar(255) DEFAULT NULL  COMMENT '补充活动地址',
    `againPeopleCount` int(9) DEFAULT NULL  COMMENT '补充活动人数',
	`againActivityIntro` varchar(800) DEFAULT NULL  COMMENT '补充活动简介',
    `againActivityImage` varchar(500) DEFAULT NULL  COMMENT '补充活动图片',
    `created` datetime DEFAULT NULL,
    `modified` datetime DEFAULT NULL,
    `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='生态基金';


/*==============================================================*/
/* Table: `lc_ecolog_plate` 生态入驻板块          							*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_ecolog_plate`;
CREATE TABLE `lc_ecolog_plate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`plateName` varchar(255) DEFAULT NULL COMMENT '板块名称',
	`sort` int(2) DEFAULT null  COMMENT '排序字段 越小越靠前',
  `isOpen` int(1) DEFAULT '1'  COMMENT '1 开启 2 关闭',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='生态入驻板块';



/*==============================================================*/
/* Table: `lc_ecolog_enter` 生态入驻          												*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_ecolog_enter`;
CREATE TABLE `lc_ecolog_enter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
	`orderNum` varchar(255) DEFAULT NULL COMMENT '单号',
	`plateId` bigint(20) DEFAULT NULL COMMENT '板块Id',
	`enterLevel` VARCHAR(20) DEFAULT NULL COMMENT '入驻等级 A(前三) B ',
	`enterName` varchar(255) DEFAULT NULL  COMMENT '入驻名称',
	`enterLogo` varchar(255) DEFAULT NULL  COMMENT '入驻logo',
	`downloadLink` varchar(255) DEFAULT NULL  COMMENT '下载链接',
    `enterApplyIntro` varchar(800) DEFAULT NULL  COMMENT '申请入驻简介',
	`enterStatus` int(1) DEFAULT NULL  COMMENT '1 申请中 2 后台审核拒绝 3 后台审核通过(待付款) 4 用户拒绝 5 用户通过（待核实） 6 核实通过 7 核实未通过 8 已到期',
	`renewStatus` int(1) DEFAULT '0'  COMMENT '0 未申请续费 1 续费待核实 ',
	`verifyDate` datetime DEFAULT NULL COMMENT '核实时间',
	`enterReply` varchar(800) DEFAULT NULL  COMMENT '入驻申请回复',
	`validityDay` int(9) DEFAULT NULL COMMENT '实际保证期有效天数',
	`expireDate` datetime DEFAULT NULL COMMENT '到期时间',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='生态入驻 ';


/*==============================================================*/
/* Table: `lc_ecolog_pay` 生态入驻缴费记录         						  */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_ecolog_pay`;
CREATE TABLE `lc_ecolog_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
	`enterId` bigint(20) DEFAULT NULL COMMENT '入驻订单Id',
	`orderNum` varchar(255) DEFAULT NULL COMMENT '单号',
	`payDate` datetime DEFAULT NULL COMMENT '缴费时间',
	`acceptAddress` varchar(255) DEFAULT NULL COMMENT '收款地址',
	`paymentAddress` varchar(255) DEFAULT NULL COMMENT '付款地址',
	`verifyDate` datetime DEFAULT NULL COMMENT '核实时间',
	`baseValidityDay` int(9) DEFAULT NULL COMMENT '当前规则中有效期天数',
	`residueValidityDay` int(9) DEFAULT NULL COMMENT '上笔剩余天数',
	`validityDay` int(9) DEFAULT NULL COMMENT '实际保证期有效天数',
	`status` int(1) DEFAULT null COMMENT '1 待核实 2 用户拒绝 3 核实未通过 4核实完成',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='生态入驻缴费记录 ';

/*站内信添加阅读时间*/
alter table message_as_customer add `readDate` datetime DEFAULT NULL COMMENT '阅读时间';
alter table message_as_customer add `deleteDate` datetime DEFAULT NULL COMMENT '删除时间';

/*==============================================================*/
/* Table: `lc_usdt_total` USDT统计         						      */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_usdt_total`;
CREATE TABLE `lc_usdt_total` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`coinCode` varchar(255) DEFAULT NULL COMMENT '币种代码',
  `payMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '充值总数',
	`extractMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '提取总数',
	`surplusMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '剩余数量',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='USDT统计';

alter table lc_ecofund add `againActivityReply` varchar(800) COLLATE utf8_bin DEFAULT NULL COMMENT '补充平台回复';
alter table lc_ecofund add `againActivityVideo` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '补充材料视频上传';
alter table lc_ecofund add `itAgain` int(1) DEFAULT 0 COMMENT '补充材料是否拒绝 0 未拒绝 1 已拒绝';

/*==============================================================*/
/* Table: `lc_exchange_config` 兑换项目配置表          					*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exchange_config`;
CREATE TABLE `lc_exchange_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`itemName` varchar(255) DEFAULT NULL COMMENT '项目名称',
	`payCoinCode` varchar(50) DEFAULT NULL COMMENT '支付币种--平台币',
	`gainCoinCode` varchar(50) DEFAULT NULL COMMENT '兑换币种',
	`ratio` decimal(10,2) DEFAULT '0.00' COMMENT '兑换汇率',
	`totalNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '本局项目兑换总数',
	`singleMinNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '单次购买最低数量',
	`soloMaxNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '单人本局最大购买值',
	`itemStartDate` datetime DEFAULT NULL COMMENT '项目启动时间',
	`gainStartDate` varchar(255) DEFAULT NULL COMMENT '兑换开始时间',
	`gainEndDate` varchar(255) DEFAULT NULL COMMENT '兑换结束时间',
	`hasChangeRatio` decimal(10,2) DEFAULT '0.00' COMMENT '已兑换比例',
	`status` int(1) DEFAULT '0' COMMENT '是否开启 0 未开启 1 开启',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='兑换项目配置表';


/*==============================================================*/
/* Table: `lc_exchange_item` 兑换项目表          								*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exchange_item`;
CREATE TABLE `lc_exchange_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`itemConfigId` bigint(20) DEFAULT NULL COMMENT '项目配置Id',
	`itemName` varchar(255) DEFAULT NULL COMMENT '项目名称',
	`payCoinCode` varchar(50) DEFAULT NULL COMMENT '支付币种--平台币',
	`gainCoinCode` varchar(50) DEFAULT NULL COMMENT '兑换币种',
	`ratio` decimal(10,2) DEFAULT '0.00' COMMENT '兑换汇率',
	`totalNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '本局项目兑换总数',
	`singleMinNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '单次购买最低数量',
	`soloMaxNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '单人本局最大购买值',
	`hasBuyNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '已购买数量',
	`itemStartDate` datetime DEFAULT NULL COMMENT '项目启动时间',
	`itemEndDate` datetime DEFAULT NULL COMMENT '项目结束时间',
	`periodsNum` int(10) DEFAULT NUll COMMENT '项目期数',
	`status` int(1) DEFAULT '0' COMMENT '是否开启 0 进行中 1 结束',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='兑换项目表';


/*==============================================================*/
/* Table: `lc_exchange_record` 兑换记录表          								*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exchange_record`;
CREATE TABLE `lc_exchange_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`exchangeSn` varchar(255) DEFAULT NULL COMMENT '兑换单号',
	`customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
	`itemId` bigint(20) DEFAULT NULL COMMENT '项目id',
	`itemName` varchar(255) DEFAULT NULL COMMENT '项目名称',
	`ratio` decimal(10,2) DEFAULT '0.00' COMMENT '兑换汇率',
	`payCoinCode` varchar(50) DEFAULT NULL COMMENT '支付币种--平台币',
	`gainCoinCode` varchar(50) DEFAULT NULL COMMENT '兑换币种',
	`payNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '支付数量',
	`gainNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '兑换数量',
	`periodsNum` int(10) DEFAULT NUll COMMENT '项目期数',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='兑换记录表';



/*==============================================================*/
/* Table: `lc_exchange_total` 兑换统计         						      */
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exchange_total`;
CREATE TABLE `lc_exchange_total` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`coinCode` varchar(255) DEFAULT NULL COMMENT '币种代码',
  `gainNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '兑换总数',
	`extractNum` decimal(20,10) DEFAULT '0.0000000000' COMMENT '提取总数',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='兑换统计';

/*==============================================================*/
/* Table: `lc_exchange_image` 兑换配置配图表         						*/
/*==============================================================*/
DROP TABLE IF EXISTS `lc_exchange_image`;
CREATE TABLE `lc_exchange_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	`configId` bigint(20) DEFAULT NULL COMMENT '兑换配置id',
	`languagetype` varchar(255) DEFAULT NULL COMMENT '语种编码',
	`image` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='兑换配置配图表';
