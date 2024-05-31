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
    private int boardId; // 새로운 필드 추가
    private String boardName; // 새로운 필드 추가

    public String getBoardName() {
        // 단순히 예시로 게시글의 제목을 게시판 이름으로 사용합니다.
        return this.title;
    }
}
