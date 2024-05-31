package com.finalProject.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CommentDto {
    private long idx;
    private int postIdx;
    private int userIdx;
    private String mbti;
    private String nick;
    private String content;
}
