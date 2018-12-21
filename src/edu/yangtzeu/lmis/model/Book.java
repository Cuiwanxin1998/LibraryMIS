package edu.yangtzeu.lmis.model;

import java.util.Date;

public class Book extends AbstractModel{
	private int bkID;
	private String bkCode;
	private String bkName;
	private String bkAuthor;
	private String bkPress;
	private Date bkDatePress;
	private String bkISBN;
	private String bkCatalog;
	private int bkLanguage;
	private int bkPages;
	private float bkPrice;
	private Date bkDateIn;
	private String bkBrief;
	private byte[] bkCover;
	private String bkStatus;
	
	public Book(){} 
	
	public int getBkID(){return bkID;}
	
	public void setBkID(int bkID){this.bkID = bkID;}
	
	public String getBkCode(){return bkCode;}
	
	public void setBkCode(String bkCode){this.bkCode = bkCode;}
	
	public String getBkName(){return bkName;}
	
	public void setBkName(String bkName){this.bkName = bkName;}
	
	public String getBkAuthor(){return bkAuthor;}
	
	public void setBkAuthor(String bkAuthor){this.bkAuthor = bkAuthor;}
	
	public String getBkPress(){return bkPress;}
	
	public void setBkPress(String bkPress){this.bkPress = bkPress;}
	
	public Date getBkDatePress(){return bkDatePress;}
	
	public void setBkDatePress(Date bkDatePress){this.bkDatePress = bkDatePress;}
	
	public String getBkISBN(){return bkISBN;}
	
	public void setBkISBN(String bkISBN){this.bkISBN = bkISBN;}
	
	public String getBkCatalog(){return bkCatalog;}
	
	public void setBkCatalog(String bkCatalog){this.bkCatalog = bkCatalog;}
	
	public int getBkLanguage(){return bkLanguage;}
	
	public void setBkLanguage(int bkLanguage){this.bkLanguage = bkLanguage;}
	
	public int getBkPages(){return bkPages;}
	
	public void setBkPages(int bkPages){this.bkPages = bkPages;}
	
	public float getBkPrice(){return bkPrice;}
	
	public void setBkPrice(float bkPrice){this.bkPrice = bkPrice;}
	
	public Date getBkDateIn(){return bkDateIn;}
	
	public void setBkDateIn(Date bkDateIn){this.bkDateIn = bkDateIn;}
	
	public String getBkBrief(){return bkBrief;}
	
	public void setBkBrief(String bkBrief){this.bkBrief = bkBrief;}
	
	public byte[] getBkCover(){return bkCover;}
	
	public void setBkCover(byte[] bkCover){this.bkCover = bkCover;}
	
	public String getBkStatus(){return bkStatus;}
	
	public void setBkStatus(String bkStatus){this.bkStatus = bkStatus;}
	
	public Book(Book bk)
	{
		setBkID(bk.getBkID());
		setBkCode(bk.getBkCode());
		setBkName(bk.getBkName());
		setBkAuthor(bk.getBkAuthor());
		setBkPress(bk.getBkPress());
		setBkDatePress(bk.getBkDatePress());
		setBkISBN(bk.getBkISBN());
		setBkCatalog(bk.getBkCatalog());
		setBkLanguage(bk.getBkLanguage());
		setBkPages(bk.getBkPages());
		setBkPrice(bk.getBkPrice());
		setBkDateIn(bk.getBkDateIn());
		setBkBrief(bk.getBkBrief());
		setBkCover(bk.getBkCover());
		setBkStatus(bk.getBkStatus());

	}
	
}
