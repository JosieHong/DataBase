SELECT Sno,Sname,Ssex,Sbirth,Smajor --��ѧ�Ų������Ϣ
FROM Student
WHERE Sno = '15030199001';

SELECT Sno,Sname,Ssex,Sbirth,Smajor --�������������Ϣ
FROM Student
WHERE Sname = '����';

SELECT Sno,Sname,Ssex,Sbirth,Mname --��רҵ�������Ϣ
FROM Student_Major
WHERE Mname = '�������ѧ�뼼��';