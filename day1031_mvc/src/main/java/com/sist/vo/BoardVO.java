package com.sist.vo;

import java.util.Date;

import lombok.Data;


@Data
public class BoardVO {
	private int no;
	private String writer,pwd,title,content;
	private Date regdate;
	private int hit;
	private String fname;
	private int b_ref, b_step, b_level;
}
