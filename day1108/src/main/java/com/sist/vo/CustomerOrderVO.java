package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerOrderVO {
	private int custid;
	private String name, address, phone;
	private int orderid, saleprice;
	private Date orderdate;
}
