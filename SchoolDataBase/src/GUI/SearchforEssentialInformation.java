package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchforEssentialInformation extends JFrame implements ActionListener{

	/**
	 * ��ѯĳλѧ���Ļ�����Ϣ
	 */
	private static final long serialVersionUID = 1L;
	//�������
	JPanel jp1,jp2,jp3,jp4;//���
	JButton jb1;//��ť
	JRadioButton jr1,jr2,jr3;ButtonGroup jb;//��ѡ��ť
	JTextField jtf1,jtf2,jtf3;//�ı�
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	private JTable tab;
	
	//���캯��
	public SearchforEssentialInformation(){
		
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
		jp2 = new JPanel();//���������
		jp3 = new JPanel();//רҵ���
		jp4 = new JPanel();//��ѯ������Ϣ���
		
		//������ť
		jb1=new JButton("��ѯ������Ϣ");
		//���ü���
		jb1.addActionListener(this);
		
		//�����ı���
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		
		//���õ�ѡ��ť
		jr1 = new JRadioButton("ѧ��");
		jr2 = new JRadioButton("����");
		jr3 = new JRadioButton("רҵ");
		//ͨ����ť�����õ�ǰֻ��ѡ��һ��
		jb = new ButtonGroup();
		jb.add(jr1);
		jb.add(jr2);
		jb.add(jr3);
		
		//���ò��ֹ���
		this.setLayout(new GridLayout(4, 1));//����ʽ����
		
		//����������
		jp1.add(jr1);
		jp1.add(jtf1);
		
		jp2.add(jr2);
		jp2.add(jtf2);
		
		jp3.add(jr3);
		jp3.add(jtf3);
		
		jp4.add(jb1);
		
		//���뵽JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
					
		//���ô���
		this.setTitle("��ѯ������Ϣ");//�����ǩ
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
		if(e.getActionCommand()=="��ѯ������Ϣ")
		{
			if(jr1.isSelected())//ͨ��ѧ�Ų�ѯ������Ϣ
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor " + 
							"FROM Student " + 
							"WHERE Sno =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf1.getText());
						rs=ps.executeQuery();
						JFrame jf = new JFrame("������Ϣ");
						//����һ���������
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 100);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			else if(jr2.isSelected())//ͨ��������ѯ������Ϣ
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor " + 
							"FROM Student " + 
							"WHERE Sname =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf2.getText());
						rs=ps.executeQuery();
						
						JFrame jf = new JFrame("������Ϣ");
						//����һ���������
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 100);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			else//ͨ��רҵ��ѯ������Ϣ
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Mname " + 
							"FROM Student_Major " + 
							"WHERE Mname =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf3.getText());
						rs=ps.executeQuery();
						
						JFrame jf = new JFrame("������Ϣ");
						//����һ���������
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 500);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
			
	}
}
