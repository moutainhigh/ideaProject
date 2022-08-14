INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'downgradeDays', '预约降级间隔(天)', '预约降级间隔(天)', '1', '15', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('361', 'marginDays', '保证金起息天数（天）', '保证金起息天数（天）', '1', '15', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('360', 'marginInterestRate', '保证金利率（%）', '保证金利率（%）', '1', '1', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('359', 'marginRatio', '保证金比例（%）', '保证金比例（%）', '1', '20', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('358', 'waitingTime', '等待尾款时长（时）', '等待尾款时长（时）', '5', '5', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('357', 'lineUpTime', '排单间隔时长（天）', '排单间隔时长（天）', '5', '7', 'KLG_Rules_Purchase_Config', '预约买入设置', '预约买入设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('356', 'grandPrizeFund', '大奖基金（USDT）%', '大奖基金', '1', '10', 'KLG_Rules_Award_Config', '糖果奖励分配', '糖果奖励分配', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'gradationAward', '级差奖（USDT）%', '级差奖', '1', '10', 'KLG_Rules_Award_Config', '糖果奖励分配', '糖果奖励分配', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'seePointAward', '见点奖（USDT）%', '见点奖', '1', '10', 'KLG_Rules_Award_Config', '糖果奖励分配', '糖果奖励分配', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'platformGain', '平台收取（USDT）%', '平台收取', '5', '10', 'KLG_Rules_Award_Config', '糖果奖励分配', '糖果奖励分配', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'meGain', '本人获得（USDT）%', '本人获得', '5', '60', 'KLG_Rules_Award_Config', '糖果奖励分配', '糖果奖励分配', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'endTime', '每天关盘时间', '每天关盘时间', '5', '2019-01-26', 'KLG_Rules_Time_Config', '开盘时间设置', '开盘时间设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'startTime', '每天开盘时间', '每天开盘时间', '5', '2019-01-03', 'KLG_Rules_Time_Config', '开盘时间设置', '开盘时间设置', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'issuePrice', '货币价格', '货币价格', '1', '10', 'KLG_Rules_Coin_Config', '平台发币信息', '平台发币信息', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'coinCode', '币种Code', '币种Code', '1', 'SME', 'KLG_Rules_Coin_Config', '平台发币信息', '平台发币信息', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'coinName', '币种名称', '币种名称', '1', 'SME', 'KLG_Rules_Coin_Config', '平台发币信息', '平台发币信息', '2019-01-12 15:58:09', '2019-04-15 15:41:28', 'hurong_system', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES (DEFAULT, 'state', '开关', '开关', '2', '1', 'KLG_Rules_Time_Config', '开盘时间设置', '开盘时间设置', '2019-01-12 15:58:09', '2019-04-16 09:48:37', 'hurong_system', '0');

-- 见点将代数 1-10代
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1104', 'klg_algebra_925890', 'klg_algebra', '十代', '10', '3', '', '', '', '2019-04-16 14:11:26', '2019-04-16 14:11:26', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1103', 'klg_algebra_813691', 'klg_algebra', '九代', '9', '3', '', '', '', '2019-04-16 14:11:20', '2019-04-16 14:11:20', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1102', 'klg_algebra_593552', 'klg_algebra', '八代', '8', '3', '', '', '', '2019-04-16 14:10:30', '2019-04-16 14:10:30', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1101', 'klg_algebra_978967', 'klg_algebra', '七代', '7', '3', '', '', '', '2019-04-16 14:10:24', '2019-04-16 14:10:24', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1100', 'klg_algebra_377342', 'klg_algebra', '六代', '6', '3', '', '', '', '2019-04-16 14:10:19', '2019-04-16 14:10:19', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1099', 'klg_algebra_168674', 'klg_algebra', '五代', '5', '3', '', '', '', '2019-04-16 14:10:15', '2019-04-16 14:10:15', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1098', 'klg_algebra_471716', 'klg_algebra', '四代', '4', '3', '', '', '', '2019-04-16 14:10:11', '2019-04-16 14:10:11', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1097', 'klg_algebra_439079', 'klg_algebra', '三代', '3', '3', '', '', '', '2019-04-16 14:10:02', '2019-04-16 14:10:02', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1096', 'klg_algebra_847283', 'klg_algebra', '二代', '2', '3', '', '', '', '2019-04-16 14:09:57', '2019-04-16 14:09:57', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1095', 'klg_algebra_317030', 'klg_algebra', '1代', '1', '3', '', '', '', '2019-04-16 14:09:51', '2019-04-16 14:09:51', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1094', 'klg_algebra', 'business', '等级代数', 'klg_algebra', '2', '', '', '', '2019-04-16 14:09:33', '2019-04-16 14:09:33', '', '', '');

-- 平台币类型
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1118', 'account_type_208755', 'account_type', 'USDT周奖账户', '108', '3', '', '', '', '2019-04-16 15:27:39', '2019-04-16 15:27:39', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1117', 'account_type_319832', 'account_type', 'USDT糖果回收账户', '107', '3', '', '', '', '2019-04-16 15:27:32', '2019-04-16 15:27:32', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1116', 'account_type_302800', 'account_type', 'USDT保证金没收账户', '106', '3', '', '', '', '2019-04-16 15:27:25', '2019-04-16 15:27:25', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1115', 'account_type_451780', 'account_type', 'USDT财政局账户', '105', '3', '', '', '', '2019-04-16 15:27:15', '2019-04-16 15:27:15', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1114', 'account_type_361067', 'account_type', 'USDT奖金沉淀账户', '104', '3', '', '', '', '2019-04-16 15:27:05', '2019-04-16 15:27:05', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1113', 'account_type_298514', 'account_type', 'SEM糖果账户', '103', '3', '', '', '', '2019-04-16 15:26:52', '2019-04-16 15:26:52', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1112', 'account_type_072199', 'account_type', 'SME粮食局账户', '102', '3', '', '', '', '2019-04-16 15:26:40', '2019-04-16 15:26:40', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1111', 'account_type_369239', 'account_type', 'SME总账户', '101', '3', '', '', '', '2019-04-16 15:26:26', '2019-04-16 15:26:26', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1110', 'account_type', 'business', '平台账户类型', 'account_type', '2', '', '', '', '2019-04-16 15:26:02', '2019-04-16 15:26:02', '', '', '');
-- 限制类型
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1122', 'limit_type_939316', 'limit_type', '预约排队', '3', '3', '', '', '', '2019-04-18 16:49:39', '2019-04-18 16:49:39', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1121', 'limit_type_388094', 'limit_type', '新人预约', '2', '3', '', '', '', '2019-04-18 16:49:29', '2019-04-18 16:49:29', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1120', 'limit_type_849408', 'limit_type', '抢单购买', '1', '3', '', '', '', '2019-04-18 16:49:21', '2019-04-18 16:49:21', '', '', '');
INSERT INTO `hry_klg`.`new_app_dic` (`id`, `mkey`, `pkey`, `name`, `value`, `type`, `remark1`, `remark2`, `remark3`, `created`, `modified`, `creator`, `editor`, `saasid`) VALUES ('1119', 'limit_type', 'business', '限制类型', 'limit_type', '2', '', '', '', '2019-04-18 16:49:06', '2019-04-18 16:49:06', '', '', '');

--大奖基金
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('377', 'luckDrawOpen', '大奖开关', '大奖开关', '2', '1', 'luckDrawKey', '大奖基金设置', '大奖基金设置', '2019-06-11 14:37:54', '2019-06-11 14:37:56', '402880e4514780120151479ac3a50005', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('378', 'bonus\n\nProportion', '基金大奖发放 （%）', '基金大奖发放 （%）', '1', '50', 'luckDrawKey', '大奖基金设置', '大奖基金设置', '2019-06-11 14:37:54', '2019-06-11 14:37:56', '402880e4514780120151479ac3a50005', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('379', 'firstProportion', '一等奖（%）', '一等奖（%）', '1', '50', 'luckDrawKey', '大奖基金设置', '大奖基金设置', '2019-06-11 14:37:54', '2019-06-11 14:37:56', '402880e4514780120151479ac3a50005', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('380', 'secondProportion', '二等奖（%）', '二等奖（%）', '1', '50', 'luckDrawKey', '大奖基金设置', '大奖基金设置', '2019-06-11 14:37:54', '2019-06-11 14:37:56', '402880e4514780120151479ac3a50005', '0');
INSERT INTO `app_config` (`configid`, `configkey`, `configname`, `configdescription`, `datatype`, `value`, `typekey`, `typename`, `description`, `created`, `modified`, `saasId`, `ishidden`) VALUES ('381', 'thirdProportion', '三等奖（%）', '三等奖（%）', '1', '50', 'luckDrawKey', '大奖基金设置', '大奖基金设置', '2019-06-11 14:37:54', '2019-06-11 14:37:56', '402880e4514780120151479ac3a50005', '0');


-- 2019年4月11日17点16分
CREATE TABLE `klg_gradation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) DEFAULT NULL,
  `gradation` decimal(20,10) DEFAULT NULL COMMENT '级差数',
  `minLevelSort` int(11) DEFAULT NULL COMMENT '最小会员等级',
  `levelNum` int(11) DEFAULT NULL COMMENT '星级个数',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='级差奖管理';


CREATE TABLE `klg_level_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) DEFAULT NULL,
  `levelName` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `sort` int(11) DEFAULT NULL COMMENT '等级排序',
  `buyNum` decimal(20,10) DEFAULT NULL COMMENT '购买限制',
  `bonusMultiple` decimal(20,10) DEFAULT NULL COMMENT '奖金倍数',
  `pointAlgebra` int(11) DEFAULT NULL COMMENT '见点代数',
  `sellTime` int(11) DEFAULT NULL COMMENT '基础卖出时长',
  `candyNum` decimal(20,10) DEFAULT NULL COMMENT '基础糖果比率',
  `addCandyNum` decimal(20,10) DEFAULT NULL COMMENT '递增糖果比率',
  `maxSellTime` int(11) DEFAULT NULL COMMENT '最高卖出时长',
  `recommendNum` int(11) DEFAULT NULL COMMENT '推荐人数',
  `recommendSort` int(11) DEFAULT NULL COMMENT '推荐星级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='等级配置   升级规则配置级差奖励';



CREATE TABLE `klg_teamlevel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `parentId` bigint(20) DEFAULT NULL COMMENT '推荐人Id',
  `level` int(10) DEFAULT NULL COMMENT '层级',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `saasId` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `hly_teamlevel_customerId` (`customerId`) USING BTREE,
  KEY `hly_teamlevel_parentId` (`parentId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='推荐关系表';


CREATE TABLE `klg_transaction_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `saasid` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `projectNumber` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号',
  `type` int(11) DEFAULT NULL COMMENT '类型( )',
  `coinCode` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '币种Code',
  `transactionCount` decimal(20,10) DEFAULT NULL COMMENT '交易量',
  `totalMoney` decimal(20,10) DEFAULT NULL COMMENT '总数量',
  `hotMoney` decimal(20,10) DEFAULT NULL COMMENT '可用数量',
  `coldMoney` decimal(20,10) DEFAULT NULL COMMENT '冻结数量',
  `customerId` bigint(64) DEFAULT NULL COMMENT '用户Id',
  `state` int(11) DEFAULT NULL COMMENT '状态类型(101冻结 默认102解冻201.支出202.收入)',
  `remark` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `foreignKey` bigint(64) DEFAULT NULL,
  `isShow` int(11) DEFAULT '0' COMMENT '0不显示1显示',
  PRIMARY KEY (`id`),
  KEY `ico_transaction_record_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='交易流水';


CREATE TABLE `klg_transfer_accounts` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `serialNumber` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `customerId` bigint(64) DEFAULT NULL COMMENT '用户Id',
  `toCustomerId` bigint(64) DEFAULT NULL COMMENT '转入方用户Id',
  `coinCode` varchar(22) COLLATE utf8_bin DEFAULT NULL COMMENT '账转币种',
  `money` decimal(20,10) DEFAULT NULL COMMENT '转账金额',
  `saasid` varchar(22) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `ico_transfer_account` (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='转账记录表';

alter table klg_level_config add buyNums VARCHAR(256) DEFAULT null COMMENT '购买限制';
alter table klg_level_config add maxRewardTime int(11) DEFAULT null COMMENT '最高奖励时长';
alter table klg_sell_transaction add sellDay int(11) COMMENT '卖出天数';
alter table klg_sell_transaction add sellEndTime datetime COMMENT '卖出时间';

-- 2019年8月7日
alter table klg_customer_level add fixedGradation  decimal(20,10) DEFAULT null COMMENT '指定级差';
alter table klg_customer_level add nowSort  int(11) not null DEFAULT 0  COMMENT '当前等级排序';




