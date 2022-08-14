package hry.admin.test.controller;

import hry.util.SpringUtil;
import hry.util.properties.PropertiesUtil;
import hry.util.properties.PropertiesUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HelloAppender extends AppenderSkeleton {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void append(LoggingEvent event) {
        String appname = PropertiesUtils.APP.getProperty("app.name");
        JdbcTemplate jdbcTemplate = SpringUtil.getBean("jdbcTemplate");
        jdbcTemplate.update("INSERT INTO app_globallog (log_level,log_time,app_id,log_detail,server_ip,log_err_location,app_name) VALUES ('"+event.getLevel()+"','"+dateFormat.format(new Date()) +"',1000,'"+event.getMessage()+"','192.168.0.51','"+event.getLocationInformation()+"','"+appname+"')");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean requiresLayout() {
        // TODO Auto-generated method stub
        return false;
    }


}
