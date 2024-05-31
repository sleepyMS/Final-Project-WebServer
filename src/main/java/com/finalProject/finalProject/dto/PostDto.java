package com.finalProject.finalProject.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private int idx;
    private String nickname;
    private String title;
    private String content;
    private int likes;
    private String userIdx; // userIndex 필드 추가
}