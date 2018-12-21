package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.ImageFilter;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BookManage extends JFrame {
	protected JTextField bkIDText;
	private JTextField bkCodeText;
	private JTextField bkNameText;
	private JTextField bkAuthorText;
	private JTextField bkPressText;
	private JTextField bkDatePressText;
	private JTextField ISBNText;
	private JTextField CataLogText;
	private JTextField bkPageText;
	private JTextField bkPriceText;
	protected JTextField bkNumberText;
	private JComboBox LanguageCombox;
	private JLabel lblCover;
	private JTextField bkDateInText; 
	private JTextArea bkBriefText; 
	protected JButton returnBtn;
	protected JButton changeBtn;
	protected JButton addBtn;
	private BookAdmin bookBll = new BookAdmin();
	
	protected void setReaderToText(Book book) throws SQLException
	{
		bkIDText.setText(String.valueOf(book.getBkID()));
		bkCodeText.setText(book.getBkCode());
		bkNameText.setText(book.getBkName());
		bkAuthorText.setText(book.getBkAuthor());
		bkPressText.setText(book.getBkPress());
		bkDatePressText.setText(String.valueOf(book.getBkDatePress()));
		ISBNText.setText(book.getBkISBN());
		CataLogText.setText(book.getBkCatalog());
		bkPageText.setText(String.valueOf(book.getBkPages()));
		bkPriceText.setText(String.valueOf(book.getBkPrice()));
		bkDateInText.setText(String.valueOf(book.getBkDateIn()));
		bkBriefText.setText(book.getBkBrief());
		
		if(book.getBkCover() != null) {
			 Image image = null;
			 InputStream input = null;
			 
			 input = new ByteArrayInputStream(book.getBkCover());
			 try {
				image = ImageIO.read(input);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 lblCover.setIcon(new ImageIcon (image));
		}
		
	}
	
	private Book getReaderFromText()
	{
		Book book = new Book();
		book.setBkID(Integer.valueOf(bkIDText.getText().trim()));
		book.setBkCode(bkCodeText.getText().trim());
		book.setBkName(bkNameText.getText().trim());     
		book.setBkAuthor(bkAuthorText.getText().trim());
		book.setBkPress(bkPressText.getText().trim());
		book.setBkDatePress(Date.valueOf(bkDatePressText.getText().trim()));
		book.setBkISBN(ISBNText.getText().trim());
		book.setBkCatalog(CataLogText.getText().trim());
		//book.setBkLanguage(Integer.valueOf(LanguageText.getText().trim()));
		book.setBkPages(Integer.valueOf(bkPageText.getText().trim()));
		book.setBkPrice(Float.valueOf(bkPriceText.getText().trim()));
		book.setBkDateIn(Date.valueOf(bkDateInText.getText().trim()));
		book.setBkBrief(bkBriefText.getText().trim());
		
		String Language = (String)LanguageCombox.getSelectedItem();
		String[] language = Language.split("-");
		book.setBkLanguage(Integer.parseInt(language[0]));
		if(lblCover.getIcon() != null) {
			Image image = ((ImageIcon)  lblCover.getIcon()).getImage();
			BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2 = bi.createGraphics();//绘制图形
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", os);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			InputStream fis  = new ByteArrayInputStream(os.toByteArray());
			byte[] BImage = new byte[os.size()];
			try {
				fis.read(BImage);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			book.setBkCover(BImage);
		}
		return book;
	}
	
	 public static Date addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/,int n/*加减天数*/)
	 { //日期格式 
		 SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值 
		 return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L); 
	 }
	private void blankText()
	{
			bkIDText.setText("");;
			bkCodeText.setText("");
			bkNameText.setText("");
			bkAuthorText.setText("");
			bkPressText.setText("");
			bkDatePressText.setText("");
			ISBNText.setText("");
			CataLogText.setText("");
			bkPageText.setText("");
			bkPriceText.setText("");
			bkNumberText.setText("");
			bkDateInText.setText("");
			bkBriefText.setText("");
			
	}
	public BookManage() {
		setSize(new Dimension(1024,768));
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF\u2014\u2014\u56FE\u4E66\u4FE1\u606F\u7EF4\u62A4");
		getContentPane().setLayout(null);
		
		JPanel bookInfoPanel = new JPanel();
		bookInfoPanel.setBounds(0, 23, 270, 564);
		getContentPane().add(bookInfoPanel);
		bookInfoPanel.setLayout(null);
		
		JLabel bkIDLabel = new JLabel("\u56FE\u4E66\u8D77\u59CB\u5E8F\u53F7:");
		bkIDLabel.setBounds(0, 0, 98, 18);
		bookInfoPanel.add(bkIDLabel);
		
		bkIDText = new JTextField();
		bkIDText.setEditable(false);
		bkIDText.setBounds(99, -3, 86, 24);
		bookInfoPanel.add(bkIDText);
		bkIDText.setColumns(10);
		
		JLabel bkCodeLabel = new JLabel("\u7D22\u4E66\u53F7:");
		bkCodeLabel.setBounds(0, 31, 72, 18);
		bookInfoPanel.add(bkCodeLabel);
		
		bkCodeText = new JTextField();
		bkCodeText.setBounds(70, 28, 186, 24);
		bookInfoPanel.add(bkCodeText);
		bkCodeText.setColumns(10);
		
		JLabel bkNameLabel = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		bkNameLabel.setBounds(0, 69, 72, 18);
		bookInfoPanel.add(bkNameLabel);
		
		bkNameText = new JTextField();
		bkNameText.setBounds(70, 66, 186, 24);
		bookInfoPanel.add(bkNameText);
		bkNameText.setColumns(10);
		
		JLabel bkAuthorLabel = new JLabel("\u56FE\u4E66\u4F5C\u8005:");
		bkAuthorLabel.setBounds(0, 111, 72, 18);
		bookInfoPanel.add(bkAuthorLabel);
		
		bkAuthorText = new JTextField();
		bkAuthorText.setBounds(70, 108, 186, 24);
		bookInfoPanel.add(bkAuthorText);
		bkAuthorText.setColumns(10);
		
		JLabel bkPressLabel = new JLabel("\u51FA\u7248\u793E:");
		bkPressLabel.setBounds(0, 159, 72, 18);
		bookInfoPanel.add(bkPressLabel);
		
		bkPressText = new JTextField();
		bkPressText.setBounds(70, 156, 186, 24);
		bookInfoPanel.add(bkPressText);
		bkPressText.setColumns(10);
		
		JLabel bkDatePressLabel = new JLabel("\u51FA\u7248\u65E5\u671F:");
		bkDatePressLabel.setBounds(0, 198, 72, 18);
		bookInfoPanel.add(bkDatePressLabel);
		
		bkDatePressText = new JTextField();
		bkDatePressText.setBounds(70, 195, 186, 24);
		bookInfoPanel.add(bkDatePressText);
		bkDatePressText.setColumns(10);
		
		JLabel ISBNLabel = new JLabel("ISBN:");
		ISBNLabel.setBounds(0, 242, 72, 18);
		bookInfoPanel.add(ISBNLabel);
		
		ISBNText = new JTextField();
		ISBNText.setBounds(70, 239, 186, 24);
		bookInfoPanel.add(ISBNText);
		ISBNText.setColumns(10);
		
		JLabel CataLogLabel = new JLabel("\u5206\u7C7B\u53F7:");
		CataLogLabel.setBounds(0, 276, 72, 18);
		bookInfoPanel.add(CataLogLabel);
		
		CataLogText = new JTextField();
		CataLogText.setBounds(70, 276, 186, 24);
		bookInfoPanel.add(CataLogText);
		CataLogText.setColumns(10);
		
		bkPageText = new JTextField();
		bkPageText.setBounds(70, 372, 186, 24);
		bookInfoPanel.add(bkPageText);
		bkPageText.setColumns(10);
		
		bkPriceText = new JTextField();
		bkPriceText.setBounds(70, 420, 186, 24);
		bookInfoPanel.add(bkPriceText);
		bkPriceText.setColumns(10);
		
		JLabel LanguageLabel = new JLabel("\u8BED\u79CD\uFF1A");
		LanguageLabel.setBounds(0, 328, 72, 18);
		bookInfoPanel.add(LanguageLabel);
		
		JLabel bkPageLabel = new JLabel("\u56FE\u4E66\u9875\u6570:");
		bkPageLabel.setBounds(0, 375, 72, 18);
		bookInfoPanel.add(bkPageLabel);
		
		JLabel bkPriceLabel = new JLabel("\u56FE\u4E66\u4EF7\u683C:");
		bkPriceLabel.setBounds(0, 423, 72, 18);
		bookInfoPanel.add(bkPriceLabel);
		
		JLabel bkDateInLabel = new JLabel("\u5165\u9986\u65E5\u671F:");
		bkDateInLabel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		bkDateInLabel.setBounds(0, 467, 72, 18);
		bookInfoPanel.add(bkDateInLabel);
		
		JLabel bkNumberLabel = new JLabel("\u56FE\u4E66\u672C\u6570:");
		bkNumberLabel.setBounds(0, 513, 72, 18);
		bookInfoPanel.add(bkNumberLabel);
		
		bkDateInText = new JTextField();
		bkDateInText.setColumns(10);
		bkDateInText.setBounds(70, 464, 186, 24);
		bookInfoPanel.add(bkDateInText);
		
		bkNumberText = new JTextField();
		bkNumberText.setColumns(10);
		bkNumberText.setBounds(70, 507, 186, 24);
		bkNumberText.setText("1");
		bookInfoPanel.add(bkNumberText);
		
		LanguageCombox = new JComboBox();
		LanguageCombox.setModel(new DefaultComboBoxModel(new String[] {"0-\u4E2D\u6587", "1-\u82F1\u6587", "2-\u6CD5\u6587", "3-\u5FB7\u6587"}));
		LanguageCombox.setBounds(70, 323, 186, 24);
		bookInfoPanel.add(LanguageCombox);
		
		JPanel bkBriefPanel = new JPanel();
		bkBriefPanel.setBounds(296, 23, 313, 564);
		getContentPane().add(bkBriefPanel);
		bkBriefPanel.setLayout(null);
		
		JLabel bkBriefLabel = new JLabel("\u5185\u5BB9\u7B80\u4ECB:");
		bkBriefLabel.setBounds(0, 0, 74, 18);
		bkBriefPanel.add(bkBriefLabel);
		
		bkBriefText = new JTextArea();
		bkBriefText.setLineWrap(true);
		bkBriefText.setBounds(0, 24, 313, 540);
		bkBriefPanel.add(bkBriefText);
		
		JPanel bkImagePanel = new JPanel();
		bkImagePanel.setBounds(623, 23, 294, 564);
		getContentPane().add(bkImagePanel);
		bkImagePanel.setLayout(null);
		
		JLabel bkCoverLabel = new JLabel("\u5C01\u9762:");
		bkCoverLabel.setBounds(0, 0, 57, 23);
		bkImagePanel.add(bkCoverLabel);
		
		//添加封面图书按钮
		
		JButton addCoverbtn = new JButton("\u6DFB\u52A0\u56FE\u7247");
		addCoverbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				int returnVal = fc.showOpenDialog(BookManage.this);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					try
					{
						BufferedImage img = ImageIO.read(file);
						Image dimg = img.getScaledInstance(lblCover.getWidth(), lblCover.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblCover.setIcon(icon);
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		addCoverbtn.setBounds(60, 540, 172, 24);
		bkImagePanel.add(addCoverbtn);
		
		lblCover = new JLabel("");
		lblCover.setBounds(10, 29, 270, 504);
		bkImagePanel.add(lblCover);
		
		JPanel operaterPanel = new JPanel();
		operaterPanel.setBounds(296, 600, 302, 50);
		getContentPane().add(operaterPanel);
		operaterPanel.setLayout(null);
		
		//添加新的图书
		
		addBtn = new JButton("\u5B8C\u6210\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = Integer.valueOf(bkNumberText.getText().trim());  
				for(int m = 0; m<i; m++)
				{
					try {
						int n = bookBll.getBkNumber().getBkID() + 1;
						Book book = getReaderFromText();
						book.setBkID(n);
						book.setBkStatus("在馆");
						bookBll.insertBook(book);
						JOptionPane.showMessageDialog(null, "第"+(m+1)+"本书添加成功！");
							
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
				
				
				
			}
		});
		addBtn.setBounds(0, 5, 93, 27);
		operaterPanel.add(addBtn);
		
		//修改图书信息
		changeBtn = new JButton("\u5B8C\u6210\u4FEE\u6539");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Book book = getReaderFromText();
				
				bookBll.updateBook(book);
				JOptionPane.showMessageDialog(null, "修改成功!");
				blankText();
				dispose();
				}
				catch(Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		changeBtn.setBounds(92, 5, 93, 27);
		operaterPanel.add(changeBtn);
		
		returnBtn = new JButton("\u8FD4\u56DE");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				blankText();
			}
		});
		returnBtn.setBounds(184, 5, 63, 27);
		operaterPanel.add(returnBtn);
	}
}
