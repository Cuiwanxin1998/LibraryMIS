package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BookDAL;
import edu.yangtzeu.lmis.model.Book;

public class BookAdmin {
	private BookDAL dal = new BookDAL();
	
	public String[] getDisplayColumnNames()
	{
		return dal.getPrettyColumnNames();
	}
	public String[] getMethodNames()
	{
		return dal.getMethodNames();
	}
	public Book[] getBkID(String findText) throws Exception
	{
		return (Book[]) dal.selectBkId(findText);
	}
	public Book[] getselectName(String findText) throws Exception
	{
		return (Book[]) dal.selectBkName(findText);
	}
	public Book[] getselectAuthor(String findText) throws Exception 
	{
		return (Book[]) dal.selectBkAuthor(findText);
	}
	public Book[] getselectPress(String findText) throws SQLException
	{
		return (Book[]) dal.selectBkPress(findText);
	}
	public Book[] getselectCateLog(String findText) throws SQLException 
	{
		return (Book[]) dal.selectBkCateLog(findText);
	}
	public Book[] getselectPressDate(String findText) throws SQLException 
	{
		return (Book[]) dal.selectBkPressDate(findText);
	}
	public Book[] getResult(String bkName, String bkPress, String bkAuthor, String CateLog, String bkInfo,
			String pressDate) throws SQLException {
		return (Book[]) dal.selectResult(bkName,bkPress,bkAuthor,CateLog,bkInfo,pressDate);
	}
	public void deletebook(Book book) {try
	{
		dal.delete(book);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
	}
	public void updateBook(Book book) {
		try
		{
			dal.update(book);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public Book getBkNumber() throws SQLException {
		return dal.getNumber();
	}
	public int getBkQty(String bkName) throws SQLException {
		// TODO Auto-generated method stub
		return dal.getQty(bkName);
	}
	public void insertBook(Book book) {
		// TODO Auto-generated method stub
		try
		{
			dal.add(book);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void changeStatus(int bkID) {
		try
		{
			dal.changeStatus(bkID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void returnBk(int bkID) {
		try
		{
			dal.returnbk(bkID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
