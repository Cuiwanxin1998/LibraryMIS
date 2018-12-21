package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.border.SoftBevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.yangtzeu.lmis.bll.DepartmentTypeAdmin;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.dal.DepartmentTypeDAL;
import edu.yangtzeu.lmis.dal.ReaderTypeDAL;
import edu.yangtzeu.lmis.model.CustomizedTableModel;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.ImageFilter;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class ReaderPanel extends JPanel {
	private JTextField tfReaderID;
	private JTable searchResultTable;
	private JScrollPane searchResultscroll;
	private JTextField tfUserName;
	private JTextField tfReaderName;
	private JTextField passwordField;
	private JTextField tfNumBorrowed;
	private JTextField tfStatus;
	private JTextField tfReaderRole;
	private JTextField tfReaderPhone;
	private JTextField tfEmail;
	private JTextField tfDate;
	private JComboBox cbGender; 
	private JComboBox cbReaderType; 
	private JComboBox cbDeptType; 
	private JComboBox rdTypeComboBox;
	private JComboBox deptTypeComboBox;
	private JButton btnSubmitUpdate;
	private JButton btnAddReader;
	private JButton btnNewReader;
	private JButton btnUpdateReader;
	private JButton btnCancelReader; 
	private JLabel lblPhoto;
	private JButton btnQuery;
	private JPanel searchPanel;
	private JPanel searchResultPanel;
	private JPanel functionCtrlPanel;
	private JPanel readerInfoPanel;
	private JPanel editCtrlPanel;
	private Date now = new Date();// new Date()为获取当前系统时间
	private ReaderAdmin readerBll = new ReaderAdmin();
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private DepartmentTypeAdmin deptTypeBll = new DepartmentTypeAdmin();	
	private enum OpStatus
	{
		inSelect, inNew, inChange,isOK
	}
	private void setComponentStatusInPanel(JPanel panel,boolean status)
	{
		for(Component comp : panel.getComponents())
		{
			comp.setEnabled(status);
		}
	}
	
	private OpStatus ops;
	private void SetStatus(OpStatus opst)
	{
		ops = opst;
		switch(ops)
		{
		case inSelect:
			searchPanel.setEnabled(true);
			searchResultPanel.setEnabled(true);
			functionCtrlPanel.setEnabled(true);
			//更改Panel中组件的状态
			setComponentStatusInPanel(functionCtrlPanel,true);
			readerInfoPanel.setEnabled(false);
			readerInfoPanel.setVisible(false);
			editCtrlPanel.setEnabled(false);
			editCtrlPanel.setVisible(false);
			setComponentStatusInPanel(editCtrlPanel,false);
			break;
		case inNew:
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtrlPanel.setEnabled(false);
			//更改Panel中组件的状态
			setComponentStatusInPanel(functionCtrlPanel,false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			editCtrlPanel.setEnabled(true);
			editCtrlPanel.setVisible(true);
			setComponentStatusInPanel(editCtrlPanel,true);
			btnSubmitUpdate.setEnabled(false);
			break;
		case inChange:
			searchPanel.setEnabled(false);
			searchResultPanel.setEnabled(false);
			functionCtrlPanel.setEnabled(false);
			//更改Panel中组件的状态
			setComponentStatusInPanel(functionCtrlPanel,false);
			readerInfoPanel.setEnabled(true);
			readerInfoPanel.setVisible(true);
			editCtrlPanel.setEnabled(true);
			editCtrlPanel.setVisible(true);
			setComponentStatusInPanel(editCtrlPanel,true);
			btnAddReader.setEnabled(false);
			break;
		case isOK:
			searchPanel.setEnabled(true);
			searchResultPanel.setEnabled(true);
			functionCtrlPanel.setEnabled(true);
			//更改Panel中组件的状态
			setComponentStatusInPanel(functionCtrlPanel,true);
			readerInfoPanel.setEnabled(false);
			readerInfoPanel.setVisible(false);
			editCtrlPanel.setEnabled(false);
			editCtrlPanel.setVisible(false);
			setComponentStatusInPanel(editCtrlPanel,false);
			btnAddReader.setEnabled(false);
			break;
		
			
		}
	}
	private void setReaderToText(Reader reader) throws SQLException
	{
	
		tfReaderID.setText(String.valueOf(reader.getRdID()));
		tfReaderName.setText(reader.getRdName());
		passwordField.setText(reader.getRdPwd());
		tfNumBorrowed.setText(String.valueOf(reader.getRdBorrowQty()));
		tfStatus.setText(reader.getRdStatus());
		tfReaderRole.setText(String.valueOf(reader.getRdAdminRoles()));
		tfReaderPhone.setText(reader.getRdPhone());
		tfEmail.setText(reader.getRdEmail());
		tfDate.setText(String.valueOf(reader.getRdDateReg()));
		cbGender.setSelectedItem(reader.getRdSex());
		cbReaderType.setSelectedItem(readerTypeBll.getObjectByID(reader.getRdType()));
		cbDeptType.setSelectedItem(deptTypeBll.getObjectByID(reader.getRdDept()));	
		
		if(reader.getRdPhoto() != null)
		{
			 Image image = null;
			 InputStream input = null;
			 
			 input = new ByteArrayInputStream(reader.getRdPhoto());
			 try {
				image = ImageIO.read(input);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 lblPhoto.setIcon(new ImageIcon (image));
		}
	}
	private void updateTable()
	{
		ReaderType rdTypeName = (ReaderType) rdTypeComboBox.getSelectedItem();
		DepartmentType deptType = (DepartmentType) deptTypeComboBox.getSelectedItem();
		String userName = tfUserName.getText().trim();
		Reader[] hits = readerBll.retrieveReaders(rdTypeName,deptType,userName);
		updateResultTable(hits);
	}
	

	
	private Reader getReaderFromText()
	{
		Reader reader = new Reader();
		reader.setRdID(Integer.valueOf(tfReaderID.getText()));
		reader.setRdName(tfReaderName.getText().trim());
		reader.setRdPwd(passwordField.getText().trim());     
		reader.setRdSex(cbGender.getSelectedItem().toString());
		reader.setRdType(((ReaderType)cbReaderType.getSelectedItem()).getRdType());
		reader.setRdDept(((DepartmentType) cbDeptType.getSelectedItem()).getRdDept());
		reader.setRdPhone(tfReaderPhone.getText().trim());
		reader.setRdStatus(tfStatus.getText().trim());
		reader.setRdEmail(tfEmail.getText().trim());
		
		reader.setRdDateReg(now);
		
		if(lblPhoto.getIcon() != null) {
			Image image = ((ImageIcon)  lblPhoto.getIcon()).getImage();
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
			reader.setRdPhoto(BImage);
		}
		return reader;
	}

	private void updateResultTable(Reader[] readers)
	{
		if(readers == null)
		{
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录：");
			return;
		}
		CustomizedTableModel<Reader> tableModel = (CustomizedTableModel<Reader>) searchResultTable.getModel();
		tableModel.setRecords(readers);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	public ReaderPanel() {
		

		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		searchPanel = new JPanel();
		searchPanel.setBounds(2, 15, 725, 48);
		searchPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel rdTypeLabel = new JLabel("\u8BFB\u8005\u7C7B\u522B:");
		rdTypeLabel.setBounds(8, 15, 68, 18);

		rdTypeComboBox = new JComboBox(readerTypeBll.getReaderTypes());	
		rdTypeComboBox.setBounds(82, 12, 125, 24);
		
		JLabel rdDeptLabel = new JLabel("\u5355\u4F4D:");
		rdDeptLabel.setBounds(213, 14, 38, 18);
		
	
		deptTypeComboBox = new JComboBox(deptTypeBll.getDepartmentTypes());
		deptTypeComboBox.setBounds(257, 11, 141, 24);
		
		JLabel rdNameLabel = new JLabel("\u59D3\u540D:");
		rdNameLabel.setBounds(404, 14, 38, 18);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(460, 11, 86, 24);
		tfUserName.setColumns(10);
		
		btnQuery = new JButton("\u67E5\u627E");
		btnQuery.setBounds(564, 10, 63, 27);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
				
			}
		});
		
		readerInfoPanel = new JPanel();
		readerInfoPanel.setBounds(573, 70, 443, 480);
		
		searchResultPanel = new JPanel();
		searchResultPanel.setBounds(16, 70, 535, 486);
		
		functionCtrlPanel = new JPanel();
		functionCtrlPanel.setBounds(16, 569, 543, 50);
		
		editCtrlPanel = new JPanel();
		editCtrlPanel.setBounds(640, 578, 279, 41);
		searchPanel.setLayout(null);
		searchPanel.add(rdTypeLabel);
		searchPanel.add(rdTypeComboBox);
		searchPanel.add(rdDeptLabel);
		searchPanel.add(deptTypeComboBox);
		searchPanel.add(rdNameLabel);
		searchPanel.add(tfUserName);
		searchPanel.add(btnQuery);
		
		btnAddReader = new JButton("\u786E\u8BA4\u529E\u8BC1");
		btnAddReader.setBounds(5, 5, 93, 27);
		btnAddReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reader reader = getReaderFromText();
				readerBll.insertReader(reader);
				updateTable();
				SetStatus(OpStatus.isOK);
			}
		});
		
		btnSubmitUpdate = new JButton("\u786E\u8BA4\u53D8\u66F4");
		btnSubmitUpdate.setBounds(103, 5, 93, 27);
		btnSubmitUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reader reader = getReaderFromText();
				readerBll.updateReader(reader);
				updateTable();
				SetStatus(OpStatus.isOK);
				
			}
		});
		
		JButton btnCancelEdit = new JButton("\u53D6\u6D88");
		btnCancelEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetStatus(OpStatus.inSelect);
			}
		});
		btnCancelEdit.setBounds(201, 5, 63, 27);
		editCtrlPanel.setLayout(null);
		editCtrlPanel.add(btnAddReader);
		editCtrlPanel.add(btnSubmitUpdate);
		editCtrlPanel.add(btnCancelEdit);
		
		btnNewReader = new JButton("\u529E\u7406\u501F\u4E66\u8BC1");
		btnNewReader.setBounds(11, 5, 107, 27);
		btnNewReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tfReaderRole.setText("0");
				tfStatus.setText("有效");
				tfNumBorrowed.setText("0");
				tfReaderID.setEditable(true);
				tfReaderID.setEnabled(true);
				tfDate.setText(sf.format(now));

				SetStatus(OpStatus.inNew);
			}
		});
		functionCtrlPanel.setLayout(null);
		functionCtrlPanel.add(btnNewReader);
		
		btnUpdateReader = new JButton("\u53D8\u66F4\u501F\u4E66\u8BC1");
		btnUpdateReader.setBounds(123, 5, 107, 27);
		btnUpdateReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					setReaderToText(((CustomizedTableModel<Reader>) searchResultTable.getModel()).getObjectAt(selectedRow));
					tfReaderID.setEditable(false);
					tfReaderID.setEnabled(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SetStatus(OpStatus.inChange);
				
			}
		});
		functionCtrlPanel.add(btnUpdateReader);
		
		JButton btnLost = new JButton("\u6302\u5931");
		btnLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					Reader reader = ((CustomizedTableModel<Reader>) searchResultTable.getModel()).getObjectAt(selectedRow);
					if(reader.getRdStatus().equals("挂失"))
					{
						JOptionPane.showMessageDialog(null, "借书证已是挂失状态!");
						return;
					}
					readerBll.lostReader(reader);
					updateTable();
					
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "挂失成功!");
			}
		});
		btnLost.setBounds(235, 5, 63, 27);
		functionCtrlPanel.add(btnLost);
		
		JButton btnFound = new JButton("\u89E3\u9664\u6302\u5931");
		btnFound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					Reader reader = ((CustomizedTableModel<Reader>) searchResultTable.getModel()).getObjectAt(selectedRow);
					if(reader.getRdStatus().equals("有效"))
					{
						JOptionPane.showMessageDialog(null, "借书证已是有效状态!");
						return;
					}
					readerBll.FoundReader(reader);
					updateTable();
					
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "解除挂失成功!");
			}
		});
		btnFound.setBounds(303, 5, 93, 27);
		functionCtrlPanel.add(btnFound);
		
		btnCancelReader = new JButton("\u6CE8\u9500");
		btnCancelReader.setBounds(401, 5, 63, 27);
		btnCancelReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = searchResultTable.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					Reader reader = ((CustomizedTableModel<Reader>) searchResultTable.getModel()).getObjectAt(selectedRow);
					readerBll.delete(reader);
					updateTable();
					
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "注销成功!");
			}
		});
		functionCtrlPanel.add(btnCancelReader);
		
		JButton btnClose = new JButton("\u9000\u51FA");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setBounds(469, 5, 63, 27);
		functionCtrlPanel.add(btnClose);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u7ED3\u679C");
		label.setBounds(0, 0, 60, 18);
		//定义table
		CustomizedTableModel<Reader> tableModel = new CustomizedTableModel<Reader>(
				readerBll.getDisplayColumnNames(),readerBll.getMethodNames());
		searchResultTable = new JTable(tableModel);
		
		searchResultscroll = new JScrollPane(searchResultTable);
		searchResultscroll.setBounds(0, 13, 535, 460);
		searchResultPanel.setLayout(null);
		searchResultPanel.add(label);
		searchResultPanel.add(searchResultscroll);
		
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel rdIDlabel = new JLabel("\u501F\u4E66\u8BC1\u53F7:");
		rdIDlabel.setBounds(0, 28, 71, 18);
		
		JLabel rdNamelabel = new JLabel("\u59D3\u540D:");
		rdNamelabel.setBounds(26, 70, 45, 18);
		
		JLabel rdPwdlabel = new JLabel("\u5BC6\u7801:");
		rdPwdlabel.setBounds(26, 112, 45, 18);
		
		JLabel rdSexlabel = new JLabel("\u6027\u522B:");
		rdSexlabel.setBounds(26, 154, 45, 18);
		
		JLabel rdBorrowQtylabel = new JLabel("\u5DF2\u501F\u4E66:");
		rdBorrowQtylabel.setBounds(14, 185, 60, 30);
		
		JLabel rdStatuslabel = new JLabel("\u8BC1\u4EF6\u72B6\u6001:");
		rdStatuslabel.setBounds(0, 228, 71, 18);
		
		JLabel rdAdminRoleslabel = new JLabel("\u8BFB\u8005\u89D2\u8272:");
		rdAdminRoleslabel.setBounds(0, 265, 71, 18);
		
		JLabel rdTypelabel = new JLabel("\u8BFB\u8005\u7C7B\u522B:");
		rdTypelabel.setBounds(0, 302, 71, 18);
		
		JLabel rdDeptlabel = new JLabel("\u5355\u4F4D:");
		rdDeptlabel.setBounds(26, 333, 45, 30);
		
		JLabel rdTellabel = new JLabel("\u7535\u8BDD\u53F7\u7801:");
		rdTellabel.setBounds(0, 376, 71, 18);
		
		JLabel rdEmaillabel = new JLabel("\u7535\u5B50\u90AE\u4EF6:");
		rdEmaillabel.setBounds(0, 413, 71, 18);
		
		JLabel DateReglabel = new JLabel("\u529E\u8BC1\u65E5\u671F:");
		DateReglabel.setBounds(0, 447, 71, 18);
		
		tfReaderID = new JTextField();
		tfReaderID.setEnabled(false);
		tfReaderID.setEditable(false);
		tfReaderID.setBounds(90, 25, 119, 24);
		tfReaderID.setColumns(10);
		
		tfReaderName = new JTextField();
		tfReaderName.setBounds(90, 67, 119, 24);
		tfReaderName.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(90, 109, 119, 24);
		passwordField.setText("");
		passwordField.setColumns(10);
		
		tfNumBorrowed = new JTextField();
		tfNumBorrowed.setEditable(false);
		tfNumBorrowed.setEnabled(false);
		tfNumBorrowed.setBounds(90, 188, 119, 24);
		tfNumBorrowed.setColumns(10);
		
		tfStatus = new JTextField();
		tfStatus.setEnabled(false);
		tfStatus.setEditable(false);
		tfStatus.setBounds(90, 225, 119, 24);
		tfStatus.setColumns(10);
		
		tfReaderRole = new JTextField();
		tfReaderRole.setEnabled(false);
		tfReaderRole.setEditable(false);
		tfReaderRole.setBounds(90, 262, 119, 24);
		tfReaderRole.setColumns(10);
		
		tfReaderPhone = new JTextField();
		tfReaderPhone.setBounds(90, 373, 119, 24);
		tfReaderPhone.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(90, 410, 119, 24);
		tfEmail.setColumns(10);
		
		tfDate = new JTextField();
		tfDate.setEditable(false);
		tfDate.setEnabled(false);
		tfDate.setBounds(90, 441, 119, 24);
		tfDate.setColumns(10);
		cbGender = new JComboBox();
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
		cbGender.setBounds(90, 151, 119, 24);
		
		cbReaderType = new JComboBox(readerTypeBll.getReaderTypes());
		cbReaderType.setBounds(90, 299, 119, 24);
		
		cbDeptType = new JComboBox(deptTypeBll.getDepartmentTypes());
		cbDeptType.setBounds(90, 336, 119, 24);
		
		lblPhoto = new JLabel("");
		lblPhoto.setBounds(227, 109, 159, 260);
		lblPhoto.setForeground(Color.GRAY);
		lblPhoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JButton btnLoadPictureFile = new JButton("\u6253\u5F00\u56FE\u7247\u6587\u4EF6");
		btnLoadPictureFile.setBounds(245, 387, 123, 27);
		btnLoadPictureFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				int returnVal = fc.showOpenDialog(ReaderPanel.this);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					try
					{
						BufferedImage img = ImageIO.read(file);
						Image dimg = img.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblPhoto.setIcon(icon);
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
		});
		
		JLabel lblReaderinfolabel = new JLabel("\u8BFB\u8005\u4FE1\u606F");
		lblReaderinfolabel.setBounds(0, 0, 60, 18);
		readerInfoPanel.setLayout(null);
		readerInfoPanel.add(rdIDlabel);
		readerInfoPanel.add(rdNamelabel);
		readerInfoPanel.add(rdPwdlabel);
		readerInfoPanel.add(rdSexlabel);
		readerInfoPanel.add(rdBorrowQtylabel);
		readerInfoPanel.add(rdStatuslabel);
		readerInfoPanel.add(rdAdminRoleslabel);
		readerInfoPanel.add(rdTypelabel);
		readerInfoPanel.add(rdDeptlabel);
		readerInfoPanel.add(rdTellabel);
		readerInfoPanel.add(rdEmaillabel);
		readerInfoPanel.add(DateReglabel);
		readerInfoPanel.add(passwordField);
		readerInfoPanel.add(tfReaderName);
		readerInfoPanel.add(tfReaderID);
		readerInfoPanel.add(tfDate);
		readerInfoPanel.add(tfEmail);
		readerInfoPanel.add(tfReaderPhone);
		readerInfoPanel.add(tfReaderRole);
		readerInfoPanel.add(tfStatus);
		readerInfoPanel.add(tfNumBorrowed);
		readerInfoPanel.add(btnLoadPictureFile);
		readerInfoPanel.add(lblPhoto);
		readerInfoPanel.add(lblReaderinfolabel);	
		readerInfoPanel.add(cbGender);
		readerInfoPanel.add(cbReaderType);
		readerInfoPanel.add(cbDeptType);
		
		JLabel searchResultLabel = new JLabel("\u67E5\u8BE2\u7ED3\u679C");
		setLayout(null);
		add(searchResultPanel);
		add(functionCtrlPanel);
		add(editCtrlPanel);
		add(readerInfoPanel);
		add(searchPanel);
		
		SetStatus(OpStatus.inSelect);
		
	}
}
