delete from klg_buy_transaction;
delete from klg_buysell_account_record;
delete from klg_exception_log;
delete from klg_reward;
delete from klg_sell_transaction;
delete from klg_task_log;
delete from klg_teamlevel;
delete from klg_transaction_record;
delete from klg_transfer_accounts;
delete from klg_exception_log;
delete from klg_task_log;
delete from klg_forecast;
delete from klg_prizedraw_selection;
delete from klg_prizedraw_lssue;
delete from klg_prizedraw_customer;

INSERT INTO `klg_forecast` (`id`, `created`, `modified`, `saasid`, `yesterdaySurplus`, `todaySurplus`, `todayForecast`, `tomorrowForecast`, `forecastInterval`, `forecastTime`) VALUES ('4', '2019-06-03 17:55:00', '2019-06-03 20:30:00', NULL, '0.000000', '0.000000', '0.000000', '0.000000', '7', '2019-06-03 17:55:00');

delete from klg_buysell_account;
INSERT INTO `klg_buysell_account` (`id`, `accountName`, `coinCode`, `money`, `created`, `modified`, `saasId`) VALUES ('1', 'buySurplusAccount', 'USDT', '0.00000000', '2019-04-19 11:06:18', '2019-04-25 17:09:46', '买单支付剩余账户');
INSERT INTO `klg_buysell_account` (`id`, `accountName`, `coinCode`, `money`, `created`, `modified`, `saasId`) VALUES ('2', 'sellSurplusAccount', 'SME', '0.00000000', '2019-04-19 11:35:47', '2019-04-25 17:09:46', '卖单卖出剩余账户');

-- 2019年5月13日 15点35分 lzy--
delete from klg_amount_limitation_record;
delete from klg_assets_record;
delete from klg_customer_level;
delete from klg_designated_reward_record;
delete from klg_platform_account_record;
delete from klg_reward;
delete from klg_transaction_record;
delete from klg_transfer_accounts;
delete from klg_upgrade_record;
delete from klg_level_config;
update klg_platform_account set money=0;



-- 个人账号表
TRUNCATE TABLE app_customer;
-- 个人信息
TRUNCATE TABLE app_person_info;
-- 个人银行卡表
TRUNCATE TABLE app_bank_card;
TRUNCATE TABLE ex_api_apply;
TRUNCATE TABLE app_commend_user;
-- 个人账户表
TRUNCATE TABLE app_account;
--  个人热账户流水表
TRUNCATE TABLE app_hot_account_record;
TRUNCATE TABLE app_sms_send;
TRUNCATE TABLE mail;
TRUNCATE TABLE app_message;
-- 个人冷账户流水表
TRUNCATE TABLE app_cold_account_record;
TRUNCATE TABLE ex_digitalmoney_account;
TRUNCATE TABLE ex_dm_cold_account_record;
TRUNCATE TABLE ex_dm_hot_account_record;
TRUNCATE TABLE ex_dm_customer_publickey;
TRUNCATE TABLE ex_dm_transaction;
TRUNCATE TABLE ex_lend_account_info;

TRUNCATE TABLE ico_account;
TRUNCATE TABLE ico_award_record;
TRUNCATE TABLE ico_customer_level;
TRUNCATE TABLE ico_buy_order;
TRUNCATE TABLE ico_dividend_record;
TRUNCATE TABLE ico_experience;
TRUNCATE TABLE ico_lock_record;
TRUNCATE TABLE ico_lock_reward;
TRUNCATE TABLE ico_rule_detailed;
TRUNCATE TABLE ico_transaction_record;
TRUNCATE TABLE ico_transfer_accounts;
TRUNCATE TABLE ico_upgrade_record;
TRUNCATE TABLE ico_award_record;
TRUNCATE TABLE ico_send_exp;
TRUNCATE TABLE ico_dividend_manual_record;


-- 李超项目清库
TRUNCATE TABLE app_team_level;
TRUNCATE TABLE lc_level_config;
TRUNCATE TABLE lc_team_level_config;
TRUNCATE TABLE lc_customer_level;
TRUNCATE TABLE lc_apply_team_award;
TRUNCATE TABLE lc_apply_record;
TRUNCATE TABLE lc_exception_log;
TRUNCATE TABLE lc_out_info;
TRUNCATE TABLE lc_deal_record;
TRUNCATE TABLE lc_task_log;
TRUNCATE TABLE app_team_level;
TRUNCATE TABLE lc_customer_level_record;
TRUNCATE TABLE lc_release_rule_details;


TRUNCATE TABLE app_sms_send;

