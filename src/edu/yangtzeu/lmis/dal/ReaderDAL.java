package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;


public class ReaderDAL {
	private String[] dispColNames = new String[] {"ID","姓名","性别","类型","院系","电话","email","状态","已借书数量","注册日期"};
	
	private String[] methodNames = new String[]	{"getRdID","getRdName","getRdSex","getRdType","getRdDept",
			"getRdPhone","getRdEmail","getRdStatus","getRdBorrowQty","getRdDateReg"};
	
	public String[] getPrettyColumnNames()
	{
		return dispColNames;
	}
	
	public String[] getMethodNames()
	{
		return methodNames;
	}
	public int add(AbstractModel object) throws Exception
	{
		if(object instanceof Reader == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Reader rd = (Reader) object;
		String sql = "insert into TB_Reader(rdID,"
				+"rdName,rdSex,rdType,rdDept,"
				+"rdPhone,rdEmail,rdDateReg,"
				+"rdPhoto,rdStatus,rdBorrowQty,"
				+"rdPwd,rdAdminRoles)"
				+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[13];
		params[0] = rd.getRdID();
		params[1] = rd.getRdName();
		params[2] = rd.getRdSex();
		params[3] = rd.getRdType();
		params[4] = rd.getRdDept();
		params[5] = rd.getRdPhone();
		params[6] = rd.getRdEmail();
		params[7] = rd.getRdDateReg();
		params[8] = rd.getRdPhoto();
		params[9] = rd.getRdStatus();
		params[10] = rd.getRdBorrowQty();
		params[11] = rd.getRdPwd();
		params[12] = rd.getRdAdminRoles();
		
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int delete(AbstractModel object) throws Exception
	{
		if(object instanceof Reader == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Reader rd = (Reader) object;
		String sql = "delete from TB_Reader where rdID = ?";
		Object[] params = new Object[]{rd.getRdID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	public int update(AbstractModel object) throws Exception
	{
		if(object instanceof Reader == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Reader rd = (Reader) object;
		String sql = "update TB_Reader set "
				+"rdName = ?,rdSex = ?,rdType = ?,"
				+"rdDept = ?,rdPhone = ?,rdEmail = ?,"
				+"rdDateReg = ?,rdPhoto = ?,rdStatus = ?,"
				+"rdBorrowQty = ?,rdPwd = ?,rdAdminRoles = ? where rdID = ?";
		Object[] params = new Object[]{rd.getRdName(),
				rd.getRdSex(),rd.getRdType(),rd.getRdDept(),rd.getRdPhone(),
				rd.getRdEmail(),rd.getRdDateReg(),rd.getRdPhoto(),rd.getRdStatus(),rd.getRdBorrowQty(),
				rd.getRdPwd(),rd.getRdAdminRoles(),rd.getRdID()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int lost(AbstractModel object) throws Exception
	{
		if(object instanceof Reader == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Reader rd = (Reader) object;
		String sql = "update TB_Reader set rdStatus = '挂失' where rdID = ?";
		Object[] params = new Object[]{rd.getRdID()};
		return SQLHelper.ExecSql(sql,params);
	}

	public int found(AbstractModel object) throws Exception
	{
		if(object instanceof Reader == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Reader rd = (Reader) object;
		String sql = "update TB_Reader set rdStatus = '有效' where rdID = ?";
		Object[] params = new Object[]{rd.getRdID()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public Reader getObjectByID(int rdID) throws SQLException
	{
		Reader rd = null;
		ResultSet rs = SQLHelper
				.getResultSet("select rdID,rdName,rdSex,rdType,rdDept,"
						+"rdPhone,rdEmail,rdDateReg,rdPhoto,rdStatus,"
						+"rdBorrowQty,rdPwd,rdAdminRoles from TB_Reader where rdID="+rdID);
		if(rs != null)
		{
			if(rs.next())
			{
				rd = initReader(rs);
			}
			rs.close();
		}
		return rd;	
	}
	private Reader initReader(ResultSet rs) throws SQLException
	{
		Reader rd = new Reader();
		rd.setRdID(rs.getInt("rdID"));
		rd.setRdName(rs.getString("rdName"));
		rd.setRdSex(rs.getString("rdSex"));
		rd.setRdType(rs.getShort("rdType"));
		rd.setRdDept(rs.getString("rdDept"));
		rd.setRdPhone(rs.getString("rdPhone"));
		rd.setRdEmail(rs.getString("rdEmail"));
		rd.setRdDateReg(rs.getDate("rdDateReg"));
		rd.setRdPhoto(rs.getBytes("rdPhoto"));
		rd.setRdStatus(rs.getString("rdStatus"));
		rd.setRdBorrowQty(rs.getInt("rdBorrowQty"));
		rd.setRdPwd(rs.getString("rdPwd"));
		rd.setRdAdminRoles(rs.getInt("rdAdminRoles"));
		
		return rd;
		
	}
	public AbstractModel[] getAllobjects() throws Exception
	{
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Reader");
		if(rs != null)
		{
			while(rs.next())
			{
				Reader rd = initReader(rs);
				objects.add(rd);
			}
			rs.close();
		}
		Reader[] types = new Reader[objects.size()];
		objects.toArray(types);
		return types;
		
	}

	public Reader[] getReadersBy(ReaderType rdTypeName, DepartmentType deptType, String userName) throws SQLException {
		ArrayList<Reader> readers = new ArrayList<Reader>();
		String sql = "select * from TB_Reader where rdType = ? and rdDept = ? and rdName like ?";
		Object[] params = new Object[] {rdTypeName.getRdType(),deptType.getRdDept(),"%" + userName + "%"};
		
		ResultSet rs = SQLHelper.getResultSet(sql, params);
		if(rs != null)
		{
			while(rs.next())
			{
				Reader reader = initReader(rs);
				readers.add(reader);
			}
			rs.close();
		}
		if(readers.size() > 0)
		{
			Reader[] array = new Reader[readers.size()];
			readers.toArray(array);
			return array;
		}
		
		return null;
	}

	public Reader[] find(String rdTypeName, String deptType, String userName)throws SQLException {
		ArrayList<Reader> readers = new ArrayList<Reader>();
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Reader where rdType = '"+rdTypeName +"' and rdDeptType = '"+ deptType +"' and rdName like '%" +userName +"%'");
		
		if(rs != null)
		{
			while(rs.next())
			{
				Reader reader = initReader(rs);
				readers.add(reader);
			}
			rs.close();
		}
		if(readers.size() > 0)
		{
			Reader[] array = new Reader[readers.size()];
			readers.toArray(array);
			return array;
		}
		return null;
	}

	public void addBorrowQty(int rdID, int number) {
		number = number + 1;
		SQLHelper.ExecSql("update TB_Reader set rdBorrowQty = '"+number+"' where rdID = '"+rdID+"'");
		
	}

	public void delBorrowQty(int rdID, int number) {
		number = number - 1;
		SQLHelper.ExecSql("update TB_Reader set rdBorrowQty = '"+number+"' where rdID = '"+rdID+"'");
	}

	

}
