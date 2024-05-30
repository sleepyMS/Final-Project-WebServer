package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public interface PostService {
    public ArrayList<PostDto> findAll();
    public PostDto findById(int id);
    public void delete(int idx);
    public PostDto save(PostDto post);

    public int count();
    boolean increaseLikes(int idx); // 좋아요 증가 메서드 수정
}