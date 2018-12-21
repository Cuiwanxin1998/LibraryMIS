package edu.yangtzeu.lmis.model;

public class DepartmentType extends AbstractModel{
	private int rdDeptType;
	private String rdDept;
	
	public DepartmentType(){}
	
	public int getRdDeptType(){return rdDeptType;}
	
	public void setRdDeptType(int rdDeptType){this.rdDeptType = rdDeptType;}
	
	public String getRdDept(){return rdDept;}
	
	public void setRdDept(String rdDept){this.rdDept = rdDept;}
	
	public String setRdDepts(String rdDept){return this.rdDept = rdDept;}
	
	public DepartmentType (DepartmentType dt)
	{
		setRdDept(dt.getRdDept());
		setRdDeptType(dt.getRdDeptType());
	}
	
	@Override
	public String toString() {
		return this.getRdDept();
	}
}
