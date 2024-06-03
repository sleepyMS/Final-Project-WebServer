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
    public ArrayList<PostDto> findAll3() {
        return postDao.findAll3();
    }

    @Override
    public ArrayList<PostDto> findAll4() {
        return postDao.findAll4();
    }

    @Override
    public PostDto findById(int category, int idx) {
        return postDao.findById(category, idx);
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
    public PostDto save3(PostDto post) {
        if (post.getIdx() == -1) {
            post.setIdx(postDao.getLastIdx(3) + 1); // P와 J 게시판의 마지막 idx를 가져와서 새로운 idx를 설정
            return postDao.insertPost(post); // P와 J 게시판에 새로운 게시글 추가
        } else {
            return postDao.updatePost(post);
        }
    }

    @Override
    public PostDto save4(PostDto post) {
        if (post.getIdx() == -1) {
            post.setIdx(postDao.getLastIdx(4) + 1); // N와 S 게시판의 마지막 idx를 가져와서 새로운 idx를 설정
            return postDao.insertPost(post); // N와 S 게시판에 새로운 게시글 추가
        } else {
            return postDao.updatePost(post);
        }
    }


    @Override
    public int count() {
        return postDao.count();
    }

    @Override
    public boolean increaseLikes(int category, int idx) {
        return postDao.increaseLikes(category, idx);
    }

    @Override
    public boolean decreaseLikes(int category, int idx) {
        return postDao.decreaseLikes(category, idx);
    }

    @Override
    public boolean isAlreadyLiked(int category, int idx) {
        return postDao.isAlreadyLiked(category, idx);
    }
}
