package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.PostDao;
import com.finalProject.finalProject.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public List<PostDto> findAll() {
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

    @Override
    public void createBoard(String title) {
        // 게시판 생성 로직
    }

    @Override
    public List<PostDto> getPostsByBoardId(int boardId) {
        // 해당 게시판의 게시글 조회 로직
        return postDao.getPostsByBoardId(boardId);
    }



    @Override
    public List<PostDto> getRecentPosts() {
        List<PostDto> recentPosts = new ArrayList<>();
        List<PostDto> allPosts = postDao.findAll();
        int count = 0;
        for (int i = allPosts.size() - 1; i >= 0; i--) {
            recentPosts.add(allPosts.get(i));
            count++;
            if (count == 3) {
                break;
            }
        }
        return recentPosts;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postDao.findAll();
    }
}
