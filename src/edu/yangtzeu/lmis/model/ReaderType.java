package edu.yangtzeu.lmis.model;

public class ReaderType extends AbstractModel{
	private int rdType;
	private String rdTypeName;
	private int canLendQty;
	private int canLendDay;
	private int canContinueTimes;
	private float punishRate;
	private int dateValid;
	
	public ReaderType(){}
	
	public int getRdType(){return rdType;}
	
	public void setRdType(int rdType){this.rdType = rdType;}
	
	public String getRdTypeName(){return rdTypeName;}
	
	public void setRdTypeName(String rdTypeName){this.rdTypeName = rdTypeName;}
	
	public int getCanLendQty(){return canLendQty;}
	
	public void setCanLendQty(int canLendQty){ this.canLendQty = canLendQty;}
	
	public int getCanLendDay(){return canLendDay;}
	
	public void setCanLendDay(int canLendDay){ this.canLendDay = canLendDay;}
	
	public int getCanContinueTimes(){return canContinueTimes;}
	
	public void setCanContinueTimes(int canContinueTimes){ this.canContinueTimes = canContinueTimes;}
	
	public float getPunishRate(){return punishRate;}
	
	public void setPunishRate(float punishRate){this.punishRate = punishRate;}
	
	public int getDateValid(){return dateValid;}
	
	public void setDateValid(int dateValid){this.dateValid = dateValid;}
	
	public ReaderType(ReaderType rt)
	{
		setRdType(rt.getRdType());
		setRdTypeName(rt.getRdTypeName());
		setCanLendQty(rt.getCanLendQty());
		setCanLendDay(rt.getCanLendDay());
		setCanContinueTimes(rt.getCanContinueTimes());
		setPunishRate(rt.getPunishRate());
		setDateValid(rt.getDateValid());
	}

	public String setRdTypeNames(String rdTypeName) {
		// TODO Auto-generated method stub
		return this.rdTypeName = rdTypeName;
	}

	@Override
	public String toString() {
		return this.getRdTypeName();
	}
	
	
	
	}
