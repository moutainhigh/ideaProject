package hry.listener;


import com.alibaba.fastjson.JSONObject;
import hry.admin.web.model.AppServerMonitor;
import hry.admin.web.model.MailConfig;
import hry.admin.web.service.AppServerMonitorService;
import hry.admin.web.service.MailConfigService;
import hry.redis.common.utils.RedisService;
import hry.util.EmailUtil;
import hry.util.SpringUtil;
import hry.util.date.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Date;
import java.util.List;

public class ServerManagement{
    private static Logger logger = Logger.getLogger(ServerManagement.class);

    public void heartbeatConnection(){
        //logger.error("10秒一次定时查询服务器状态-------"+logger);
        //logger.error("10秒一次定时查询服务器状态-------"+logger);
        AppServerMonitorService appServerMonitorService = SpringUtil.getBean("appServerMonitorService");
        List<AppServerMonitor> appServerMonitorServiceAll = appServerMonitorService.findAll();
        ThreadPoolTaskExecutor taskExecutor= SpringUtil.getBean("serverManagementTaskExecutor");
        for ( AppServerMonitor  serverMonitor:appServerMonitorServiceAll){
            //对启用的服务器监控
            if(serverMonitor.getIsOpen()==1){
                taskExecutor.execute(new ServerManagementThread(serverMonitor));
            }
        }
    }

