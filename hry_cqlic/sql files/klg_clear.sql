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