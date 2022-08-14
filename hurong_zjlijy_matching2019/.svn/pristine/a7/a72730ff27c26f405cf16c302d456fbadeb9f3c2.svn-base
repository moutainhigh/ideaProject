/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.55
Source Server Version : 50723
Source Host           : 192.168.0.55:3306
Source Database       : demo_global_mall_8.0

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-08-21 10:32:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cm_account
-- ----------------------------
DROP TABLE IF EXISTS `cm_account`;
CREATE TABLE `cm_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coinCode` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '币种',
  `customerId` bigint(20) NOT NULL COMMENT '用户或者会员id',
  `hotMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '可用总额',
  `coldMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '冻结总额',
  `usedMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '已用总额：当前所有持仓占用保证金总和',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='云矿机账户表';

-- ----------------------------
-- Table structure for cm_account_record
-- ----------------------------
DROP TABLE IF EXISTS `cm_account_record`;
CREATE TABLE `cm_account_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `accountId` bigint(20) NOT NULL COMMENT '数字货币账户id（dm_account）',
  `customerId` bigint(20) NOT NULL COMMENT '用户id',
  `coinCode` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '币种',
  `recordType` int(1) NOT NULL COMMENT '流水类型 （ 1增加 2减少）',
  `transactionMoney` decimal(20,10) DEFAULT '0.0000000000' COMMENT '交易金额',
  `balanceMoney` decimal(30,10) DEFAULT '0.0000000000',
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '交易备注',
  `remarkkey` int(3) DEFAULT NULL COMMENT '交易备注1,2,3',
  `monteyType` int(3) NOT NULL COMMENT '1热账户2冷账户3已用账户',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1980 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='云矿机账户流水表';

-- ----------------------------
-- Table structure for cm_customer
-- ----------------------------
DROP TABLE IF EXISTS `cm_customer`;
CREATE TABLE `cm_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `grade1` int(10) DEFAULT '0' COMMENT '矿工等级',
  `gradeName1` varchar(255) DEFAULT NULL COMMENT '矿工等级名称',
  `isEdit1` tinyint(2) DEFAULT '1' COMMENT '矿工等级是否人工修改 1否 2是',
  `grade2` int(10) DEFAULT '0' COMMENT '矿场等级',
  `gradeName2` varchar(255) DEFAULT NULL COMMENT '矿场等级名称',
  `isEdit2` tinyint(2) DEFAULT '1' COMMENT '矿场等级是否人工修改 1否 2是',
  `teamNum` int(10) DEFAULT '0' COMMENT '团队人数',
  `personalAchievement` decimal(20,8) DEFAULT '0.00000000' COMMENT '个人业绩',
  `teamAchievement` decimal(20,8) DEFAULT '0.00000000' COMMENT '团队业绩',
  `maxAchievement` decimal(20,8) DEFAULT '0.00000000' COMMENT '大趋业绩',
  `minAchievement` decimal(20,8) DEFAULT '0.00000000' COMMENT '小趋业绩',
  `remark` varchar(255) DEFAULT NULL,
  `saasId` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `customer_information_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员等级信息';

