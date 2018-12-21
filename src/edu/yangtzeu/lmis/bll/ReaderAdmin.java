package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderAdmin {
	private ReaderDAL dal = new ReaderDAL();
	
	
	public Reader getReader(int rdID)
	{
		try
		{
			return (Reader) dal.getObjectByID(rdID);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String[] getDisplayColumnNames()
	{
		return dal.getPrettyColumnNames();
	}
	public String[] getMethodNames()
	{
		return dal.getMethodNames();
	}
	public Reader[] retrieveReaders(ReaderType rdTypeName,DepartmentType deptType, String userName) {
		try
		{
			return dal.getReadersBy(rdTypeName,deptType,userName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void updateReader(Reader reader)
	{
		try
		{
			dal.update(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void insertReader(Reader reader) {
		try
		{
			dal.add(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void lostReader(Reader reader) {
		try
		{
			dal.lost(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void FoundReader(Reader reader) {
		try
		{
			dal.found(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public Reader[] find(String rdTypeName, String deptType, String userName) {
		try
		{
			return dal.find(rdTypeName,deptType,userName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void delete(Reader reader) {
		try
		{
			dal.delete(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void addBorrowQty(int rdID, int number) {
		try
		{
			dal.addBorrowQty(rdID,number);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void delBorrowQty(int rdID, int number) {
		try
		{
			dal.delBorrowQty(rdID,number);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}
