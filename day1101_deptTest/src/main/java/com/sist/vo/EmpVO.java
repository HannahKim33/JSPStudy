package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int eno;
	private String ename;
	private int dno, salary, comm;
	private Date hiredate;
	private String phone, addr;
	private int mgr;
	private String job, email, jumin;
}
