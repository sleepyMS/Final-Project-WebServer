package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;
import java.util.List;
import java.util.Map;

public interface PostService {
    List<PostDto> findAllByCategory(String category);
    PostDto findById(String category, int id);
    void delete(String category, int idx);
    PostDto save(PostDto post);
    int count();
    boolean increaseLikes(String category, int idx);
    boolean decreaseLikes(String category, int idx);
    boolean isAlreadyLiked(String category, int idx);

    // 모든 카테고리의 게시글을 반환하는 메서드 추가
    Map<String, List<PostDto>> findAllPosts();
}
