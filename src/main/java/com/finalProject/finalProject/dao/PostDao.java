package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;

public interface PostDao {
    List<PostDto> findAll();
    PostDto findById(int idx);
    void delete(int idx);
    PostDto insertPost(PostDto post);
    int count();
    boolean increaseLikes(int idx);
    int getLastIdx();
    PostDto updatePost(PostDto post);
}
