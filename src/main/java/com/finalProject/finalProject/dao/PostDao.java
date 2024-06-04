package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;
import java.util.List;
import java.util.Map;

public interface PostDao {
    List<PostDto> findAllByCategory(int category);
    PostDto findById(int category, int idx);
    void delete(int category, int idx);
    PostDto insertPost(PostDto post);
    int count();
    boolean increaseLikes(int category, int idx);
    boolean decreaseLikes(int category, int idx);
    boolean isAlreadyLiked(int category, int idx);
    int getLastIdx(int category);
    PostDto updatePost(PostDto post);

    // 모든 카테고리의 게시글을 반환하는 메서드 추가
    Map<Integer, List<PostDto>> findAllPosts();
}
