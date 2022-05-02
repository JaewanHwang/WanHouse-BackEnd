package com.ssafy.happyhouse.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private int no;
	private String title;
	private String content;
	private Timestamp lastModified;
	private String memberId;
}
