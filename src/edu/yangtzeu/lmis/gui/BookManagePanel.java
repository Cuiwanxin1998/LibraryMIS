package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.model.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;
import edu.yangtzeu.lmis.model.Book;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Canvas;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class BookManagePanel extends JPanel {
	private JTextField FindText;
	private JTextField BookNameText;
	private JTextField BookPressText;
	private JTextField BookAuthorText;
	private JTextField CataLogText;
	private JTextField BookInfoText;
	private JTextField PressDateText;
	private JComboBox FindBox;
	private JTable SearchTable;
	private JScrollPane searchResultscroll;
	private BookAdmin bookBll = new BookAdmin();
	
	private void updateResultTable(Book[] books)
	{
		if(books == null)
		{
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录：");
			return;
		}
		CustomizedTableModel<Book> tableModel = (CustomizedTableModel<Book>) SearchTable.getModel();
		tableModel.setRecords(books);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	public BookManagePanel() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(95, 84, 838, 143);
		add(tabbedPane);
		JPanel simplyfind = new JPanel();
		JPanel complexfind = new JPanel();
		tabbedPane.add(simplyfind,"简单查询");
		simplyfind.setLayout(null);
		
		JLabel FindLabel = new JLabel("\u68C0\u7D22\u5B57\u6BB5:");
		FindLabel.setBounds(14, 51, 72, 18);
		simplyfind.add(FindLabel);
		
		FindBox = new JComboBox();
		FindBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E66\u540D", "\u51FA\u7248\u793E", "\u4F5C\u8005", "\u5206\u7C7B\u53F7", "\u51FA\u7248\u5E74"}));
		FindBox.setBounds(89, 48, 176, 24);
		simplyfind.add(FindBox);
		
		FindText = new JTextField();
		FindText.setBounds(298, 48, 213, 24);
		simplyfind.add(FindText);
		FindText.setColumns(10);
		
		JButton FindBtn = new JButton("\u67E5\u8BE2");
		FindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectItem = FindBox.getSelectedItem();
				String findText = FindText.getText().trim();
				if(selectItem == "书名")
				{
					try {
						Book[] hits = bookBll.getselectName(findText);
						updateResultTable(hits);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(selectItem == "作者")
				{
					try {
						Book[] hits = bookBll.getselectAuthor(findText);
						updateResultTable(hits);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(selectItem == "出版社")
				{
					try {
						Book[] hits = bookBll.getselectPress(findText);
						updateResultTable(hits);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(selectItem == "分类号")
				{
					try {
						Book[] hits = bookBll.getselectCateLog(findText);
						updateResultTable(hits);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else 
				{
					try {
						Book[] hits = bookBll.getselectPressDate(findText);
						updateResultTable(hits);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}		
				FindText.setText("");
			}
		});
		FindBtn.setBounds(523, 47, 113, 27);
		simplyfind.add(FindBtn);
		tabbedPane.add(complexfind,"高级查询");
		complexfind.setLayout(null);
		
		JLabel BookNameLabel = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		BookNameLabel.setBounds(14, 13, 72, 18);
		complexfind.add(BookNameLabel);
		
		JLabel BookPressLabel = new JLabel("\u51FA\u7248\u793E\u540D:");
		BookPressLabel.setBounds(14, 66, 72, 18);
		complexfind.add(BookPressLabel);
		
		BookNameText = new JTextField();
		BookNameText.setText("");
		BookNameText.setBounds(91, 10, 98, 24);
		complexfind.add(BookNameText);
		BookNameText.setColumns(10);
		
		BookPressText = new JTextField();
		BookPressText.setText("");
		BookPressText.setColumns(10);
		BookPressText.setBounds(91, 63, 98, 24);
		complexfind.add(BookPressText);
		
		JLabel BookAuthorLabel = new JLabel("\u56FE\u4E66\u4F5C\u8005:");
		BookAuthorLabel.setBounds(243, 13, 72, 18);
		complexfind.add(BookAuthorLabel);
		
		BookAuthorText = new JTextField();
		BookAuthorText.setBounds(327, 10, 98, 24);
		complexfind.add(BookAuthorText);
		BookAuthorText.setColumns(10);
		
		JLabel CataLogLabel = new JLabel("\u5206\u7C7B\u53F7:");
		CataLogLabel.setBounds(243, 66, 72, 18);
		complexfind.add(CataLogLabel);
		
		CataLogText = new JTextField();
		CataLogText.setColumns(10);
		CataLogText.setBounds(327, 63, 98, 24);
		complexfind.add(CataLogText);
		
		JLabel BookInfoLabel = new JLabel("\u56FE\u4E66\u63CF\u8FF0:");
		BookInfoLabel.setBounds(481, 13, 72, 18);
		complexfind.add(BookInfoLabel);
		
		BookInfoText = new JTextField();
		BookInfoText.setColumns(10);
		BookInfoText.setBounds(556, 10, 98, 24);
		complexfind.add(BookInfoText);
		
		JLabel PressDateLabel = new JLabel("\u51FA\u7248\u5E74:");
		PressDateLabel.setBounds(481, 66, 72, 18);
		complexfind.add(PressDateLabel);
		
		PressDateText = new JTextField();
		PressDateText.setColumns(10);
		PressDateText.setBounds(556, 63, 98, 24);
		complexfind.add(PressDateText);
		
		JButton ComplexFindBtn = new JButton("\u67E5\u8BE2");
		ComplexFindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bkName = BookNameText.getText().trim();
				String bkPress = BookPressText.getText().trim();
				String bkAuthor = BookAuthorText.getText().trim();
				String CataLog = CataLogText.getText().trim();
				String bkInfo = BookInfoText.getText().trim();
				String PressDate = PressDateText.getText().trim();
				try {
					Book[] hits = bookBll.getResult(bkName,bkPress,bkAuthor,CataLog,bkInfo,PressDate);
					updateResultTable(hits);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BookNameText.setText("");
				BookPressText.setText("");
				BookAuthorText.setText("");
				CataLogText.setText("");
				BookInfoText.setText("");
				PressDateText.setText("");
				
			}
		});
		ComplexFindBtn.setBounds(706, 39, 113, 27);
		complexfind.add(ComplexFindBtn);
		
		JPanel ResultPanel = new JPanel();
		ResultPanel.setBounds(49, 287, 900, 340);
		add(ResultPanel);
		ResultPanel.setLayout(null);
		
		//定义Table
		CustomizedTableModel<Book> tableModel = new CustomizedTableModel<Book>(
				bookBll.getDisplayColumnNames(),bookBll.getMethodNames());
		SearchTable = new JTable(tableModel);
		SearchTable.setBounds(160, 130, 1, 1);
		searchResultscroll = new JScrollPane(SearchTable);
		searchResultscroll.setBounds(0, 0, 900, 340);
		ResultPanel.add(searchResultscroll);
		SearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(349, 640, 388, 54);
		add(selectPanel);
		selectPanel.setLayout(null);
		
		JButton changeBtn = new JButton("\u4FEE\u6539");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = SearchTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					Book book = ((CustomizedTableModel<Book>) SearchTable.getModel()).getObjectAt(selectedRow);
					int n = bookBll.getBkQty(book.getBkName());
					loadBookManageGUI_tochange(book,n);
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}			
			}
		});
		changeBtn.setBounds(14, 13, 72, 27);
		selectPanel.add(changeBtn);
		
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = SearchTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					Book book = ((CustomizedTableModel<Book>) SearchTable.getModel()).getObjectAt(selectedRow);
					bookBll.deletebook(book);
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "删除成功!");
				BookNameText.setText("");
				BookPressText.setText("");
				BookAuthorText.setText("");
				CataLogText.setText("");
				BookInfoText.setText("");
				PressDateText.setText("");
			}
			
		});
		deleteBtn.setBounds(88, 13, 79, 27);
		selectPanel.add(deleteBtn);
		
		JButton addBtn = new JButton("\u65B0\u4E66\u5165\u5E93");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = bookBll.getBkNumber().getBkID() + 1;
					
					loadBookManageGUI_toadd(i);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addBtn.setBounds(265, 13, 113, 27);
		selectPanel.add(addBtn);
		
		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(172, 13, 79, 27);
		selectPanel.add(button);
	}
	protected void loadBookManageGUI_tochange(Book book,int bkQty) throws SQLException 
	{
		
		BookManage BkManage = new BookManage();
		BkManage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BkManage.setLocationRelativeTo(null);
		BkManage.setVisible(true);
		BkManage.setReaderToText(book);
		BkManage.addBtn.setEnabled(false);
		BkManage.bkNumberText.setEnabled(false);
		BkManage.bkIDText.setEnabled(false);
		BkManage.bkNumberText.setText(String.valueOf(bkQty));
		
	}
	protected void loadBookManageGUI_toadd(int bkID) throws SQLException 
	{
		
		BookManage BkManage = new BookManage();
		BkManage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BkManage.setLocationRelativeTo(null);
		BkManage.setVisible(true);
		BkManage.bkIDText.setText(String.valueOf(bkID));
		BkManage.changeBtn.setEnabled(false);
		
	}
}
