package edu.yangtzeu.lmis.gui;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.gui.Main;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private JTextField tfUserName;
	private JPasswordField pwdField;
	private JButton btnLogin;
	private JButton btnClose;
	private JLabel labelLoginInfo;
	private int loginTimes = 0;//登陆次数
	private ReaderAdmin readerBLL = new ReaderAdmin();
	public static Reader reader = null;//登陆用户信息，可用于整个程序
	public Login() {
		setSize(new Dimension(350, 250));
		setResizable(false);
		setTitle("\u957F\u6C5F\u5927\u5B66\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++loginTimes;
				String errorMsg = "";
				int rdId = -1;
				try
				{
					rdId = Integer.valueOf(tfUserName.getText().trim());
				}
				catch(NumberFormatException e)
				{
					errorMsg = "用户名无效";
					tfUserName.requestFocus();//获取焦点
				}
				if(rdId != -1)
				{
					reader = readerBLL.getReader(rdId);
					if(reader == null)
					{
						errorMsg = "用户名无效";
						tfUserName.requestFocus();
					}
					else if(reader.getRdPwd().equals(
							new String(pwdField.getPassword()).trim()))
					{
						loadMainGUI();
					}
					else
					{
						errorMsg = "密码有误";
						pwdField.requestFocus();//聚焦
					}
				}
				if(errorMsg.length() > 0)
					labelLoginInfo.setText(errorMsg);
			}
		});

		
		btnLogin.setBounds(59, 150, 85, 27);
		getContentPane().add(btnLogin);
		
		JButton btnClose = new JButton("\u9000\u51FA");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();//关闭当前窗体
				
			}
		});

		btnClose.setOpaque(false);
		btnClose.setBounds(171, 150, 85, 27);
		getContentPane().add(btnClose);
		
		JLabel label = new JLabel("\u7528\u6237\u7F16\u53F7:");
		label.setBounds(59, 57, 72, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u5BC6\u7801:");
		label_1.setBounds(59, 98, 72, 18);
		getContentPane().add(label_1);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(145, 54, 111, 24);
		getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(145, 95, 111, 24);
		getContentPane().add(pwdField);
		
		labelLoginInfo = new JLabel("");
		labelLoginInfo.setToolTipText("");
		labelLoginInfo.setBounds(124, 129, 101, 18);
		getContentPane().add(labelLoginInfo);

		
		
	}
/*	private void addButtonClickEventHandlers()
	{
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();//关闭当前窗体
			}
		});	
	}*/
	protected void loadMainGUI() 
	{
		this.dispose();
		Main mainGUI = new Main();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null);
		mainGUI.setVisible(true);
	}
	public void start()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		Login login = new Login();
		login.start();
	}
	}