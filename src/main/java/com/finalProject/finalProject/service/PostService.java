package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    PostDto findById(int idx);
    void delete(int idx);
    PostDto save(PostDto post);
    int count();
    boolean increaseLikes(int idx);

}
