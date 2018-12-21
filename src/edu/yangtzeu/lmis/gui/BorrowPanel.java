package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.bll.BookBorrowAdmin;
import edu.yangtzeu.lmis.bll.BorrowAdmin;
import edu.yangtzeu.lmis.bll.DepartmentTypeAdmin;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.BookBorrow;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BorrowPanel extends JPanel {
	private JTextField rdIDText;
	private JTextField rdNameText;
	private JTextField canLendQtyText;
	private JTextField rdDeptText;
	private JTextField canLendDayText;
	private JTextField rdTypeText;
	private JTextField borrowQtyText;
	private JTable BorrowBookTable;
	private JTable bookInfoTable;
	private JScrollPane BorrowBookJscroll;
	private JScrollPane bookInfoJscroll;
	private SimpleDateFormat df;
	private BookAdmin bookBll = new BookAdmin();
	private ReaderAdmin readerBll = new ReaderAdmin();
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private BookBorrowAdmin bookborrowBll = new BookBorrowAdmin();
	private BorrowAdmin borrowBll = new BorrowAdmin();
	private JTextField bkIdText;
	private JTextField bkNameText;
	private Date now = new Date();// new Date()为获取当前系统时间
	
	private void updateResultTable(BookBorrow[] bkbw)
	{
		CustomizedTableModel<BookBorrow> tableModel = (CustomizedTableModel<BookBorrow>) BorrowBookTable.getModel();
		tableModel.setRecords(bkbw);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	private void updateResultTable1(Book[] books)
	{
		if(books == null)
		{
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录：");
			return;
		}
		CustomizedTableModel<Book> tableModel = (CustomizedTableModel<Book>) bookInfoTable.getModel();
		tableModel.setRecords(books);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	
	private Borrow borrow(int bkID, int rdID,int CanLendQty)
	{
		Borrow borrow = new Borrow();
		borrow.setBkID(bkID);
		borrow.setRdID(rdID);
		borrow.setIdDateOut(now);
		borrow.setIdDateRetPlan(addAndSubtractDaysByGetTime(now,CanLendQty));
		return borrow;
	}
	
	 public static Date addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/,int n/*加减天数*/)
	 { //日期格式 
		 SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值 
		 return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L); 
	 }
		
	public BorrowPanel() {
		setLayout(null);
		
		JPanel rdInfoPanel = new JPanel();
		rdInfoPanel.setBounds(32, 30, 924, 92);
		add(rdInfoPanel);
		rdInfoPanel.setLayout(null);
		
		JLabel readerInfoLabel = new JLabel("\u8BFB\u8005\u4FE1\u606F:");
		readerInfoLabel.setBounds(0, 0, 72, 18);
		rdInfoPanel.add(readerInfoLabel);
		
		JLabel rdIDLabel = new JLabel("\u8BFB\u8005\u7F16\u53F7:");
		rdIDLabel.setBounds(10, 31, 72, 18);
		rdInfoPanel.add(rdIDLabel);
		
		rdIDText = new JTextField();
		rdIDText.setBounds(81, 28, 100, 24);
		rdInfoPanel.add(rdIDText);
		rdIDText.setColumns(10);
		
		JButton findReaderBtn = new JButton("\u67E5\u627E");
		findReaderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdIDText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "读者编号为空!");
					return;
				}
				int rdID = Integer.valueOf(rdIDText.getText());
				Reader reader = readerBll.getReader(rdID);
				rdNameText.setText(reader.getRdName());
				ReaderType readerType = readerTypeBll.getObjectByID(reader.getRdType());
				rdDeptText.setText(reader.getRdDept());
				canLendQtyText.setText(String.valueOf(readerType.getCanLendQty()));
				canLendDayText.setText(String.valueOf(readerType.getCanLendDay()));
				rdTypeText.setText(String.valueOf(readerType.getRdTypeName()));
				borrowQtyText.setText(String.valueOf(reader.getRdBorrowQty()));	
			
				//查询的时候，将过期日期和赔偿金更新进borrow表
				borrowBll.updatePlan(rdID);
				df = new SimpleDateFormat("YYYY-MM-dd");	
				BookBorrow[] hits = bookborrowBll.getObjectByID(rdID);			
				updateResultTable(hits);
				
			}
		});
		findReaderBtn.setBounds(185, 27, 63, 27);
		rdInfoPanel.add(findReaderBtn);
		
		JLabel rdNameLabel = new JLabel("\u8BFB\u8005\u59D3\u540D:");
		rdNameLabel.setBounds(327, 13, 72, 18);
		rdInfoPanel.add(rdNameLabel);
		
		JLabel canBorrowLabel = new JLabel("\u53EF\u501F\u4E66\u6570\u91CF:");
		canBorrowLabel.setBounds(313, 44, 83, 18);
		rdInfoPanel.add(canBorrowLabel);
		
		rdNameText = new JTextField();
		rdNameText.setEditable(false);
		rdNameText.setBounds(407, 10, 86, 24);
		rdInfoPanel.add(rdNameText);
		rdNameText.setColumns(10);
		
		canLendQtyText = new JTextField();
		canLendQtyText.setEditable(false);
		canLendQtyText.setBounds(407, 41, 86, 24);
		rdInfoPanel.add(canLendQtyText);
		canLendQtyText.setColumns(10);
		
		JLabel rdDeptLabel = new JLabel("\u8BFB\u8005\u5355\u4F4D:");
		rdDeptLabel.setBounds(532, 13, 72, 18);
		rdInfoPanel.add(rdDeptLabel);
		
		rdDeptText = new JTextField();
		rdDeptText.setEditable(false);
		rdDeptText.setBounds(612, 10, 86, 24);
		rdInfoPanel.add(rdDeptText);
		rdDeptText.setColumns(10);
		
		JLabel canBorrowQtyLabel = new JLabel("\u53EF\u501F\u4E66\u5929\u6570:");
		canBorrowQtyLabel.setBounds(517, 44, 83, 18);
		rdInfoPanel.add(canBorrowQtyLabel);
		
		canLendDayText = new JTextField();
		canLendDayText.setEditable(false);
		canLendDayText.setBounds(612, 41, 86, 24);
		rdInfoPanel.add(canLendDayText);
		canLendDayText.setColumns(10);
		
		JLabel rdTypeLabel = new JLabel("\u8BFB\u8005\u7C7B\u578B:");
		rdTypeLabel.setBounds(722, 13, 72, 18);
		rdInfoPanel.add(rdTypeLabel);
		
		rdTypeText = new JTextField();
		rdTypeText.setEditable(false);
		rdTypeText.setBounds(795, 10, 86, 24);
		rdInfoPanel.add(rdTypeText);
		rdTypeText.setColumns(10);
		
		JLabel borrowQtyLabel = new JLabel("\u5DF2\u501F\u6570\u91CF:");
		borrowQtyLabel.setBounds(722, 47, 72, 18);
		rdInfoPanel.add(borrowQtyLabel);
		
		borrowQtyText = new JTextField();
		borrowQtyText.setEditable(false);
		borrowQtyText.setBounds(795, 44, 86, 24);
		rdInfoPanel.add(borrowQtyText);
		borrowQtyText.setColumns(10);
		
		JPanel borrowBookPanel = new JPanel();
		borrowBookPanel.setBounds(32, 135, 924, 229);
		add(borrowBookPanel);
		borrowBookPanel.setLayout(null);
		
		JLabel label = new JLabel("\u5DF2\u501F\u56FE\u4E66:");
		label.setBounds(0, 0, 72, 18);
		borrowBookPanel.add(label);
		
		CustomizedTableModel<BookBorrow> tableModel = new CustomizedTableModel<BookBorrow>(
				bookborrowBll.getDisplayColumnNames(),bookborrowBll.getMethodNames());
		
		BorrowBookTable = new JTable(tableModel);
		BorrowBookJscroll = new JScrollPane(BorrowBookTable);
		BorrowBookJscroll.setBounds(0, 24, 924, 174);
		BorrowBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		borrowBookPanel.add(BorrowBookJscroll);
		
		JButton ContinueLendBtn = new JButton("\u7EED\u501F");
		ContinueLendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = BorrowBookTable.getSelectedRow();
				int rdID = Integer.valueOf(rdIDText.getText().trim());
				Reader reader = readerBll.getReader(rdID);
				ReaderType readerType = readerTypeBll.getObjectByID(reader.getRdType());
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				if(rdIDText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "读者编号为空!");
					return;
				}
				
				if((((CustomizedTableModel<BookBorrow>) BorrowBookTable.getModel()).getObjectAt(selectedRow)).getIdContinueTimes()<readerType.getCanContinueTimes())
				{
					int bkID = (((CustomizedTableModel<BookBorrow>) BorrowBookTable.getModel()).getObjectAt(selectedRow)).getBkID();
					int canLend = (((CustomizedTableModel<BookBorrow>) BorrowBookTable.getModel()).getObjectAt(selectedRow)).getIdContinueTimes();
					int canLendDay = Integer.valueOf(canLendDayText.getText().trim());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date future = addAndSubtractDaysByGetTime(now,canLendDay);					
					borrowBll.Lend(bkID,canLend,rdID,sf.format(now),sf.format(future));
					float money = 0;
					try {
						Borrow borrow = borrowBll.Money(bkID,rdID);
						money = borrow.getIdOverMoney();
						borrowBll.continueupdate(rdID,bkID);
						BookBorrow[] hits1 = bookborrowBll.getObjectByID(rdID);
						updateResultTable(hits1);
						JOptionPane.showMessageDialog(null, "缴纳"+money+"元，续借成功！");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "续借超过次数，续借失败!");
					return;
				}			
			}
		});
		ContinueLendBtn.setBounds(247, 202, 72, 27);
		borrowBookPanel.add(ContinueLendBtn);
		
		JButton returnbkBtn = new JButton("\u8FD8\u4E66");
		returnbkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = BorrowBookTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				if(rdIDText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "读者编号为空!");
					return;
				}
				int bkID = (((CustomizedTableModel<BookBorrow>) BorrowBookTable.getModel()).getObjectAt(selectedRow)).getBkID();
				int rdID = Integer.valueOf(rdIDText.getText().trim());
				float money = 0;
				Reader reader = readerBll.getReader(rdID);
				Borrow borrow;
				try {
					borrow = borrowBll.Money(bkID,rdID);
					money = borrow.getIdOverMoney();
				} catch (SQLException e2) {
					
					e2.printStackTrace();
				}
				bookBll.returnBk(bkID);
				borrowBll.del(bkID,rdID);
				readerBll.delBorrowQty(rdID,reader.getRdBorrowQty());
				BookBorrow[] hits1 = bookborrowBll.getObjectByID(rdID);
				updateResultTable(hits1);
				String bkName = bkNameText.getText().trim();		
				try {
					Book[] hits = bookBll.getselectName(bkName);
					updateResultTable1(hits);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				borrowQtyText.setText(String.valueOf(reader.getRdBorrowQty()-1));
				JOptionPane.showMessageDialog(null, "缴纳"+money+"元,还书成功!");
			}
		});
		returnbkBtn.setBounds(322, 202, 72, 27);
		borrowBookPanel.add(returnbkBtn);
		JPanel BookInfoPanel = new JPanel();
		BookInfoPanel.setBounds(32, 387, 924, 229);
		add(BookInfoPanel);
		BookInfoPanel.setLayout(null);
		
		JLabel bookInfoLabel = new JLabel("\u56FE\u4E66\u4FE1\u606F:");
		bookInfoLabel.setBounds(0, 0, 72, 18);
		BookInfoPanel.add(bookInfoLabel);
		
		//定义table
		CustomizedTableModel<Book> tableModel1 = new CustomizedTableModel<Book>(
				bookBll.getDisplayColumnNames(),bookBll.getMethodNames());
		bookInfoTable = new JTable(tableModel1);
		bookInfoJscroll = new JScrollPane(bookInfoTable);
		bookInfoJscroll.setBounds(0, 57, 924, 172);
		bookInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BookInfoPanel.add(bookInfoJscroll);
		
		JLabel bookIdLabel = new JLabel("\u56FE\u4E66\u5E8F\u53F7:");
		bookIdLabel.setBounds(0, 26, 72, 18);
		BookInfoPanel.add(bookIdLabel);
		
		bkIdText = new JTextField();
		bkIdText.setBounds(75, 23, 86, 24);
		BookInfoPanel.add(bkIdText);
		bkIdText.setColumns(10);
		
		JButton findBtn = new JButton("\u67E5\u8BE2");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bkID = bkIdText.getText().trim();
				try {
					Book[] hits = bookBll.getBkID(bkID);
					updateResultTable1(hits);
					bkIdText.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		findBtn.setBounds(162, 22, 72, 27);
		BookInfoPanel.add(findBtn);
		
		JLabel bkNameLabel = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		bkNameLabel.setBounds(248, 26, 72, 18);
		BookInfoPanel.add(bkNameLabel);
		
		bkNameText = new JTextField();
		bkNameText.setColumns(10);
		bkNameText.setBounds(314, 23, 86, 24);
		BookInfoPanel.add(bkNameText);
		
		JButton findbtn = new JButton("\u67E5\u8BE2");
		findbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bkName = bkNameText.getText().trim();
				try {
					Book[] hits = bookBll.getselectName(bkName);
					updateResultTable1(hits);
					bkNameText.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		findbtn.setBounds(403, 22, 72, 27);
		BookInfoPanel.add(findbtn);
		
		JButton borrowBtn = new JButton("\u501F\u9605");
		borrowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookInfoTable.getSelectedRow();	
				int rdID = Integer.valueOf(rdIDText.getText().trim());
				Reader reader = readerBll.getReader(rdID);
				ReaderType readerType = readerTypeBll.getObjectByID(reader.getRdType());
				
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				Book book = (((CustomizedTableModel<Book>) bookInfoTable.getModel()).getObjectAt(selectedRow));
				if(rdIDText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "读者编号为空!");
					return;
				}
				
				if(reader.getRdStatus().equals("挂失"))
				{
					JOptionPane.showMessageDialog(null, "读者借书证挂失，无法借书!");
					return;
				}
				try {
					if(borrowBll.OverDay(rdID))
					{
						JOptionPane.showMessageDialog(null, "读者有过期未还图书!无法借书!");
						return;
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(book.getBkStatus().equals("借出"))
				{
					JOptionPane.showMessageDialog(null, "该书已被借出!");
					return;
				}
							
				if(readerType.getCanLendQty()<=Integer.valueOf(borrowQtyText.getText().trim()))
				{
					JOptionPane.showMessageDialog(null, "超过可借书数量!借书失败!");
					return;
				}
				int bkID = (((CustomizedTableModel<Book>) bookInfoTable.getModel()).getObjectAt(selectedRow)).getBkID();
				int canLendDay = Integer.valueOf(canLendDayText.getText().trim());
				bookBll.changeStatus(bkID);
				String bkName = bkNameText.getText().trim();
				try {		
					Book[] hits = bookBll.getselectName(bkName);
					borrowBll.add(borrow(bkID,rdID,canLendDay));
					readerBll.addBorrowQty(rdID,reader.getRdBorrowQty());
					updateResultTable1(hits);
					BookBorrow[] hits1 = bookborrowBll.getObjectByID(rdID);
					updateResultTable(hits1);
					JOptionPane.showMessageDialog(null, "借阅成功!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				borrowQtyText.setText(String.valueOf(reader.getRdBorrowQty()+1));
				
				
			}
		});
		borrowBtn.setBounds(388, 659, 113, 27);
		add(borrowBtn);
		
		JButton returnBtn = new JButton("\u8FD4\u56DE");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		returnBtn.setBounds(501, 659, 113, 27);
		add(returnBtn);
	}
}
