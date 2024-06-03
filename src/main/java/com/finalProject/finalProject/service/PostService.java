package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;
import java.util.ArrayList;

public interface PostService {
    ArrayList<PostDto> findAll();
    ArrayList<PostDto> findAll2();
    PostDto findById(int id);
    void delete(int category, int idx);
    PostDto save1(PostDto post);
    PostDto save2(PostDto post);
    int count();
    boolean increaseLikes(int idx);
    boolean decreaseLikes(int idx);
    boolean isAlreadyLiked(int idx);
}
