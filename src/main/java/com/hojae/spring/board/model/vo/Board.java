package com.hojae.spring.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Board {

	private int no;
	private String title;
	private String memberId;
	private String content;
	private Date regDate;
	private int readCount;
	
		
}

