package edu.yangtzeu.lmis.model;

import java.util.Date;

public class Borrow extends AbstractModel{
	private int BorrowID;
	private int rdID;
	private int bkID;
	private int IdContinueTimes;
	private Date IdDateOut;
	private Date IdDateRetPlan;
	private Date IdOverRetAct;
	private int IdOverDay;
	private float IdOverMoney;
	private float IdPunishMoney;
	private boolean IsHasReturn;
	private String OperatorLend;
	private String OperatorRet;
	
	public Borrow(){}
	
	public int getBorrowID(){return BorrowID;}
	
	public void setBorrowID(int BorrowID){this.BorrowID = BorrowID;}
	
	public int getRdID(){return rdID;}
	
	public void setRdID(int rdID){this.rdID = rdID;}
	
	public int getBkID(){return bkID;}
	
	public void setBkID(int bkID){this.bkID = bkID;}
	
	public int getIdContinueTimes(){return IdContinueTimes;}
	
	public void setIdContinueTimes(int IdContinueTimes){this.IdContinueTimes = IdContinueTimes;}
	
	public Date getIdDateOut(){return IdDateOut;}
	
	public void setIdDateOut(Date IdDateOut){this.IdDateOut = IdDateOut;}
	
	public Date getIdOverRetAct(){return IdOverRetAct;}
	
	public void setIdOverRetAct(Date IdOverRetAct){this.IdOverRetAct = IdOverRetAct;}
	
	public Date getIdDateRetPlan(){return IdDateRetPlan;}
	
	public void setIdDateRetPlan(Date IdDateRetPlan){this.IdDateRetPlan = IdDateRetPlan;}
	
	public int getIdOverDay(){return IdOverDay;}
	
	public void setIdOverDay(int IdOverDay){this.IdOverDay = IdOverDay;}
	
	public float getIdOverMoney(){return IdOverMoney;}
	
	public void setIdOverMoney(float IdOverMoney){this.IdOverMoney = IdOverMoney;}
	
	public float getIdPunishMoney(){return IdPunishMoney;}
	
	public void setIdPunishMoney(float IdPunishMoney){this.IdPunishMoney = IdPunishMoney;}
	
	public boolean getIsHasReturn(){return IsHasReturn;}
	
	public void setIsHasReturn(boolean IsHasReturn){this.IsHasReturn = IsHasReturn;}
	
	public String getOperatorLend(){return OperatorLend;}
	
	public void setOperatorLend(String OperatorLend){this.OperatorLend = OperatorLend;}
	
	public String getOperatorRet(){return OperatorRet;}
	
	public void setOperatorRet(String OperatorRet){this.OperatorRet = OperatorRet;}
	
	public Borrow(Borrow bw)
	{
		setBorrowID(bw.getBorrowID());
		setRdID(bw.getRdID());
		setBkID(bw.getBkID());
		setIdContinueTimes(bw.getIdContinueTimes());
		setIdDateOut(bw.getIdDateOut());
		setIdDateRetPlan(bw.getIdDateRetPlan());
		setIdOverRetAct(bw.getIdOverRetAct());
		setIdOverDay(bw.getIdOverDay());
		setIdPunishMoney(bw.getIdPunishMoney());
		setIsHasReturn(bw.getIsHasReturn());
		setOperatorLend(bw.getOperatorLend());
		setOperatorRet(bw.getOperatorRet());
	}
	
}
