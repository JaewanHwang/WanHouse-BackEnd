package com.ssafy.happyhouse.model.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int commentNo;
    private int boardNo;
    private String comment;
    private int like;
    private String userId;
}
