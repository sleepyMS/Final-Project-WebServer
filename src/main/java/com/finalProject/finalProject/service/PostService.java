package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;
import java.util.ArrayList;
import java.util.List;

public interface PostService {
    ArrayList<PostDto> findAll();
    ArrayList<PostDto> findAll2();
    ArrayList<PostDto> findAll3();
    ArrayList<PostDto> findAll4();
    PostDto findById(int category, int id);
    void delete(int category, int idx);
    PostDto save1(PostDto post);
    PostDto save2(PostDto post);
    PostDto save3(PostDto post);
    PostDto save4(PostDto post);
    int count();
    boolean increaseLikes(int category, int idx);
    boolean decreaseLikes(int category, int idx);
    boolean isAlreadyLiked(int category, int idx);
}
