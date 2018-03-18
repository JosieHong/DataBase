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

public class AddStudents extends JFrame implements ActionListener{
	
	/**
	 * ¼��һλѧ������Ϣ
	 */
	private static final long serialVersionUID = 1L;
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//�������
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;//���
	JLabel jlb1,jlb2,jlb3,jlb4,jlb5;//��ǩ
	JTextField jtf1,jtf2,jtf3;//�ı�
	JButton jb1;//��ť
	JRadioButton jr1,jr2;
	ButtonGroup jb;//��ѡ��ť
	
	JComboBox<String> jcb1,jcb2;//������
	
	//���캯��
	public AddStudents()
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
		jp2 = new JPanel();//���������
		jp3 = new JPanel();//�Ա����
		jp4 = new JPanel();//�����������
		jp5 = new JPanel();//רҵ���
		jp6 = new JPanel();//��½��ť���
		
		//������ǩ
		jlb1 = new JLabel("ѧ        ��");
		jlb2 = new JLabel("��        ��");
		jlb3 = new JLabel("��        ��");
		jlb4 = new JLabel("��������");
		jlb5 = new JLabel("רҵ���");
		
		//������ť
		jb1=new JButton("¼��");
		//���ü���
		jb1.addActionListener(this);
		
		//�����ı���
		jtf1=new JTextField(15);
		jtf2=new JTextField(5);
		jtf3=new JTextField(15);
		
		//���õ�ѡ��ť
		jr1 = new JRadioButton("��");
		jr2 = new JRadioButton("Ů");
		//ͨ����ť�����õ�ǰֻ��ѡ��һ��
		jb = new ButtonGroup();
		jb.add(jr2);
		jb.add(jr1);
		
		//����������
		String[] arr1=new String[70];//����ʱ������,������ʹ��ǰҪ��ʼ��  
        for(int i=0;i<70;i++){
            arr1[i] = i+1950+"";
        }         
        jcb1 = new JComboBox<String>(arr1);
        String [] arr2 = new String[12];  
        for(int i=0;i<12;i++){  
            arr2[i]=i+1+"";
        }  
        jcb2 = new JComboBox<String>(arr2);
		
		//���ò��ֹ���
		this.setLayout(new GridLayout(6, 1));//����ʽ����
		
		//����������
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jtf2);
		
		jp3.add(jlb3);
		jp3.add(jr1);
		jp3.add(jr2);
		
		jp4.add(jlb4);
		jp4.add(jcb1);
		jp4.add(jcb2);
		
		jp5.add(jlb5);
		jp5.add(jtf3);
		
		jp6.add(jb1);
		
		//���뵽JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
					
		//���ô���
		this.setTitle("¼��ѧ��");//�����ǩ
		this.setSize(300, 280);//�����С
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
				String sql = "INSERT INTO Student (Sno,Sname,Ssex,Sbirth,Smajor,Skey) VALUES (?, ?, ?, ?, ?, ?)";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				if(jr1.isSelected())
					ps.setString(3, "��");
				else
					ps.setString(3, "Ů");
				ps.setString(4, jcb1.getSelectedItem().toString()+jcb2.getSelectedItem().toString());
				ps.setString(5, jtf3.getText());
				ps.setString(6, jtf1.getText());
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
}
