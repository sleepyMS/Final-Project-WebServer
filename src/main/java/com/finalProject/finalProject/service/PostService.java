package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;
import java.util.List;

public interface PostService {
    List<PostDto> findAllByCategory(int category);
    PostDto findById(int category, int id);
    void delete(int category, int idx);
    PostDto save(PostDto post);
    int count();
    boolean increaseLikes(int category, int idx);
    boolean decreaseLikes(int category, int idx);
    boolean isAlreadyLiked(int category, int idx);
}
