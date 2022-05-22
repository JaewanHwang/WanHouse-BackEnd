package com.ssafy.happyhouse.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BoardDto {
	private int boardNo;
	private String title;
	private String content;
	private Timestamp lastModified;
	private String userId;
	private int hit;
	private List<CommentDto> comments;
}
