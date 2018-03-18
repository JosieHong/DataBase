package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TeacherChoose extends JFrame implements ActionListener
{
	/**
	 * ����ѡ��
	 */
	private static final long serialVersionUID = 1L;
	
	//�������
	JPanel jp1;//���
	JButton jb1,jb2,jb3,jb4,jb5,jb6;//��ť
		
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//���캯��
	public TeacherChoose()
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
		jp1 = new JPanel();//�û������
		//������ť
		jb1=new JButton("���ѧ����Ϣ");
		jb2=new JButton("¼��ɼ�");
		jb3=new JButton("��ѯ������Ϣ");
		jb4=new JButton("��ѯ�ɼ�");
		jb5=new JButton("��ѯ�ڿ����");
		jb6=new JButton("��ѯ�챻����ѧ��");
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		//����������
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(jb5);
		jp1.add(jb6);
		//���뵽JFrame
		this.add(jp1);
		//���ô���
		this.setTitle("��ʦ");//�����ǩ
		this.setSize(200, 240);//�����С
		this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame
		this.setVisible(true);//��ʾ����
						
		//��������
		this.setResizable(false);
	}
	
	//��Ӧ����
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="���ѧ����Ϣ")
		{
			AddStudents win = new AddStudents();
		}
		else if(e.getActionCommand()=="¼��ɼ�")
		{
			AddGrades win = new AddGrades();
		}
		else if(e.getActionCommand()=="��ѯ������Ϣ")
		{
			SearchforEssentialInformation win = new SearchforEssentialInformation();
		}
		else if(e.getActionCommand()=="��ѯ�ɼ�")
		{
			SearchforGrades win = new SearchforGrades();
		}
		else if(e.getActionCommand()=="��ѯ�ڿ����")
		{
			SearchforGivingLessons win = new SearchforGivingLessons();
		}
		else if(e.getActionCommand()=="��ѯ�챻����ѧ��")
		{
			try {
				String sql = "SELECT DISTINCT Student.Sno, Sname, RFailSUM.SumCredit, OFailSUM.SumCredit " + 
						"FROM RFailSUM, OFailSUM, Student " + 
						"WHERE (Student.Sno = RFailSUM.Sno AND " + 
						"				RFailSUM.SumCredit >=12 ) " + 
						"				OR " + 
						"				(Student.Sno = OFailSUM.Sno AND " + 
						"				OFailSUM.SumCredit >=17)";
				ps=dbConn.prepareStatement(sql);
				rs=ps.executeQuery();
				JFrame jf = new JFrame("�챻����ѧ��");
				//����һ���������
				JPanel jp = new JPanel();
				while(rs.next())
				{
					JLabel jl1 = new JLabel(rs.getString(1));
					JLabel jl2 = new JLabel(rs.getString(2));
					JLabel jl3 = new JLabel(rs.getString(3));
					JLabel jl4 = new JLabel(rs.getString(4));
					jp.add(jl1);
					jp.add(jl2);
					jp.add(jl3);
					jp.add(jl4);
				}
				jf.add(jp);
				jf.setBounds(200, 200, 300, 300);
				jf.setSize(220, 300);
				jf.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
