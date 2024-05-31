package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.PostDao;
import com.finalProject.finalProject.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public ArrayList<PostDto> findAll() {
        return postDao.findAll();
    }

    @Override
    public PostDto findById(int idx) {
        return postDao.findById(idx);
    }

    @Override
    public void delete(int idx) {
        postDao.delete(idx);
    }

    @Override
    public PostDto save(PostDto post) {
        if (post.getIdx() == -1) {
            int idx = postDao.getLastIdx() + 1;
            post.setIdx(idx);
            return postDao.insertPost(post);
        } else {
            return postDao.updatePost(post);
        }
    }

    @Override
    public int count() {
        return postDao.count();
    }

    @Override
    public boolean increaseLikes(int idx) {
        return postDao.increaseLikes(idx);
    }
}
