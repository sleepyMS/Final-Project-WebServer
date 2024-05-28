package com.finalProject.finalProject.dto;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private int num;
    private String nickname;
    private String title;
    private String content;
    // 좋아요 수를 위한 필드 추가
    private int likes;
}
