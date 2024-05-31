package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public interface PostDao {
    ArrayList<PostDto> findAll();
    PostDto findById(int id);
    void delete(int idx);
    PostDto insertPost(PostDto post);
    int count();
    boolean increaseLikes(int idx);
    int getLastIdx(); // 추가된 메서드
    PostDto updatePost(PostDto post); // 추가된 메서드
}
