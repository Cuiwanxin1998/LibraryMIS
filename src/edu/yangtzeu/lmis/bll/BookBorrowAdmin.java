package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BookBorrowDAL;
import edu.yangtzeu.lmis.model.BookBorrow;
import edu.yangtzeu.lmis.model.Borrow;


public class BookBorrowAdmin extends LibraryBLL{
	private BookBorrowDAL dal = new BookBorrowDAL();
	
	public String[] getDisplayColumnNames()
	{
		return dal.getPrettyColumnNames();
	}
	public String[] getMethodNames()
	{
		return dal.getMethodNames();
	}
	public BookBorrow[] getObjectByID(int rdID) {
		try
		{
			return dal.getObjectByID(rdID);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
		
	
}
