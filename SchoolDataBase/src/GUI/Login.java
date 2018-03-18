package GUI;
/*
 * 1.�������
 * 2.���캯��
 * 3.��Ӧ����
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
	/**
	 * �û���¼
	 */
	private static final long serialVersionUID = 1L;

	//�������
	JPanel jp1,jp2,jp3,jp4;//���
	JLabel jlb1,jlb2,jlb3;//��ǩ
	JButton jb1,jb2;//��ť
	JRadioButton jr1,jr2;ButtonGroup jb;//��ѡ��ť
	JTextField jtf;//�ı�
	JPasswordField jpf;//����
	
	//�趨�û���������
	String userword;
	String pwd;
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public static void main(String[] args)
    {
		Login win= new Login();
        return;
    }
	//���캯��
	public Login()
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
		jp2 = new JPanel();//���������
		jp3 = new JPanel();//Ȩ�����
		jp4 = new JPanel();//��½/���ð�ť���
		//������ǩ
		jlb1=new JLabel("�û���");
		jlb2=new JLabel("��    ��");
		jlb3=new JLabel("Ȩ    ��");
		//������ť
		jb1=new JButton("��¼");
		jb2=new JButton("����");
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//�����ı���
		jtf=new JTextField(15);
		//���������
		jpf=new JPasswordField(15);
		//���õ�ѡ��ť
		jr1 = new JRadioButton("��ʦ");
		jr2 = new JRadioButton("ѧ��");
		//ͨ����ť�����õ�ǰֻ��ѡ��һ��
		jb = new ButtonGroup();
		jb.add(jr2);
		jb.add(jr1);
	
		//���ò��ֹ���
		this.setLayout(new GridLayout(4, 1));//����ʽ����
			
		//����������
		jp1.add(jlb1);
		jp1.add(jtf);
			
		jp2.add(jlb2);
		jp2.add(jpf);
		
		jp3.add(jlb3);
		jp3.add(jr1);
		jp3.add(jr2);
		
		jp4.add(jb1);
		jp4.add(jb2);
		
		//���뵽JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
			
		//���ô���
		this.setTitle("�û���¼");//�����ǩ
		this.setSize(300, 200);//�����С
		this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳��ر�JFrame
		this.setVisible(true);//��ʾ����
			
		//��������
		this.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="��¼")
		{
			//���ѡ�н�ʦ��¼
			if(jr1.isSelected())
			{
				//���������
				try
				{
					String sql = "select * from Teacher";
					ps=dbConn.prepareStatement(sql);		
					//ResultSet�����,���԰�ResultSet���ɷ���һ�ű��еĽ����
					rs=ps.executeQuery();
					//�ж��Ƿ��½�ɹ�
					if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else if(jtf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else if(jpf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						boolean flag = false;
						while(rs.next())
						{
							userword=rs.getString(1);
							pwd=rs.getString(5);
							if(userword.equals(jtf.getText())&&pwd.equals(jpf.getText()))
							{
								//System.out.println("��¼�ɹ�");
								flag = true;
								JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
								clear();
								//�رյ�ǰ����
								dispose();
								//����һ���½���
								TeacherChoose tea = new TeacherChoose();
							}
						}
						if(flag == false)
						{
							JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
							//��������
							clear();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(jr2.isSelected())//ѧ���ڵ�¼ϵͳ
			{
				//���������
				try 
				{
					String sql = "select * from Student";
					ps=dbConn.prepareStatement(sql);
					//ResultSet�����,��ҿ��԰�ResultSet���ɷ���һ�ű��еĽ����
					rs=ps.executeQuery();
					//�ж��Ƿ��½�ɹ�
					if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else if(jtf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else if(jpf.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						boolean flag = false;
						while(rs.next())
						{
							userword=rs.getString(1);
							pwd=rs.getString(6);
							if(userword.equals(jtf.getText())&&pwd.equals(jpf.getText()))
							{
								//System.out.println("��¼�ɹ�");
								flag = true;
								JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
								clear();
								//�رյ�ǰ����
								dispose();
								//����һ���½���
								StudentChoose stu = new StudentChoose(jtf.getText());
							}
						}
						if(flag == false)
						{
							JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
							//��������
							clear();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getActionCommand()=="����")
		{
			clear();
		}			
		
	}
	//����ı���������
	public void clear()
	{
		jtf.setText("");
		jpf.setText("");
	}
}

