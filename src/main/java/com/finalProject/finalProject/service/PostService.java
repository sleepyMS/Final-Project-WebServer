package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    PostDto findById(int idx);
    void delete(int idx);
    PostDto save(PostDto post);
    int count();
    boolean increaseLikes(int idx);
    void createBoard(String title);
    List<PostDto> getPostsByBoardId(int boardId);

    List<String> getAllBoards(); // 모든 게시판 이름을 가져오는 메서드 추가
    List<PostDto> getRecentPosts(); // 최근 게시글 목록을 가져오는 메서드 추가

    List<PostDto> getAllPosts();
}
