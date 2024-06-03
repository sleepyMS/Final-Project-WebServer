package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

public interface PostDao {
    ArrayList<PostDto> findAll();
    ArrayList<PostDto> findAll2();
    ArrayList<PostDto> findAll3();
    ArrayList<PostDto> findAll4();
    PostDto findById(int category, int id);
    void delete(int category,int idx);
    PostDto insertPost(PostDto post);
    int count();
    boolean increaseLikes(int category, int id);
    boolean decreaseLikes(int category, int id);
    boolean isAlreadyLiked(int category, int id);
    int getLastIdx(int category);
    PostDto updatePost(PostDto post);
}
