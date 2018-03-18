package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchforGrades extends JFrame implements ActionListener {
	
	/**
	 * �ɼ���ѯ
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3,jp4;//���
	JButton jb1,jb2,jb3;//��ť
	JTextField jtf1;//�ı�
	JLabel jlb1;//��ǩ
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//���캯��
	public SearchforGrades() {
		
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
		jp2 = new JPanel();//��ѯ��ť���
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		//������ǩ
		jlb1 = new JLabel("ѧ��");
		
		//������ť
		jb1=new JButton("��ѯ���Ƴɼ�");
		jb2=new JButton("��ѯƽ���ɼ�");
		jb3=new JButton("��ѯ���޿�ƽ���ɼ�");
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		
		//�����ı���
		jtf1=new JTextField(11);
		
		//���ò��ֹ���
		this.setLayout(new GridLayout(4, 1));//����ʽ����
		
		//����������
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jb1);
		jp3.add(jb2);
		jp4.add(jb3);
		
		//���뵽JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
					
		//���ô���
		this.setTitle("��ѯ�ɼ�");//�����ǩ
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
			if(e.getActionCommand()=="��ѯ���Ƴɼ�")
			{
				try {
					String sql = "SELECT Grades.Sno, Student.Sname, Grades.Lno, Lesson.RorO,  Lesson.TeachingSemester, Grades.Grade " + 
							"FROM Grades, Lesson, Student " + 
							"WHERE Student.Sno = Grades.Sno AND " + 
							"			  Grades.Lno = Lesson.Lno AND " + 
							"			  Lesson.Mno = SUBSTRING ( Grades.Sno, 3 , 4) AND " + 
							"			  Grades.Sno =?";
					ps=dbConn.prepareStatement(sql);
					ps.setString(1, jtf1.getText());
					rs=ps.executeQuery();
					JFrame jf = new JFrame("���Ƴɼ�");
					//����һ���������
					JPanel jp = new JPanel();
					while(rs.next())
					{
						JLabel jl1 = new JLabel(rs.getString(1));
						JLabel jl2 = new JLabel(rs.getString(2));
						JLabel jl3 = new JLabel(rs.getString(3));
						JLabel jl4 = new JLabel(rs.getString(4));
						JLabel jl5 = new JLabel(rs.getString(5));
						JLabel jl6 = new JLabel(rs.getString(6));
						jp.add(jl1);
						jp.add(jl2);
						jp.add(jl3);
						jp.add(jl4);
						jp.add(jl5);
						jp.add(jl6);
					}
					jf.add(jp);
					jf.setBounds(200, 200, 300, 300);
					jf.setSize(400, 400);
					jf.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand()=="��ѯƽ���ɼ�")
			{
				try {
					String sql = "SELECT Grade_Credit.Sno, Student.Sname, SUM(Grade*Lcredit)/SUM(Lcredit) " + 
							"FROM Grade_Credit, Student " + 
							"WHERE Grade_Credit.Sno =? AND " + 
							"			Grade_Credit.Sno = Student.Sno " + 
							"GROUP BY Grade_Credit.Sno, Student.Sname";
					ps=dbConn.prepareStatement(sql);
					ps.setString(1, jtf1.getText());
					rs=ps.executeQuery();
					JFrame jf = new JFrame("ƽ���ɼ�");
					//����һ���������
					JPanel jp = new JPanel();
					while(rs.next())
					{
						JLabel jl1 = new JLabel(rs.getString(1));
						JLabel jl2 = new JLabel(rs.getString(2));
						JLabel jl3 = new JLabel(rs.getString(3));
						jp.add(jl1);
						jp.add(jl2);
						jp.add(jl3);
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
			else if(e.getActionCommand()=="��ѯ���޿�ƽ���ɼ�")
			{
				try {
					String sql = "SELECT Grade_rCredit.Sno, Student.Sname, SUM(Grade*Lcredit)/SUM(Lcredit) " + 
							"FROM Grade_rCredit, Student " + 
							"WHERE Grade_rCredit.Sno =? AND " + 
							"			Grade_rCredit.Sno = Student.Sno " + 
							"GROUP BY Grade_rCredit.Sno, Student.Sname";
					ps=dbConn.prepareStatement(sql);
					ps.setString(1, jtf1.getText());
					rs=ps.executeQuery();
					JFrame jf = new JFrame("���޿�ƽ���ɼ�");
					//����һ���������
					JPanel jp = new JPanel();
					while(rs.next())
					{
						JLabel jl1 = new JLabel(rs.getString(1));
						JLabel jl2 = new JLabel(rs.getString(2));
						JLabel jl3 = new JLabel(rs.getString(3));
						jp.add(jl1);
						jp.add(jl2);
						jp.add(jl3);
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
				
		}

}
