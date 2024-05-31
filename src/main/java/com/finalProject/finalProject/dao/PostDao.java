package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public interface PostDao {
    public ArrayList<PostDto> findAll();
    public PostDto findPostById(int id);
    public void delete(int idx);
    public PostDto insertPost(PostDto post);


    public int count();
    boolean increaseLikes(int idx); // 좋아요 증가 메서드 수정
}
