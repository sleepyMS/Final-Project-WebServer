package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public interface PostService {
    public ArrayList<PostDto> findAll();
    public PostDto findById(int id);
    public void delete(int idx);
    public PostDto save(PostDto movie);

    public int count();
}
