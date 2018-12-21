package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;
import java.util.Date;

import edu.yangtzeu.lmis.dal.BorrowDAL;
import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.Reader;

public class BorrowAdmin extends LibraryBLL {
	private BorrowDAL dal = new BorrowDAL();

	
	public Borrow getBorrow(int rdID)
	{
		try
		{
			return (Borrow) dal.getObjectByID(rdID);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void updatePlan(int rdID)
	{
		try
		{
			dal.getRetPlan(rdID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void add(Borrow borrow) {
		try
		{
			dal.add(borrow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void Lend(int bkID, int canLend, int rdID, String now, String future) {
		try
		{
			dal.Lend(bkID,canLend,rdID,now,future);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void del(int bkID, int rdID) {
		try
		{
			dal.del(bkID,rdID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Borrow Money(int bkID, int rdID) throws SQLException {
		return dal.money(bkID,rdID);
	}

	public void continueupdate(int rdID,int bkID) {
		try
		{
			dal.continue_update(rdID,bkID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public boolean OverDay(int rdID) throws SQLException 
	{
		return dal.OverDay(rdID);
	}

	public Borrow getNameBorrow(int rdID, int bkID) throws SQLException {
		return dal.getNameBorrow(rdID,bkID);
	}
	


	
	

}
