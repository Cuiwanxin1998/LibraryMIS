package edu.yangtzeu.lmis.model;

import java.util.Date;

public class BookBorrow extends AbstractModel{
	private int rdID;
	private int bkID;
	private String bkName;
	private String bkAuthor;
	private int IdContinueTimes;
	private Date IdDateOut;
	private Date IdDateRetPlan;
	private int IdOverDay;
	private float IdOverMoney;
	
	private Date now = new Date();
	
	public BookBorrow(){}
	
	public int getRdID(){return rdID;}
	
	public void setRdID(int rdID){this.rdID = rdID;}
	
	public int getBkID(){return bkID;}
	
	public void setBkID(int bkID){this.bkID = bkID;}
	
	public String getBkName(){return bkName;}
	
	public void setBkName(String bkName){this.bkName = bkName;}
	
	public String getBkAuthor(){return bkAuthor;}
	
	public void setBkAuthor(String bkAuthor){this.bkAuthor = bkAuthor;}
	
	public int getIdContinueTimes(){return IdContinueTimes;}
	
	public void setIdContinueTimes(int IdContinueTimes){this.IdContinueTimes = IdContinueTimes;}
	
	public Date getIdDateOut(){return IdDateOut;}
	
	public void setIdDateOut(Date IdDateOut){this.IdDateOut = IdDateOut;}
	
	public Date getIdDateRetPlan(){return IdDateRetPlan;}
	
	public void setIdDateRetPlan(Date IdOverRetPlan){this.IdDateRetPlan = IdOverRetPlan;}
	
	public int getIdOverDay()
	{
		return IdOverDay;
	}
	
	public void setIdOverDay(int IdOverDay){this.IdOverDay = IdOverDay;}
	
	public float getIdOverMoney(){return IdOverMoney;}
	
	public void setIdOverMoney(float IdOverMoney){this.IdOverMoney = IdOverMoney;}
	
	public BookBorrow(BookBorrow bkbw)
	{
		setRdID(bkbw.getRdID());
		setBkID(bkbw.getBkID());
		setBkName(bkbw.getBkName());
		setBkAuthor(bkbw.getBkAuthor());
		setIdContinueTimes(bkbw.getIdContinueTimes());
		setIdDateOut(bkbw.getIdDateOut());
		setIdDateRetPlan(bkbw.getIdDateRetPlan());
		setIdOverDay(bkbw.getIdOverDay());
		setIdOverMoney(bkbw.getIdOverMoney());
		
	}
	
	
}
