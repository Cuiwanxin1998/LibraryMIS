package edu.yangtzeu.lmis.dal;
import edu.yangtzeu.lmis.model.AbstractModel;
import java.sql.SQLException;

public abstract class AbstractDAL {
	public abstract AbstractModel[] getAllobjects() throws Exception;
	public abstract int add(AbstractModel object) throws Exception;
	public abstract int delete(AbstractModel object) throws Exception;
	public abstract int update(AbstractModel object) throws Exception;
	public abstract AbstractModel getObjcetByID(int id) throws SQLException;
	public abstract String[] getPrettyColumnNames();
	public abstract String[] getMehodNames();
	

}
