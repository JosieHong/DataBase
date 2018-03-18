package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class AddGrades extends JFrame implements ActionListener{
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//�������
	JPanel jp1,jp2,jp3,jp4;//���
	JLabel jlb1,jlb2,jlb3;//��ǩ
	JTextField jtf1,jtf2,jtf3;//�ı�
	JButton jb1;//��ť
	
	//���캯��
	public AddGrades()
	{
		//�������ݿ�
	    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=SchoolDB";
	    String userName = "sa";
	    String userPwd = "123456";
	    try
	    {
	        Class.forName(driverName);
	        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	        //System.out.println("�������ݿ�ɹ�");
	     }
	     catch (Exception e)
	     {
	        e.printStackTrace();
	        System.out.print("����ʧ��");
	     }
	    
		//�������
		jp1 = new JPanel();//ѧ�����
		jp2 = new JPanel();//�γ̺����
		jp3 = new JPanel();//�ɼ����
		jp4 = new JPanel();//¼�밴ť���
		
		//������ǩ
		jlb1 = new JLabel("ѧ        ��");
		jlb2 = new JLabel("��  ��  ��");
		jlb3 = new JLabel("��        ��");
		
		//������ť
		jb1=new JButton("¼��");
		//���ü���
		jb1.addActionListener(this);
		
		//�����ı���
		jtf1=new JTextField(11);
		jtf2=new JTextField(5);
		jtf3=new JTextField(3);
		
		//���ò��ֹ���
		this.setLayout(new GridLayout(4, 1));//����ʽ����
		
		//����������
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jtf2);
		
		jp3.add(jlb3);
		jp3.add(jtf3);
		
		jp4.add(jb1);
		
		//���뵽JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
					
		//���ô���
		this.setTitle("¼��ɼ�");//�����ǩ
		this.setSize(300, 200);//�����С
		this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame
		this.setVisible(true);//��ʾ����
			
		//��������
		this.setResizable(false);
	}
	
	//��Ӧ����
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="¼��")
		{
			try {
				String sql = "INSERT INTO Grades (Sno,Lno,Grade) VALUES (?, ?, ?)";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				ps.setString(3, jtf3.getText());
				if(ps.executeUpdate()==1)
				{
					JOptionPane.showMessageDialog(null,"��ӳɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//����ı���������
	public void clear()
	{
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
	}

}
