/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-26 10:06:47 
 */
package hry.admin.test.service.impl;

import hry.admin.test.model.Student;
import hry.admin.test.service.StudentService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> StudentService </p>
 * @author:         liushilei
 * @Date :          2018-06-26 10:06:47  
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student, Long> implements StudentService{
	
	@Resource(name="studentDao")
	@Override
	public void setDao(BaseDao<Student, Long> dao) {
		super.dao = dao;
	}
	

}
