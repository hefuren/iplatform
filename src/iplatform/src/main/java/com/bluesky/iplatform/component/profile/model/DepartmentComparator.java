package com.bluesky.iplatform.component.profile.model;

import java.util.Comparator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 部门比较器
 * @author ElwinHe
 *
 */
@Component(value = "DepartmentComparator")
@Scope(value = "prototype")
public class DepartmentComparator<T>  implements Comparator<T> {

	private String orderBy;
	
	public DepartmentComparator() {

	}
	public DepartmentComparator(String orderBy) {
		this.orderBy = orderBy;
	}

	public int compare(Object arg0, Object arg1) {
		int result = 0;
		Department dept_1 = (Department) arg0;
		Department dept_2 = (Department) arg1;

		if ("LEVEL".equals(orderBy.toUpperCase())) {
			result = dept_1.getLevel().compareTo(dept_2.getLevel());
		}else{
			result = dept_1.getSeqno().compareTo(dept_2.getSeqno());
		}

		return result;
	}

}
