package hry.financail.listener;

import hry.util.sys.AppUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/5/10 11:23
 * @Description:
 */
public class StartupListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        //初始化应用程序工具类
        AppUtils.init(event.getServletContext());

    }
}