    public static boolean isOnline(String hostname, int port) {
        Socket server = null;
        try {
            server = new Socket();
            InetSocketAddress address = new InetSocketAddress(hostname, port);//例如 www.baidu.com 80
            server.connect(address, 3000);
            //logger.error("服务器"+hostname+":"+port+"连接成功");
            return true;
        }
        catch (UnknownHostException e) {
            //logger.error("服务器"+hostname+":"+port+"无法连接");
        } catch (IOException e) {
            //logger.error("服务器"+hostname+":"+port+"无法连接");
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public static String postSend(String strUrl,String param){


        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);//连接超时
            connection.setReadTimeout(5000);//读取超时
            connection.connect();

            //POST方法时使用
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            //logger.error("服务器"+strUrl+"连接成功");
            return buffer.toString();
        } catch (IOException e) {
            //logger.error("服务器"+strUrl+"无法连接");
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
    class  ServerManagementThread implements Runnable{
        private  AppServerMonitor serverMonitor;
        private final RedisService redisService = SpringUtil.getBean("redisService");
        private final MailConfigService mailConfigService = SpringUtil.getBean("mailConfigService");
        private final AppServerMonitorService appServerMonitorService = SpringUtil.getBean("appServerMonitorService");
        ServerManagementThread(AppServerMonitor serverMonitor){
            this.serverMonitor=serverMonitor;
        }
        @Override
        public void run() {
            if("front".equals(serverMonitor.getType())){
                String result=postSend("http://"+serverMonitor.getServerHost()+":"+serverMonitor.getServerPort()+"/ServerManagement/heartbeatConnection","");
                if(null !=result){
                    JSONObject jsonObject= JSONObject.parseObject(result);
                    if(jsonObject.getBoolean("success")){//如果返回成功
                        querySuccess();
                    }else{
                        queryFail();
                    }
                }else{
                    queryFail();
                }
            }else if("service".equals(serverMonitor.getType())){
                String result=postSend("http://"+serverMonitor.getServerHost()+":"+serverMonitor.getServerPort()+"/"+serverMonitor.getType()+"/static/ServerManagement/heartbeatConnection","");
                if(null !=result){
                    JSONObject jsonObject= JSONObject.parseObject(result);
                    //如果返回成功
                    if(jsonObject.getBoolean("success")){
                        querySuccess();
                    }else{
                        queryFail();
                    }
                }else{
                    queryFail();
                }
            }else if("trade".equals(serverMonitor.getType())){
                String result=postSend("http://"+serverMonitor.getServerHost()+":"+serverMonitor.getServerPort()+"/trade/ServerManagement/heartbeatConnection","");
                if(null !=result){
                    JSONObject jsonObject= JSONObject.parseObject(result);
                    //如果返回成功
                    if(jsonObject.getBoolean("success")){
                        querySuccess();
                    }else{
                        queryFail();
                    }
                }else{
                    queryFail();
                }
            }else{
                boolean online = isOnline(serverMonitor.getServerHost(), serverMonitor.getServerPort());
                if(online){
                    querySuccess();
                }else{
                    queryFail();
                }
            }
        }

        private void querySuccess() {
            String iserror = redisService.get("ServerManagement:" + serverMonitor.getServerHost() + ":" + serverMonitor.getServerPort());
            if(!"success".equals(iserror)){//如果是失败后第一次查询成功，就把失败的值改掉
                AppServerMonitor sm=new AppServerMonitor();
                sm.setId(serverMonitor.getId());
                sm.setCreated(serverMonitor.getCreated());
                sm.setServerStruts(1);
                redisService.save("ServerManagement:"+serverMonitor.getServerHost()+":"+serverMonitor.getServerPort(),"success");
                appServerMonitorService.update(sm);
                //发送邮件
                sendEmail(true);

            }
        }

        private void queryFail() {
            String iserror = redisService.get("ServerManagement:" + serverMonitor.getServerHost() + ":" + serverMonitor.getServerPort());
            //logger.info("请求失败，查看redis中记录的值是否是error："+iserror);
            if(!"error".equals(iserror)){//如果是第一次查询失败，就把值改掉
                redisService.save("ServerManagement:"+serverMonitor.getServerHost()+":"+serverMonitor.getServerPort(),"error");
                AppServerMonitor sm=new AppServerMonitor();
                sm.setId(serverMonitor.getId());
                sm.setCreated(serverMonitor.getCreated());
                sm.setServerStruts(0);
                sm.setFailTime(new Date());
                appServerMonitorService.update(sm);
                //发送邮件
                sendEmail(false);
            }
        }

        /**
         * 发送邮件
         * @param flag 成功是true  失败是false
         */
        private void sendEmail(Boolean flag) {
            if(null!=serverMonitor.getSendEmails()&&!"".equals(serverMonitor.getSendEmails().trim())){
                //查询邮箱配置
                MailConfig mailConfig = mailConfigService.get(serverMonitor.getMailConfigId());
                String title=serverMonitor.getServerName();
                String content ="管理员你好：</br>"+serverMonitor.getServerName();
                if(flag){
                    title=title+"成功启动";
                    int allMinute = DateUtil.compareDateMinute(new Date(), serverMonitor.getFailTime());
                    int day=allMinute/60/24;
                    int otherday=allMinute%(24*60);
                    int hour=otherday/60;
                    int minute=otherday%60;
                    content=content+"修复完成了,工程师处理问题所用时间："+day+"天"+hour+"小时"+minute+"分钟";
                    if(allMinute<60){
                        content=content+"</br>超越了90%的工程师，速度突破天际";
                    }else if(60<allMinute&&allMinute<120){
                        content=content+"</br>超越了80%的工程师，速度飞起";
                    }else if(120<allMinute&&allMinute<600){
                        content=content+"</br>超越了60%的工程师，继续努力";
                    }else if(600<allMinute&&allMinute<1440){
                        content=content+"</br>超越了10%的工程师，还可以拯救";
                    }else{
                        content=content+"</br>1%的工程师，没救了";
                    }
                }else{
                    title=title+"紧急报告";
                    content=content+"停止运行了，请尽快处理!</br>服务器地址："+serverMonitor.getServerHost();
                    content=content+"</br>服务器端口："+serverMonitor.getServerPort();
                }
                String[] emails = serverMonitor.getSendEmails().trim().split(",");
                if(emails.length>0){
                    for (String emailUser:emails) {
                        EmailUtil.sendMail(mailConfig,title,content,emailUser);
                    }
                }
            }
        }
    }
}

