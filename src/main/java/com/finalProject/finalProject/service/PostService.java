package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    Map<String, List<PostDto>> findAllPostsReverse();
    List<PostDto> findPostsByCategory(String category); // 메서드 이름 변경
    List<PostDto> findPostsByUserId(int userId); // 메서드 이름 변경
    PostDto findById(String category, int idx);
    void delete(String category, int idx);
    PostDto save(PostDto post);
    int count();
    boolean increaseLikes(String category, int idx);
    boolean decreaseLikes(String category, int idx);
    boolean isAlreadyLiked(String category, int idx);
}
