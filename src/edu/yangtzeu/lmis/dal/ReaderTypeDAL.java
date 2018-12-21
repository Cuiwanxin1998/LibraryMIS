package edu.yangtzeu.lmis.dal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderTypeDAL {
	
	private String[] dispColNames = new String[] {"ID","读者类型","可借数量","可借天数","可续借次数","罚金率","证件有效期"};
	
	private String[] methodNames = new String[]	{"getRdType","getRdTypeName","getCanLendQty","getCanLendDay","getCanContinueTimes","getPunishRate",
			"getDateValid"};
	
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
		if(object instanceof ReaderType == false)
		{
			throw new Exception("Can only handle ReaderType");
		}
		ReaderType rt = (ReaderType) object;
		String sql = "insert into TB_ReaderType(rdType,"
				+"rdTypeName,CanLendQty,CanLendDay,"
				+"CanContinueTimes,PunishRate,DateValid)"
				+"values(?,?,?,?,?,?,?)";
		Object[] params = new Object[7];
		params[0] = rt.getRdType();
		params[1] = rt.getRdTypeName();
		params[2] = rt.getCanLendQty();
		params[3] = rt.getCanLendDay();
		params[4] = rt.getCanContinueTimes();
		params[5] = rt.getPunishRate();
		params[6] = rt.getDateValid();
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int delete(AbstractModel object) throws Exception
	{
		if(object instanceof ReaderType == false)
		{
			throw new Exception("Can only handle ReaderType");
		}
		ReaderType rt = (ReaderType) object;
		String sql = "delete from TB_ReaderType where rdType = ?";
		Object[] params = new Object[]{rt.getRdType()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	public int update(AbstractModel object) throws Exception
	{
		if(object instanceof ReaderType == false)
		{
			throw new Exception("Can only handle ReaderType");
		}
		ReaderType rt = (ReaderType) object;
		String sql = "update TB_ReaderType set rdTypeName = ?,"
				+"CanLendQty = ?,CanLendDay = ?,"
				+"CanContinueTimes = ?,PunishRate = ?,"
				+"DateValid = ? where rdType = ?";
		Object[] params = new Object[]{rt.getRdTypeName(),
				rt.getCanLendQty(),rt.getCanLendDay(),
				rt.getCanContinueTimes(),rt.getPunishRate(),
				rt.getDateValid(),rt.getRdType()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public ReaderType getObjectByID(int rdType) throws SQLException
	{
		ReaderType rt = null;
		ResultSet rs = SQLHelper
				.getResultSet("select rdType,rdTypeName,CanLendQty"
						+",CanLendDay,CanContinueTimes,PunishRate"
						+",DateValid from TB_ReaderType where rdType="
						+"'"+rdType+"'");
		if(rs != null)
		{
			if(rs.next())
			{
				rt = initReaderType(rs);
			}
			rs.close();
		}
		return rt;	
	}
	private ReaderType initReaderType(ResultSet rs) throws SQLException
	{
		ReaderType rt = new ReaderType();
		rt.setRdType(rs.getInt("rdType"));
		rt.setRdTypeName(rs.getString("rdTypeName"));
		rt.setCanLendQty(rs.getInt("CanLendQty"));
		rt.setCanLendDay(rs.getInt("CanLendDay"));
		rt.setCanContinueTimes(rs.getInt("CanContinueTimes"));
		rt.setPunishRate(rs.getFloat("PunishRate"));
		rt.setDateValid(rs.getInt("DateValid"));
		return rt;
	}
	
//	private String initrdTypeName(ResultSet rs) throws SQLException
//	{
//		ReaderType rt = new ReaderType();
//		return rt.setRdTypeNames(rs.getString("rdTypeName")); 
//	}

	public AbstractModel[] getAllobjects() throws Exception
	{
		ArrayList<ReaderType> objects = new ArrayList<ReaderType>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_ReaderType");
		if(rs != null)
		{
			while(rs.next())
			{
				ReaderType rt = initReaderType(rs);
				objects.add(rt);
			}
			rs.close();
		}
		ReaderType[] types = new ReaderType[objects.size()];
		objects.toArray(types);

		return types;		
	}
	
//	public String[] getrdTypeName() throws Exception
//	{
//		ArrayList<String> objects = new ArrayList<String>();
//		ResultSet rs = SQLHelper.getResultSet("select rdTypeName from TB_ReaderType");
//		if(rs != null)
//		{
//			while(rs.next())
//			{
//				String rt = initrdTypeName(rs);
//				objects.add(rt);
//			}
//			rs.close();
//		}
//		String[] types = new String[objects.size()];
//		objects.toArray(types);
//
//		return types;		
//	}
//	
	
}


