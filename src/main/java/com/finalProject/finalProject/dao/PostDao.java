package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public interface PostDao {
    ArrayList<PostDto> findAll();
    ArrayList<PostDto> findAll2();
    PostDto findById(int id);
    void delete(int category,int idx);
    PostDto insertPost(PostDto post);
    int count();
    boolean increaseLikes(int idx);
    boolean decreaseLikes(int idx);
    boolean isAlreadyLiked(int idx);
    int getLastIdx(int category);
    PostDto updatePost(PostDto post);
}
