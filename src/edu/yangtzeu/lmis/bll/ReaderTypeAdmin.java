package edu.yangtzeu.lmis.bll;

import edu.yangtzeu.lmis.dal.ReaderTypeDAL;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderTypeAdmin {
	ReaderTypeDAL dal = new ReaderTypeDAL();
	
	public ReaderType[] getReaderTypes()
	{
		try
		{
			return (ReaderType[]) dal.getAllobjects();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
//	public String[] getrdTypeNames()
//	{
//		try
//		{
//			return (String[]) dal.getrdTypeName();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
	public String[] getDisplayColumnNames()
	{
		return dal.getPrettyColumnNames();
	}
	
	public String[] getMethodNames()
	{
		return dal.getMethodNames();
	}
	public ReaderType getObjectByID(int rdType) {
		// TODO Auto-generated method stub
		try
		{
			return (ReaderType) dal.getObjectByID(rdType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void updateReaderType(ReaderType readerType) {
		try
		{
			dal.update(readerType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void insertReaderType(ReaderType readerType) {
		try
		{
			dal.add(readerType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void deleteReaderType(ReaderType readerType) {
		try
		{
			dal.delete(readerType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void add(ReaderType readerType) {
		try
		{
			dal.add(readerType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
