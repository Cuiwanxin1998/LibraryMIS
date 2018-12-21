package edu.yangtzeu.lmis.bll;

import edu.yangtzeu.lmis.dal.AbstractDAL;
import edu.yangtzeu.lmis.dal.ReaderDAL;

public abstract class LibraryBLL {
	protected ReaderDAL dal;
	
	public void ReaderAdmin()
	{
		dal = new ReaderDAL();
	}
	public String[] getDisplayColumnNames()
	{
		return dal.getPrettyColumnNames();
	}
	public String[] getMethodNames()
	{
		return dal.getMethodNames();
	}

}
