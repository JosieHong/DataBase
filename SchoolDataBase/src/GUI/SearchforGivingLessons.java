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

public class SearchforGivingLessons extends JFrame implements ActionListener {
	/**
	 * ��ѯ�ον�ʦ
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2;//���
	JButton jb1;//��ť
	JTextField jtf1;//�ı�
	JLabel jlb1;//��ǩ
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//���캯��
		public SearchforGivingLessons() {
			
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
			
			//������ǩ
			jlb1 = new JLabel("ѧ��");
			
			//������ť
			jb1=new JButton("��ѯ�ον�ʦ");
	
			//���ü���
			jb1.addActionListener(this);
			
			//�����ı���
			jtf1=new JTextField(11);
			
			//���ò��ֹ���
			this.setLayout(new GridLayout(2, 1));//����ʽ����
			
			//����������
			jp1.add(jlb1);
			jp1.add(jtf1);
			
			jp2.add(jb1);
			
			//���뵽JFrame
			this.add(jp1);
			this.add(jp2);
			
			//���ô���
			this.setTitle("��ѯ�ον�ʦ");//�����ǩ
			this.setSize(300, 100);//�����С
			this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame
			this.setVisible(true);//��ʾ����
						
			//��������
			this.setResizable(false);
		}

		//��Ӧ����
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand()=="��ѯ�ον�ʦ")
			{
				try {
					String sql = "SELECT Sno, Sname, Tname, Lno " + 
							"FROM Student, Teacher, GivingLesson " + 
							"WHERE Student.Sno =? AND " + 
							"			  GivingLesson.Cno = LEFT(Student.Sno, 7) AND " + 
							"			  GivingLesson.Tno = Teacher.Tno;";
					ps=dbConn.prepareStatement(sql);
					ps.setString(1, jtf1.getText());
					rs=ps.executeQuery();
					JFrame jf = new JFrame("ѡ��������ον�ʦ");
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
