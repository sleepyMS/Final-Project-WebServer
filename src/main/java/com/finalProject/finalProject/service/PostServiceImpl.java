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
    public ArrayList<PostDto> findAll2() {
        return postDao.findAll2();
    }

    @Override
    public PostDto findById(int idx) {
        return postDao.findById(idx);
    }

    @Override
    public void delete(int category, int idx) {
        postDao.delete(category, idx);
    }

    @Override
    public PostDto save1(PostDto post) {
        if (post.getIdx() == -1) {
            post.setIdx(postDao.getLastIdx(1) + 1); // E와 I 게시판의 마지막 idx를 가져와서 새로운 idx를 설정
            return postDao.insertPost(post); // E와 I 게시판에 새로운 게시글 추가
        } else {
            return postDao.updatePost(post);
        }
    }

    @Override
    public PostDto save2(PostDto post) {
        if (post.getIdx() == -1) {
            post.setIdx(postDao.getLastIdx(2) + 1); // T와 F 게시판의 마지막 idx를 가져와서 새로운 idx를 설정
            return postDao.insertPost(post); // T와 F 게시판에 새로운 게시글 추가
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

    @Override
    public boolean decreaseLikes(int idx) {
        return postDao.decreaseLikes(idx);
    }

    @Override
    public boolean isAlreadyLiked(int idx) {
        return postDao.isAlreadyLiked(idx);
    }
}
