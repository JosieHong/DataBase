package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import java.util.Vector;

public class StudentChoose extends JFrame implements ActionListener
{
	/**
	 * ѧ������ѡ��
	 */
	private static final long serialVersionUID = 1L;
	
	//ѧ��ѧ�ţ�����֮�����
	private static String useword = null;
	
	//�������
	JPanel jp1;//���
	JButton jb1,jb2;//��ť
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//��ʾ��ѯ���
	//private JTable tab;
	
	//���캯��
	public StudentChoose(String key)
	{
		//��ʼ��ѧ��ѧ��
		useword = key;
		
		//�������ݿ�
		Login win= new Login();
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
		jb1=new JButton("��ѯ������Ϣ");
		jb2=new JButton("��ѯ���гɼ�");
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);	
		//����������
		jp1.add(jb1);
		jp1.add(jb2);
		//���뵽JFrame
		this.add(jp1);
		//���ô���
		this.setTitle("ѧ��"+useword);//�����ǩ
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
		
		if(e.getActionCommand()=="��ѯ������Ϣ")
		{
			try {
				System.out.println("��ѧ��ѧ��Ϊ\t"+useword);
				String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor FROM Student WHERE Sno =?";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, useword);
				rs=ps.executeQuery();
				
				JFrame jf = new JFrame("������Ϣ");
				//����һ���������
				JPanel jp = new JPanel();
				while(rs.next())
				{
					System.out.println(rs.getString(1));
					JLabel jl = new JLabel(rs.getString(1));
					jp.add(jl);
				}
				jf.add(jp);
				//jf.setSize(width, height)
				//jf.setLocation(x, y)
				jf.setBounds(200, 200, 300, 300);
				jf.setVisible(true);
				
				/*int count = 0;
				while(rs.next()){
				count++;
				}
				rs=ps.executeQuery();
				
				// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
				Object[][] info = new Object[count][5];
				count = 0;
				while(rs.next()){
				info[count][0] = rs.getString(1);
				info[count][1] = rs.getString(2);
				info[count][2] = rs.getString(3);
				info[count][3] = rs.getString(4);
				info[count][4] = rs.getString(5);
				count++;
				}
				// �����ͷ
				String[] title = {"ѧ��","����","�Ա�","��������","רҵ"};
				// ����JTable
				this.tab = new JTable(info,title);
				this.tab.getTableHeader();
				
		        this.setTitle("������Ϣ");  
		        this.setSize(260, 150);
		        this.setLocation(300, 200);  
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        this.setResizable(false);  
		        this.setVisible(true);*/
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand()=="��ѯ���гɼ�")
		{
			
		}
	}

}
