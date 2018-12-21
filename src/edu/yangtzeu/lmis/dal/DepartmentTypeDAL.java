package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.DepartmentType;

public class DepartmentTypeDAL {
	public int add(AbstractModel object) throws Exception
	{
		if(object instanceof DepartmentType == false)
		{
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType dt = (DepartmentType) object;
		String sql = "insert into TB_DepartmentType(rdDeptType,rdDept"+"values(?,?)";
		Object[] params = new Object[2];
		params[0] = dt.getRdDeptType();
		params[1] = dt.getRdDept();
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int delete(AbstractModel object) throws Exception
	{
		if(object instanceof DepartmentType == false)
		{
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType dt = (DepartmentType) object;
		String sql = "delete from TB_DepartmentType where rdDept = ?";
		Object[] params = new Object[]{dt.getRdDept()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	public int update(AbstractModel object) throws Exception
	{
		if(object instanceof DepartmentType == false)
		{
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType dt = (DepartmentType) object;
		String sql = "update TB_DepartmentType set rdDept = ? where rdDeptTpe = ?";
		Object[] params = new Object[]{dt.getRdDeptType()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public DepartmentType getObjectByID(String rdDept) throws SQLException
	{
		DepartmentType dt = null;
		ResultSet rs = SQLHelper
				.getResultSet("select rdDeptType,rdDept from TB_DepartmentType where rdDept = "
						+"'"+rdDept+"'");
		if(rs != null)
		{
			if(rs.next())
			{
				dt = initDepartmentType(rs);
			}
			rs.close();
		}
		return dt;	
	}
	private DepartmentType initDepartmentType(ResultSet rs) throws SQLException
	{
		DepartmentType dt = new DepartmentType();
		dt.setRdDeptType(rs.getInt("rdDeptType"));
		dt.setRdDept(rs.getString("rdDept"));
		return dt;
	}
	
	
	public AbstractModel[] getAllobjects() throws Exception
	{
		ArrayList<DepartmentType> objects = new ArrayList<DepartmentType>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_DepartmentType");
		if(rs != null)
		{
			while(rs.next())
			{
				DepartmentType dt = initDepartmentType(rs);
				objects.add(dt);
			}
			rs.close();
		}
		DepartmentType[] types = new DepartmentType[objects.size()];
		objects.toArray(types);
		return types;
		
	}
	

}
