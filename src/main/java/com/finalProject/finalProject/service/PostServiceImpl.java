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
        return db;
    }

    @Override
    public PostDto findById(int idx) {
        return db.stream().filter(p -> p.getIdx() == idx).findAny().orElse(null);
    }

    @Override
    public void delete(int idx) {
        db.removeIf(p -> p.getIdx() == idx);
    }

    @Override
    public PostDto save(PostDto post) {
        if (post.getIdx() == -1) {

//            db의 마지막 인덱스를 가져와야함
//            int idx = db.isEmpty() ? 1 : db.get(db.size() - 1).getIdx() + 1;
            int idx = postDao.getLastIdx() + 1;
            post.setIdx(idx);

//            db.add(post);
            postDao.insertPost(post);

            return post;
        } else {
//            PostDto existingPost = db.stream().filter(p -> p.getIdx() == post.getIdx()).findAny().orElse(null);
            PostDto existingPost = postDao.findPostById(post.getIdx());

            if (existingPost != null) {
                existingPost.setNickname(post.getNickname());
                existingPost.setTitle(post.getTitle());
                existingPost.setContent(post.getContent());
                existingPost.setLikes(post.getLikes());
                // existingPost.setUserIndex(post.getUserIndex()); // userIndex는 수정하지 않음
            }
            return existingPost;
        }
    }

    @Override
    public int count() {
        return db.size();
    }

    @Override
    public boolean increaseLikes(int idx) {
        PostDto post = db.stream().filter(p -> p.getIdx() == idx).findAny().orElse(null);
        if (post != null && post.getLikes() == 0) {
            post.setLikes(1);
            return true;
        }
        return false;
    }
}