-- ----------------------------
-- Table structure for cm_customer_miner
-- ----------------------------
DROP TABLE IF EXISTS `cm_customer_miner`;
CREATE TABLE `cm_customer_miner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `minerId` bigint(20) DEFAULT NULL COMMENT '矿机ID',
  `customerId` bigint(20) DEFAULT NULL COMMENT '购买人Id',
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `minerName` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '矿机名称',
  `minerCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '矿机编号',
  `minerPrice` decimal(20,8) DEFAULT '0.00000000' COMMENT '矿机价钱',
  `minerProfitType` tinyint(2) DEFAULT '1' COMMENT '收益领取类型：1自动发放 2果树领取',
  `payCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '支付币种',
  `profitCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '收益币种',
  `profitRate` decimal(10,4) DEFAULT '0.0000' COMMENT '收益倍数',
  `totalProfit` decimal(20,8) DEFAULT NULL COMMENT '总收益',
  `dayProfit` decimal(20,8) DEFAULT NULL COMMENT '日收益',
  `effectiveTimeLength` int(10) DEFAULT NULL COMMENT '有效时长',
  `timeLengthCompany` int(10) DEFAULT '1' COMMENT '有效时长单位 1：年 ，2：天',
  `generateCycle` int(10) DEFAULT NULL COMMENT '产币周期 1:天  2:小时',
  `profit1` decimal(20,8) DEFAULT '0.00000000' COMMENT '已领取收益',
  `profit2` decimal(20,8) DEFAULT '0.00000000' COMMENT '未领取收益',
  `profit3` decimal(20,8) DEFAULT '0.00000000' COMMENT '总产出收益',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL COMMENT '运行时间',
  `endDate` datetime DEFAULT NULL COMMENT '到期时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '矿机状态 1：待运行 2：运行中 3:已结束',
  PRIMARY KEY (`id`),
  KEY `customer_miner_minerId` (`minerId`) USING BTREE,
  KEY `customer_miner_customerId` (`customerId`) USING BTREE,
  KEY `customer_miner_orderId` (`orderId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户矿机表';

-- ----------------------------
-- Table structure for cm_customer_miner_profit
-- ----------------------------
DROP TABLE IF EXISTS `cm_customer_miner_profit`;
CREATE TABLE `cm_customer_miner_profit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `minerId` bigint(20) DEFAULT NULL COMMENT '矿机ID',
  `customerId` bigint(20) DEFAULT NULL COMMENT '购买人Id',
  `customerMinerId` bigint(20) DEFAULT NULL COMMENT '购买矿机Id',
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `minerName` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '矿机名称',
  `minerCode` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '矿机编号',
  `minerPrice` decimal(20,8) DEFAULT '0.00000000' COMMENT '矿机价钱',
  `profit1` decimal(20,8) DEFAULT '0.00000000' COMMENT '本次产币收益',
  `profit2` decimal(20,8) DEFAULT '0.00000000' COMMENT '累计产币收益',
  `status` tinyint(2) DEFAULT '1' COMMENT '发放状态 1.未发放 2已发放 3果树领取',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_miner_minerId` (`minerId`) USING BTREE,
  KEY `customer_miner_customerId` (`customerId`) USING BTREE,
  KEY `customer_miner_orderId` (`orderId`) USING BTREE,
  KEY `customer_miner_customerMinerId` (`customerMinerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1810 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户矿机产出记录';

-- ----------------------------
-- Table structure for cm_dividend_config
-- ----------------------------
DROP TABLE IF EXISTS `cm_dividend_config`;
CREATE TABLE `cm_dividend_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ratio` varchar(10) DEFAULT NULL COMMENT '分红比例',
  `feeType` varchar(20) DEFAULT NULL COMMENT '手续费参与类型 1.OTC   2.提币  3.币币交易 4: c2c',
  `type` int(1) DEFAULT NULL COMMENT '分红类型 1：果树领取 2：自动发放',
  `remark` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '说明',
  `saasid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='加权分红配置';

-- ----------------------------
-- Table structure for cm_dividend_record
-- ----------------------------
DROP TABLE IF EXISTS `cm_dividend_record`;
CREATE TABLE `cm_dividend_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customerId` bigint(20) NOT NULL COMMENT '用户id',
  `cmCustomerId` bigint(20) NOT NULL COMMENT '矿机会员表Id',
  `coinCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '币种',
  `dividendGross` decimal(20,10) DEFAULT '0.0000000000' COMMENT '加权分红总量',
  `ratio` varchar(10) DEFAULT NULL COMMENT '分红比例',
  `dividend` decimal(20,10) DEFAULT '0.0000000000' COMMENT '个人加权分红量',
  `status` tinyint(2) DEFAULT '1' COMMENT '分红是否领取 1未领取 2已领取',
  `saasid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='加权分红记录表';

-- ----------------------------
-- Table structure for cm_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `cm_exception_log`;
CREATE TABLE `cm_exception_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '方法名称',
  `exceptionText` varchar(255) COLLATE utf8_bin DEFAULT '0' COMMENT '异常原因',
  `ipaddress` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `customerId` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT NULL COMMENT '创建时间''',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `saasId` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2011 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时任务异常日志';

-- ----------------------------
-- Table structure for cm_fee_record
-- ----------------------------
DROP TABLE IF EXISTS `cm_fee_record`;
CREATE TABLE `cm_fee_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coinCode` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '币种',
  `feeMoney` decimal(20,8) DEFAULT '0.00000000' COMMENT '手续费',
  `feeType` tinyint(2) DEFAULT '0' COMMENT '手续费类型 1.币币交易手续费 2.提币手续费 3.OTC手续费  4.C2C手续费',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` tinyint(2) DEFAULT '1' COMMENT '发放状态 1未发放 2已发放 3不发放',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=768 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='手续费分红记录表';

-- ----------------------------
-- Table structure for cm_grade_miner
-- ----------------------------
DROP TABLE IF EXISTS `cm_grade_miner`;
CREATE TABLE `cm_grade_miner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade` int(255) DEFAULT '0' COMMENT '等级',
  `gradeName` varchar(255) DEFAULT NULL COMMENT '等级名称',
  `gradeCondition` decimal(20,8) DEFAULT '0.00000000' COMMENT '升级条件(自投要求)',
  `profitProportion` decimal(10,4) DEFAULT NULL COMMENT '动态收益比例',
  `cappingMultiple` decimal(10,4) DEFAULT '0.0000' COMMENT '封顶倍数',
  `remark` varchar(255) DEFAULT NULL,
  `saasId` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='矿工等级配置';

-- ----------------------------
-- Table structure for cm_grade_minercamps
-- ----------------------------
DROP TABLE IF EXISTS `cm_grade_minercamps`;
CREATE TABLE `cm_grade_minercamps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade` int(255) DEFAULT '0' COMMENT '等级',
  `gradeName` varchar(255) DEFAULT NULL COMMENT '等级名称',
  `thisInvestment` decimal(20,8) DEFAULT '0.00000000' COMMENT '自投要求',
  `teamInvestment` decimal(20,8) DEFAULT '0.00000000' COMMENT '团队投入要求',
  `maxTeamInvestment` decimal(20,8) DEFAULT '0.00000000' COMMENT '最大线投入要求',
  `teamProfitProportion` decimal(10,4) DEFAULT '0.0000' COMMENT '团队收入比例',
  `isBonus` tinyint(2) DEFAULT '1' COMMENT '是否加权分红 1否 2是',
  `remark` varchar(255) DEFAULT NULL,
  `saasId` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='矿场等级配置';

-- ----------------------------
-- Table structure for cm_grade_record
-- ----------------------------
DROP TABLE IF EXISTS `cm_grade_record`;
CREATE TABLE `cm_grade_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `oldGrade` int(10) DEFAULT NULL COMMENT '原等级',
  `oldGradeName` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '旧等级名称',
  `newGrade` int(10) DEFAULT NULL COMMENT '新等级',
  `newGradeName` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '新等级名称',
  `gradeType` tinyint(2) DEFAULT NULL COMMENT '等级类型 1矿工等级  2矿场等级',
  `saasId` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cm_grade_record_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for cm_miner
-- ----------------------------
DROP TABLE IF EXISTS `cm_miner`;
CREATE TABLE `cm_miner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `labelId` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `status` tinyint(4) DEFAULT '1' COMMENT '矿机状态 1：上架中 2：已下架 3:已售罄',
  `minerName` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '矿机名称',
  `minerCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '矿机编号',
  `minerPrice` decimal(20,8) DEFAULT '0.00000000' COMMENT '矿机价钱',
  `minerProfitType` tinyint(2) DEFAULT '1' COMMENT '收益领取类型：1自动发放 2果树领取',
  `payCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '支付币种',
  `profitCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '收益币种',
  `profitRate` decimal(10,4) DEFAULT '0.0000' COMMENT '收益倍数',
  `totalProfit` decimal(20,8) DEFAULT NULL COMMENT '总收益',
  `dayProfit` decimal(20,8) DEFAULT NULL COMMENT '日收益',
  `effectiveTimeLength` int(10) DEFAULT NULL COMMENT '有效时长',
  `timeLengthCompany` int(10) DEFAULT '1' COMMENT '有效时长单位 1：年 ，2：天',
  `generateCycle` int(10) DEFAULT NULL COMMENT '产币周期 1:天  2:小时',
  `pictureUrl` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '矿机图片路径',
  `totalNum` int(10) DEFAULT '0' COMMENT '矿机总数',
  `surplusNum` int(10) DEFAULT '0' COMMENT '矿机剩余数量',
  `coldNum` int(10) DEFAULT '0' COMMENT '购买冻结数量',
  `minNum` int(10) DEFAULT '0' COMMENT '最小购买数量',
  `maxNum` int(10) DEFAULT '0' COMMENT '最大购买数量',
  `progressProportion` int(8) DEFAULT '0' COMMENT '进度条显示比例',
  `isDisplay` tinyint(2) DEFAULT '1' COMMENT '是否显示进度条(1.不显示  2.显示 3.显示假进度条)',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='矿机基础信息表';

-- ----------------------------
-- Table structure for cm_miner_label
-- ----------------------------
DROP TABLE IF EXISTS `cm_miner_label`;
CREATE TABLE `cm_miner_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1上架中  2已下架',
  `lableKey` varchar(255) DEFAULT NULL COMMENT '标签Key',
  `lableName` varchar(255) DEFAULT NULL COMMENT '标签名称',
  `pictureUrl` varchar(255) DEFAULT NULL COMMENT '标签图标',
  `remark` varchar(255) DEFAULT NULL,
  `saasId` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='矿机标签';

-- ----------------------------
-- Table structure for cm_miner_pay_coin
-- ----------------------------
DROP TABLE IF EXISTS `cm_miner_pay_coin`;
CREATE TABLE `cm_miner_pay_coin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coinCode` varchar(255) DEFAULT NULL COMMENT '标签名称',
  `remark` varchar(255) DEFAULT NULL,
  `saasId` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='矿机标签';

-- ----------------------------
-- Table structure for cm_order
-- ----------------------------
DROP TABLE IF EXISTS `cm_order`;
CREATE TABLE `cm_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `minerId` bigint(20) DEFAULT NULL COMMENT '矿机ID',
  `customerId` bigint(20) DEFAULT NULL COMMENT '购买人Id',
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `minerName` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '矿机名称',
  `minerCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '矿机编号',
  `minerPrice` decimal(20,8) DEFAULT '0.00000000' COMMENT '矿机价钱',
  `minerProfitType` tinyint(2) DEFAULT '1' COMMENT '收益领取类型：1自动发放 2果树领取',
  `payCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '支付币种',
  `profitCoinCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '收益币种',
  `profitRate` decimal(10,4) DEFAULT '0.0000' COMMENT '收益倍数',
  `totalProfit` decimal(20,8) DEFAULT NULL COMMENT '总收益',
  `dayProfit` decimal(20,8) DEFAULT NULL COMMENT '日收益',
  `effectiveTimeLength` int(10) DEFAULT NULL COMMENT '有效时长',
  `timeLengthCompany` int(10) DEFAULT '1' COMMENT '有效时长单位 1：年 ，2：天',
  `generateCycle` int(10) DEFAULT NULL COMMENT '产币周期 1:天  2:小时',
  `buyNumber` int(10) DEFAULT '1' COMMENT '购买数量',
  `orderPrice` decimal(20,8) DEFAULT '0.00000000' COMMENT '订单总金额',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL COMMENT '运行时间',
  `endDate` datetime DEFAULT NULL COMMENT '到期时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '矿机状态 1：待运行 2：运行中 3:已结束',
  PRIMARY KEY (`id`),
  KEY `order_minerId` (`minerId`) USING BTREE,
  KEY `order_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='订单表';

-- ----------------------------
-- Table structure for cm_profit_one
-- ----------------------------
DROP TABLE IF EXISTS `cm_profit_one`;
CREATE TABLE `cm_profit_one` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '收益人ID',
  `customerTeamId` bigint(20) DEFAULT NULL COMMENT '产出币层级的推荐人id',
  `grade` int(255) DEFAULT '0' COMMENT '等级',
  `gradeName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '等级名称',
  `profitProportion` decimal(10,4) DEFAULT NULL COMMENT '动态收益比例',
  `cappingMultiple` decimal(10,4) DEFAULT '0.0000' COMMENT '封顶倍数',
  `customerProduce` decimal(20,8) DEFAULT '0.00000000' COMMENT '收益人当天产出总数',
  `subordinateProduce` decimal(20,8) DEFAULT '0.00000000' COMMENT '下级当天产出总数',
  `subordinateDynamicProfit` decimal(20,8) DEFAULT '0.00000000' COMMENT '下级当天动态收益',
  `profit1` decimal(20,8) DEFAULT '0.00000000' COMMENT '应发收益',
  `profit2` decimal(20,8) DEFAULT '0.00000000' COMMENT '实发收益',
  `profit3` decimal(20,8) DEFAULT '0.00000000' COMMENT '烧伤收益',
  `status` tinyint(2) DEFAULT '1' COMMENT '发放状态 1.未发放 2已发放',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号',
  PRIMARY KEY (`id`),
  KEY `customer_miner_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='矿工动态收益';

-- ----------------------------
-- Table structure for cm_profit_two
-- ----------------------------
DROP TABLE IF EXISTS `cm_profit_two`;
CREATE TABLE `cm_profit_two` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customerId` bigint(20) DEFAULT NULL COMMENT '收益人ID',
  `customerTeamId` bigint(20) DEFAULT NULL COMMENT '产出动态收益层级的推荐人id',
  `transactionNum` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号',
  `grade` int(255) DEFAULT '0' COMMENT '收益人矿场等级',
  `gradeName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '收益人等级名称',
  `proportion` decimal(10,4) DEFAULT NULL COMMENT '收益比例',
  `profit1` decimal(20,8) DEFAULT '0.00000000' COMMENT '分发收益基数',
  `profit2` decimal(20,8) DEFAULT '0.00000000' COMMENT '同级收益',
  `profit3` decimal(20,8) DEFAULT '0.00000000' COMMENT '获取收益',
  `status` tinyint(2) DEFAULT '1' COMMENT '发放状态 1.未发放 2已发放',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `saasId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `downProportion` decimal(10,4) DEFAULT NULL COMMENT '下级收益比例(减去的收益比例）',
  PRIMARY KEY (`id`),
  KEY `customer_profittow_customerId` (`customerId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='矿工动态收益';

-- ----------------------------
-- Table structure for cm_task_log
-- ----------------------------
DROP TABLE IF EXISTS `cm_task_log`;
CREATE TABLE `cm_task_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '{name:''方法名称''}',
  `isException` int(3) DEFAULT '0' COMMENT '{name:''是否出现异常 0否 1是''}',
  `ipaddress` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '{name:''ip地址''}',
  `lasttime` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '{name:''持续时间''}',
  `startDate` datetime DEFAULT NULL COMMENT '{name:''开始时间''}',
  `endDate` datetime DEFAULT NULL COMMENT '{name:''结束时间''}',
  `created` datetime DEFAULT NULL COMMENT '{name:''创建时间''}',
  `modified` datetime DEFAULT NULL COMMENT '{name:''修改时间''}',
  `saasid` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4697 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定时任务日志';
