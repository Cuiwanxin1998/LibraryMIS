package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Panel;

public class Main extends JFrame {
	private JMenu MN_ReaderMgt;
	private JMenu MN_BookMgt;
	private JMenu MN_BorrowMgt;
	private JMenuBar menuBar;
	private final String blankPanelName = "Blank";
	private final String readerPanelName = "Reader";
	private final String bookManagePanelName  = "BookManage";
	private final String borrowPanelName = "Borrow";
	private final String userPanelName = "user";
	private final String readerTypePanelName = "ReaderType";
	
	private JPanel cards;
	private ReaderPanel readerPanel;
	private BorrowPanel borrowPanel;
	private UserPanel userPanel;
	private ReaderTypePanel readerTypePanel;
	private BookManagePanel bookManagePanel;
	public Main() {
		setSize(new Dimension(1024,768));
		
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		
		initCardPanels();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		MN_ReaderMgt = new JMenu("\u8BFB\u8005\u7BA1\u7406");
		MN_ReaderMgt.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		menuBar.add(MN_ReaderMgt);
		
		JMenuItem MI_NewReader = new JMenuItem("\u529E\u7406\u501F\u4E66\u8BC1");
		MI_NewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(cards.getLayout());
				c1.show(cards, readerPanelName);
				
			}
		});
		MN_ReaderMgt.add(MI_NewReader);
		
		JMenuItem MI_ReaderTypeMgt = new JMenuItem("\u8BFB\u8005\u7C7B\u578B\u7BA1\u7406");
		MI_ReaderTypeMgt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(cards.getLayout());
				c1.show(cards, readerTypePanelName);
			}
		});
		MN_ReaderMgt.add(MI_ReaderTypeMgt);
		
		MN_BookMgt = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		MN_BookMgt.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		menuBar.add(MN_BookMgt);
		
		JMenuItem MN_ChangeBookInfo = new JMenuItem("\u56FE\u4E66\u4FE1\u606F\u7EF4\u62A4");
		MN_ChangeBookInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(cards.getLayout());
				c1.show(cards, bookManagePanelName);
			}
		});
		MN_BookMgt.add(MN_ChangeBookInfo);
		
		 MN_BorrowMgt = new JMenu("\u501F\u9605\u7BA1\u7406");
		MN_BorrowMgt.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		menuBar.add(MN_BorrowMgt);
		
		JMenuItem MI_Borrow = new JMenuItem("\u501F\u4E66(\u8FD8\u4E66\u3001\u7EED\u501F)");
		MI_Borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(cards.getLayout());
				c1.show(cards, borrowPanelName);
			}
		});
		MN_BorrowMgt.add(MI_Borrow);
		
		//initMenu();
	}

		private void initMenu()
		{
			MN_ReaderMgt.setEnabled(Login.reader.isReaderAdmin());
			MN_BookMgt.setEnabled(Login.reader.isBookAdmin());
			MN_BorrowMgt.setEnabled(Login.reader.isBorrowAdmin());

		}
		
		private void initCardPanels()
		{
			JPanel blankPanel = new JPanel();
			
			readerPanel = new ReaderPanel();
			readerPanel.setVisible(false);//隐藏控件

			
			borrowPanel = new BorrowPanel();
			borrowPanel.setVisible(false);
			
			userPanel = new UserPanel();
			userPanel.setVisible(false);
			
			readerTypePanel = new ReaderTypePanel();
			readerTypePanel.setVisible(false);
			
			bookManagePanel = new BookManagePanel();
			bookManagePanel.setVisible(false);

			/*
			 * 对于选项卡这个概念大家可能不会陌生，就是在一个窗口中可以切换显示多页不同的内容，但同一时间只能是其中的某一页可见的，
		这样的一个个的页面就是选项卡。

               CardLayout就是类似的这样一个布局管理器，它能够让多个组件共享同一个显示空间，共享空间的组件之间的关系就像重叠在一起的一幅扑克牌，
               组件重叠在一起，初始时显示该空间中第一个组件，通过CardLayout类提供的方法可以切换该空间中显示的组件。
			*/
			cards = new JPanel(new CardLayout());
			cards.add(blankPanel,blankPanelName);
			cards.add(readerPanel,readerPanelName);
			cards.add(borrowPanel,borrowPanelName);
			cards.add(userPanel,userPanelName);
			cards.add(readerTypePanel,readerTypePanelName);
			cards.add(bookManagePanel,bookManagePanelName);
			//初始化一个容器
			getContentPane().add(cards);
		}

}
