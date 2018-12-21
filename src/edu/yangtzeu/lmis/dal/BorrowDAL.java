package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class BorrowDAL {
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private ReaderAdmin readerBll = new ReaderAdmin();
	public int add(AbstractModel object) throws Exception
	{
		if(object instanceof Borrow == false)
		{
			throw new Exception("Can only handle Borrow");
		}
		Borrow bw = (Borrow) object;
		String sql = "insert into TB_Borrow("
				+"rdID,bkID,IdDateOut,"
				+"IdDateRetPlan,IdOverDay,IdOverMoney) "
				+"values(?,?,?,?,?,?)";
		Object[] params = new Object[6];
		params[0] = bw.getRdID();
		params[1] = bw.getBkID();
		params[2] = bw.getIdDateOut();
		params[3] = bw.getIdDateRetPlan();
		params[4] = bw.getIdOverDay();
		params[5] = bw.getIdOverMoney();
		
		return SQLHelper.ExecSql(sql,params);
	}
	
	
	
	public int delete(AbstractModel object) throws Exception
	{
		if(object instanceof Borrow == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Borrow bw = (Borrow) object;
		String sql = "delete from TB_Borrow where BorrowID = ?";
		Object[] params = new Object[]{bw.getBorrowID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	public int update(AbstractModel object) throws Exception
	{
		if(object instanceof Borrow == false)
		{
			throw new Exception("Can only handle Reader");
		}
		Borrow bw = (Borrow) object;
		String sql = "update TB_Borrow set BorrowID = ?,"
				+"rdID = ?,bkID = ?,IdContinueTimes = ?,IdDateOut = ?,"
				+"IdDateRetPlan = ?,IdOverRetAct = ?,IdOverDay = ?,IdOverMoney = ?,"
				+"IdPunishMoney = ?,IsHasReturn = ?,OperatorLend = ?,OperatorRet = ?,";
		Object[] params = new Object[]{bw.getBorrowID(),bw.getRdID(),
				bw.getBkID(),bw.getIdContinueTimes(),bw.getIdDateOut(),bw.getIdDateRetPlan(),
				bw.getIdOverRetAct(),bw.getIdOverDay(),bw.getIdOverMoney(),bw.getIdPunishMoney(),
				bw.getIsHasReturn(),bw.getOperatorLend(),bw.getOperatorRet()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public Borrow getObjectByID(int rdID) throws SQLException
	{
		Borrow bw = null;
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Borrow where rdID = '"+rdID+"'");
		if(rs != null)
		{
			if(rs.next())
			{
				bw = initBorrow(rs);
			}
			rs.close();
		}
		return bw;	
	}
	private Borrow initBorrow(ResultSet rs) throws SQLException
	{

		Borrow bw = new Borrow();
		bw.setBorrowID(rs.getInt("BorrowID"));
		bw.setRdID(rs.getInt("rdID"));
		bw.setBkID(rs.getInt("bkID"));
		bw.setIdContinueTimes(rs.getInt("IdContinueTimes"));
		bw.setIdDateOut(rs.getDate("IdDateOut"));
		bw.setIdDateRetPlan(rs.getDate("IdDateRetPlan"));
		bw.setIdOverRetAct(rs.getDate("IdOverRetAct"));
		bw.setIdOverDay(rs.getInt("IdOverDay"));
		bw.setIdOverMoney(rs.getLong("IdOverMoney"));
		bw.setIdPunishMoney(rs.getLong("IdPunishMoney"));
		bw.setIsHasReturn(rs.getBoolean("IsHasReturn"));
		bw.setOperatorLend(rs.getString("OperatorLend"));
		bw.setOperatorRet(rs.getString("OperatorRet"));
		return bw;	
	}
	

	
	public AbstractModel[] getAllobjects() throws Exception
	{
		ArrayList<Borrow> objects = new ArrayList<Borrow>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow");
		if(rs != null)
		{
			while(rs.next())
			{
				Borrow bw = initBorrow(rs);
				objects.add(bw);
			}
			rs.close();
		}
		Borrow[] types = new Borrow[objects.size()];
		objects.toArray(types);
		return types;
		
	}

	public void Lend(int bkID, int canLend, int rdID, String now, String future) {
		canLend=canLend+1;
		SQLHelper.ExecSql("update TB_Borrow set IdContinueTimes = '"+ canLend +"' ,IdDateOut = '" +now+"' ,IdDateRetPlan ='"+future+ "' where bkID = '" + bkID +"' and rdID ='"+rdID +"'");
	}



	public void del(int bkID, int rdID) {
		SQLHelper.ExecSql("delete from tb_borrow where bkID = '" + bkID +"' and rdID = '"+rdID +"'");	
	}
	
	public void getRetPlan(int rdID) throws SQLException
	{
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow where rdID = '" +rdID +"'");
		Date now = new Date();
		Reader reader = readerBll.getReader(rdID);
		ReaderType readerType = readerTypeBll.getObjectByID(reader.getRdType());
		if(rs != null)
		{
			while(rs.next())
			{
				Borrow bw = initBorrow(rs);
				
				int day = (int)((now.getTime() - bw.getIdDateRetPlan().getTime())/(24*60*60*1000));
				
				if(day>0)
				{
					float money = day*readerType.getPunishRate();
					SQLHelper.ExecSql("update TB_Borrow set IdOverDay = '"+day+"' ,IdOverMoney = '"+money+"' where bkID = '" + 
					bw.getBkID() +"' and rdID = '"+bw.getRdID() +"'");	
				}
				
			}
		rs.close();				
		}
		
	}



	public Borrow money(int bkID, int rdID) throws SQLException {
		Borrow bw = null;
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Borrow where rdID = '"+rdID+"' and bkID = '"+bkID+"'");
		if(rs != null)
		{
			while(rs.next())
			{
				bw = initBorrow(rs);
			}
			rs.close();
		}
		return bw;	
	}



	public void continue_update(int rdID, int bkID) {
		SQLHelper.ExecSql("update TB_Borrow set IdOverDay = '0' , IdOverMoney = '0' ,IdPunishMoney = '0' where rdID = '"+rdID+"' and bkID = '" +bkID +"'");
		
	}



	public boolean OverDay(int rdID) throws SQLException {
		Borrow bw = null;
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Borrow where rdID = '"+rdID +"'");
		if(rs != null)
		{
			while(rs.next())
			{
				bw = initBorrow(rs);
				if(bw.getIdOverDay()>0)
					return true;
			}
		}
		return false;
	}



	public Borrow getNameBorrow(int rdID, int bkID) throws SQLException {
		Borrow bw = null;
		ResultSet rs = SQLHelper
				.getResultSet("select * from TB_Borrow where rdID ='"+rdID+"' and bkID = '"+bkID+"'");
		if(rs != null)
		{
			while(rs.next())
			{
				bw = initBorrow(rs);
			}
			rs.close();
		}
		return bw;	
		
	}
}
