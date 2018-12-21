package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Book;

public class BookDAL {
	private String[] dispColNames = new String[] {"ID","索书号","书名","作者","出版社","出版日期","ISBN","分类号","语种","页数","价格","入馆时间","状态"};
	
	private String[] methodNames = new String[]	{"getBkID","getBkCode","getBkName","getBkAuthor","getBkPress",
			"getBkDatePress","getBkISBN","getBkCatalog","getBkLanguage","getBkPages","getBkPrice","getBkDateIn","getBkStatus"};
	
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
		if(object instanceof Book == false)
		{
			throw new Exception("Can only handle Book");
		}
		Book bk = (Book) object;
		String sql = "insert into TB_Book(bkID,"
				+"bkCode,bkName,bkAuthor,bkPress,"
				+"bkDatePress,bkISBN,bkCatalog,bkLanguage,"
				+"bkPages,bkPrice,bkDateIn,bkBrief,"
				+"bkCover,bkStatus)"
				+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[15];
		params[0] = bk.getBkID();
		params[1] = bk.getBkCode();
		params[2] = bk.getBkName();
		params[3] = bk.getBkAuthor();
		params[4] = bk.getBkPress();
		params[5] = bk.getBkDatePress();
		params[6] = bk.getBkISBN();
		params[7] = bk.getBkCatalog();
		params[8] = bk.getBkLanguage();
		params[9] = bk.getBkPages();
		params[10] = bk.getBkPrice();
		params[11] = bk.getBkDateIn();
		params[12] = bk.getBkBrief();
		params[13] = bk.getBkCover();
		params[14] = bk.getBkStatus();
		
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int delete(AbstractModel object) throws Exception
	{
		if(object instanceof Book == false)
		{
			throw new Exception("Can only handle Book");
		}
		Book bk = (Book) object;
		String sql = "delete from TB_Book where bkID = ?";
		Object[] params = new Object[]{bk.getBkID()};
		int rows = SQLHelper.ExecSql(sql,params);
		return rows;
	}
	
	public int update(AbstractModel object) throws Exception
	{
		if(object instanceof Book == false)
		{
			throw new Exception("Can only handle Book");
		}
		Book bk = (Book) object;
		String sql = "update TB_Book set "
				+"bkCode = ?,bkName = ?,bkAuthor = ?,bkPress = ?,"
				+"bkDatePress = ?,bkISBN = ?,bkCatalog = ?,bkLanguage = ?,"
				+"bkPages = ?,bkPrice = ?,bkDateIn = ?,bkBrief = ?,"
				+"bkCover = ? where bkID = ?";
		Object[] params = new Object[]{bk.getBkCode(),
				bk.getBkName(),bk.getBkAuthor(),bk.getBkPress(),bk.getBkDatePress(),
				bk.getBkISBN(),bk.getBkCatalog(),bk.getBkLanguage(),bk.getBkPages(),
				bk.getBkPrice(),bk.getBkDateIn(),bk.getBkBrief(),bk.getBkCover(),bk.getBkID()};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public Book getObjectByID(int bkID) throws SQLException
	{
		Book bk = null;
		ResultSet rs = SQLHelper
				.getResultSet("select bkID,bkCode,bkName,bkAuthor,bkPress,"
						+"bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,"
						+"bkPrice,bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkID="+bkID);
		if(rs != null)
		{
			if(rs.next())
			{
				bk = initBook(rs);
			}
			rs.close();
		}
		return bk;	
	}
	private Book initBook(ResultSet rs) throws SQLException
	{

		Book bk = new Book();
		bk.setBkID(rs.getInt("bkID"));
		bk.setBkCode(rs.getString("bkCode"));
		bk.setBkName(rs.getString("bkName"));
		bk.setBkAuthor(rs.getString("bkAuthor"));
		bk.setBkPress(rs.getString("bkPress"));
		bk.setBkDatePress(rs.getDate("bkDatePress"));
		bk.setBkISBN(rs.getString("bkISBN"));
		bk.setBkCatalog(rs.getString("bkCatalog"));
		bk.setBkLanguage(rs.getInt("bkLanguage"));
		bk.setBkPages(rs.getInt("bkPages"));
		bk.setBkPrice(rs.getLong("bkPrice"));
		bk.setBkDateIn(rs.getDate("bkDateIn"));
		bk.setBkBrief(rs.getString("bkBrief"));
		bk.setBkCover(rs.getBytes("bkCover"));
		bk.setBkStatus(rs.getString("bkStatus"));
		
		
		return bk;
		
	}
	
	public AbstractModel[] getAllobjects() throws Exception
	{
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book");
		if(rs != null)
		{
			while(rs.next())
			{
				Book bk = initBook(rs);
				objects.add(bk);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
		
	}
	
	public Book[] selectBkId(String bkId) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkId like "+"'%"+bkId +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}
	public Book[] selectBkAuthor(String findText) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkAuthor like "+"'%"+findText +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}
	public Book[] selectBkName(String findText) throws Exception
	{
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkName like "+"'%"+findText +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	public Book[] selectBkPress(String findText) throws SQLException 
	{
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkPress like "+"'%"+findText +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	public Book[] selectBkCateLog(String findText) throws SQLException
	{
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkCataLog like "+"'%"+findText +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	public Book[] selectBkPressDate(String findText) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkDatePress like "+"'%"+findText +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	public Book[] selectResult(String bkName, String bkPress, String bkAuthor, String CataLog, String bkInfo,
			String pressDate) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Book where bkName like '"+"%"+bkName +"%'"
				+"and bkPress like '%"+bkPress+"%'"+"and bkAuthor like '%"+bkAuthor+"%'"+"and bkCataLog like '%"+CataLog+"%'"+"and bkBrief like '%"+bkInfo +"%'"
				+"and bkDatePress like '%" + pressDate +"%'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		if(books.size() > 0)
		{
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	public Book getNumber() throws SQLException {
		Book book = null;
		ResultSet rs = SQLHelper.getResultSet("SELECT * FROM tb_book ORDER BY bkID  DESC LIMIT 1");
		if(rs != null)
		{
			while(rs.next())
			{
				book = initBook(rs);

			}
			rs.close();
		}
		return book;
	}



	public int getQty(String bkName) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select * from tb_Book where bkName = '"+ bkName +"'");
		if(rs != null)
		{
			while(rs.next())
			{
				Book book = initBook(rs);
				books.add(book);
			}
			rs.close();
		}
		int number = books.size();
		return number;
	}

	public void changeStatus(int bkID) {
		SQLHelper.ExecSql("update TB_Book set bkStatus = '借出' where bkID = "+"'"+bkID +"'");
	}

	public void returnbk(int bkID) {
		SQLHelper.ExecSql("update TB_Book set bkStatus = '在馆' where bkID = "+"'"+bkID +"'");
		
	}

}
