CREATE TABLE Major --����רҵ��
(	Mno CHAR(4) PRIMARY KEY,
	Mname CHAR(20) NOT NULL
);

CREATE TABLE Student	--����ѧ����
(	Sno CHAR(11) PRIMARY KEY,
	Sname CHAR(6) NOT NULL,
	Ssex CHAR(2) CHECK (Ssex IN ('��','Ů')),
	Sbirth CHAR(8),
	Smajor CHAR(4) REFERENCES Major(Mno),
	Skey CHAR(11) NOT NULL
);

CREATE TABLE Teacher	--������ʦ��
(	Tno CHAR(11) PRIMARY KEY,
	Tname CHAR(6) NOT NULL,
	Tsex CHAR(2) CHECK (Tsex IN ('��','Ů')),
	Tbirth CHAR(8),
	Tkey CHAR(11) NOT NULL
);

CREATE TABLE Lesson --���γ̱�
(	Mno CHAR(4) REFERENCES Major(Mno),
	Lno CHAR(5) NOT NULL,
	Lname CHAR(20) NOT NULL,
	RorO CHAR(4) CHECK( RorO IN ('����','ѡ��')),
	TeachingSemester SMALLINT CHECK(TeachingSemester>=1 AND TeachingSemester<=8) NOT NULL,
	Lcredit SMALLINT NOT NULL,
	PRIMARY KEY(Mno, Lno)
);

CREATE TABLE GivingLesson --�����ڿα�
(	Tno CHAR(11) REFERENCES Teacher(Tno),
	Cno CHAR(7),
	Lno CHAR(5),
	PRIMARY KEY(Tno, Cno)
);

CREATE TABLE Grades --�����ɼ���
(	Sno CHAR(11) REFERENCES Student(Sno),
	Lno CHAR(5),
	Grade SMALLINT
	PRIMARY KEY(Sno, Lno)
);