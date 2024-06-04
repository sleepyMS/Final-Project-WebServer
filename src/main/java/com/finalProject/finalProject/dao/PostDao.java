package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;

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
}
