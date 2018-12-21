package edu.yangtzeu.lmis.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;

import edu.yangtzeu.lmis.model.CustomizedTableModel;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class ReaderTypePanel extends JPanel {
	private JTextField rdTypeNameText;
	private JTextField borrowQtyText;
	private JTextField CanLendDayText;
	private JTextField CanLendQtyText;
	private JTextField PunishRateText;
	private JTextField DateValidText;
	private JButton changeBtn;
	private JButton stopBtn;
	private JButton button;
	private JButton addBtn;
	private JButton OkBtn;
	private JPanel searchPanel; 
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private JTable table;
	private JScrollPane scrollPane;
	private void setReaderToText(ReaderType readerType) throws SQLException
	{
		rdTypeNameText.setText(readerType.getRdTypeName());
		borrowQtyText.setText(String.valueOf(readerType.getCanLendQty()));
		CanLendDayText.setText(String.valueOf(readerType.getCanLendDay()));
		CanLendQtyText.setText(String.valueOf(readerType.getCanContinueTimes()));
		PunishRateText.setText(String.valueOf(readerType.getPunishRate()));
		DateValidText.setText(String.valueOf(readerType.getDateValid()));
	}
	
	private void blankText()
	{
		rdTypeNameText.setText("");;
		borrowQtyText.setText("");
		CanLendDayText.setText("");
		CanLendQtyText.setText("");
		PunishRateText.setText("");
		DateValidText.setText("");
	}
	private ReaderType getReaderTypeFromText()
	{
		ReaderType readerType = new ReaderType();
		readerType.setRdTypeName(rdTypeNameText.getText());
		readerType.setCanLendQty(Integer.valueOf(borrowQtyText.getText().trim()));
		readerType.setCanLendDay(Integer.valueOf(CanLendDayText.getText().trim()));     
		readerType.setCanContinueTimes(Integer.valueOf(CanLendQtyText.getText().trim()));
		readerType.setPunishRate(Float.valueOf(PunishRateText.getText().trim()));
		readerType.setDateValid(Integer.valueOf(DateValidText.getText().trim()));
		return readerType;
	}
	private enum OpStatues
	{
		inChange,isOK
	}
	private OpStatues ops;
	private JTextField rdTypeIDText;
	private JButton backBtn;
	private void SetStatus(OpStatues opst)
	{
		ops = opst;
		switch(ops)
		{
		case inChange:
			changeBtn.setEnabled(false);
			
			button.setEnabled(false);
			stopBtn.setEnabled(true);
			break;
		case isOK:
			changeBtn.setEnabled(true);	
			button.setEnabled(true);
			stopBtn.setEnabled(false);
			break;
		}
	}
	private void updateResultTable(ReaderType[] readerType)
	{
		CustomizedTableModel<ReaderType> tableModel = (CustomizedTableModel<ReaderType>) table.getModel();
		tableModel.setRecords(readerType);
		//更新表格
		tableModel.fireTableDataChanged();
	}
	private void updateTable()
	{
		ReaderType[] hits = readerTypeBll.getReaderTypes();
		updateResultTable(hits);
	}
	
	public ReaderTypePanel() {

		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBounds(56, 34, 889, 144);
		add(searchResultPanel);
		searchResultPanel.setLayout(null);
		
		JLabel rdTypeNameLabel = new JLabel("\u7C7B\u578B\u540D\u79F0:");
		rdTypeNameLabel.setBounds(117, 26, 76, 18);
		searchResultPanel.add(rdTypeNameLabel);
		
		JLabel DateValidLabel = new JLabel("\u8BC1\u4EF6\u6709\u6548\u671F:");
		DateValidLabel.setBounds(564, 82, 100, 18);
		searchResultPanel.add(DateValidLabel);
		
		DateValidText = new JTextField();
		DateValidText.setBounds(663, 78, 86, 24);
		searchResultPanel.add(DateValidText);
		DateValidText.setColumns(10);
		
		PunishRateText = new JTextField();
		PunishRateText.setBounds(390, 79, 86, 24);
		searchResultPanel.add(PunishRateText);
		PunishRateText.setColumns(10);
		
		JLabel PunishRateLabel = new JLabel("\u7F5A\u91D1\u7387:");
		PunishRateLabel.setBounds(323, 82, 53, 18);
		searchResultPanel.add(PunishRateLabel);
		
		CanLendQtyText = new JTextField();
		CanLendQtyText.setBounds(207, 79, 86, 24);
		searchResultPanel.add(CanLendQtyText);
		CanLendQtyText.setText("");
		CanLendQtyText.setColumns(10);
		
		JLabel CanLendQtyLabel = new JLabel("\u53EF\u7EED\u501F\u6B21\u6570:");
		CanLendQtyLabel.setBounds(105, 82, 100, 18);
		searchResultPanel.add(CanLendQtyLabel);
		
		CanLendDayText = new JTextField();
		CanLendDayText.setBounds(663, 23, 86, 24);
		searchResultPanel.add(CanLendDayText);
		CanLendDayText.setColumns(10);
		
		JLabel CanLendDayLabel = new JLabel("\u53EF\u501F\u5929\u6570:");
		CanLendDayLabel.setBounds(564, 26, 72, 18);
		searchResultPanel.add(CanLendDayLabel);
		
		borrowQtyText = new JTextField();
		borrowQtyText.setBounds(389, 23, 86, 24);
		searchResultPanel.add(borrowQtyText);
		borrowQtyText.setEnabled(true);
		borrowQtyText.setEditable(true);
		borrowQtyText.setText("");
		borrowQtyText.setColumns(10);
		
		JLabel borrowQtyLabel = new JLabel("\u53EF\u501F\u6570\u91CF:");
		borrowQtyLabel.setBounds(322, 26, 72, 18);
		searchResultPanel.add(borrowQtyLabel);
		
		rdTypeNameText = new JTextField();
		rdTypeNameText.setBounds(196, 23, 86, 24);
		searchResultPanel.add(rdTypeNameText);
		rdTypeNameText.setColumns(10);
		
		JLabel lblid = new JLabel("\u7C7B\u578BID\uFF1A");
		lblid.setBounds(0, 0, 72, 18);
		searchResultPanel.add(lblid);
		
		rdTypeIDText = new JTextField();
		rdTypeIDText.setEnabled(false);
		rdTypeIDText.setEditable(false);
		rdTypeIDText.setColumns(10);
		rdTypeIDText.setBounds(1, 20, 86, 24);
		searchResultPanel.add(rdTypeIDText);
		
		OkBtn = new JButton("\u6DFB\u52A0\u5B8C\u6210");
		OkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReaderType readerType = getReaderTypeFromText();
				readerType.setRdType(Integer.valueOf(rdTypeIDText.getText().trim()));
				
				readerTypeBll.add(readerType);
				changeBtn.setEnabled(true);
				
				stopBtn.setEnabled(true);
				button.setEnabled(true);
				addBtn.setEnabled(true);
				backBtn.setEnabled(false);
				OkBtn.setEnabled(false);
				rdTypeIDText.setEditable(false);
				rdTypeIDText.setEnabled(false);
				updateTable();
				
			}
		});
		OkBtn.setEnabled(false);
		OkBtn.setBounds(0, 57, 106, 27);
		searchResultPanel.add(OkBtn);
		
		backBtn = new JButton("\u53D6\u6D88");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetStatus(OpStatues.isOK);
				OkBtn.setEnabled(false);
				backBtn.setEnabled(false);
				addBtn.setEnabled(true);
				rdTypeIDText.setEnabled(false);
				blankText();
			}
		});
		backBtn.setEnabled(false);
		backBtn.setBounds(0, 113, 113, 27);
		searchResultPanel.add(backBtn);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(56, 191, 889, 421);
		add(searchPanel);
		
		//定义Table
		CustomizedTableModel<ReaderType> tableModel = new CustomizedTableModel<ReaderType>(
				readerTypeBll.getDisplayColumnNames(),readerTypeBll.getMethodNames());
	
		
		JLabel label = new JLabel("\u67E5\u8BE2\u7ED3\u679C:");
		label.setBounds(0, 0, 72, 18);
		
		
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 28, 889, 393);
		searchPanel.setLayout(null);
		searchPanel.add(label);
		searchPanel.add(scrollPane);
		
		updateTable();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(261, 633, 427, 39);
		add(panel);
		panel.setLayout(null);
		
		changeBtn = new JButton("\u4FEE\u6539");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow < 0)
				{
					JOptionPane.showMessageDialog(null, "请先选中一条记录!");
					return;
				}
				try {
					setReaderToText(((CustomizedTableModel<ReaderType>) table.getModel()).getObjectAt(selectedRow));
					
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				
				SetStatus(OpStatues.inChange);
				addBtn.setEnabled(false);
			}
		});
		changeBtn.setBounds(161, 5, 63, 27);
		panel.add(changeBtn);
		
		stopBtn = new JButton("\u5B8C\u6210");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				ReaderType readerType = getReaderTypeFromText();
				readerType.setRdType(((CustomizedTableModel<ReaderType>) table.getModel()).getObjectAt(selectedRow).getRdType());;
				//readerType.setRdType();
				readerTypeBll.updateReaderType(readerType);
				updateTable();
				blankText();
				SetStatus(OpStatues.isOK);
			}
		});
		stopBtn.setBounds(238, 5, 63, 27);
		panel.add(stopBtn);
		button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(304, 5, 63, 27);
		panel.add(button);
		
		addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBtn.setEnabled(false);
				
				stopBtn.setEnabled(false);
				button.setEnabled(false);
				addBtn.setEnabled(false);
				backBtn.setEnabled(true);
				OkBtn.setEnabled(true);
				rdTypeIDText.setEditable(true);
				rdTypeIDText.setEnabled(true);
				
			}
		});
		addBtn.setBounds(73, 5, 74, 27);
		panel.add(addBtn);
		SetStatus(OpStatues.isOK);
	}
}
