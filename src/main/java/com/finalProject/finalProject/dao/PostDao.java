package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostDao {
    Map<String, List<PostDto>> findAllPosts();
    List<PostDto> findPostsByCategory(String category); // 메서드 이름 변경
    PostDto findById(String category, int idx);
    void delete(String category, int idx);
    PostDto insertPost(PostDto post);
    PostDto updatePost(PostDto post);
    int getLastIdx(String category);
    int count();
    boolean increaseLikes(String category, int idx);
    boolean decreaseLikes(String category, int idx);
    boolean isAlreadyLiked(String category, int idx);
}
