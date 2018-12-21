package edu.yangtzeu.lmis.bll;

import edu.yangtzeu.lmis.dal.DepartmentTypeDAL;
import edu.yangtzeu.lmis.model.DepartmentType;

public class DepartmentTypeAdmin {
	DepartmentTypeDAL dal = new DepartmentTypeDAL();
	
	public DepartmentType[] getDepartmentTypes()
	{
		try
		{
			return (DepartmentType[]) dal.getAllobjects();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public DepartmentType getObjectByID(String rdDept) {
		try
		{
			return (DepartmentType) dal.getObjectByID(rdDept);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
