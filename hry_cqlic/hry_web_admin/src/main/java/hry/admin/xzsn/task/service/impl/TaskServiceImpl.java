/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2022-05-19 10:31:38 
 */
package hry.admin.xzsn.task.service.impl;

import hry.admin.xzsn.task.model.Task;
import hry.admin.xzsn.task.service.TaskService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> TaskService </p>
 * @author:         zhouming
 * @Date :          2022-05-19 10:31:38  
 */
@Service("taskService")
public class TaskServiceImpl extends BaseServiceImpl<Task, Long> implements TaskService{
	
	@Resource(name="taskDao")
	@Override
	public void setDao(BaseDao<Task, Long> dao) {
		super.dao = dao;
	}
	

}
