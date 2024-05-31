// PostDaoImpl.java
package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {
    private ArrayList<PostDto> db = new ArrayList<>();

    public PostDaoImpl() {
        System.out.println("PostDaoImpl 객체 생성");
        db.add(new PostDto(1, "ISTJ준형", "집에서 시간 때우는 법", "잠을 많이 잔다.", 0, 1));
        db.add(new PostDto(2, "ENFP철수", "축제 즐기는 방법", "부어라 마셔라", 0, 2));
        db.add(new PostDto(3, "ESTJ영희", "조원을 통솔하는 방법", "강하게 말한다", 0, 3));
    }

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
    public PostDto insertPost(PostDto post) {
        db.add(post);
        return post;
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

    @Override
    public boolean decreaseLikes(int idx) {
        PostDto post = db.stream().filter(p -> p.getIdx() == idx).findAny().orElse(null);
        if (post != null && post.getLikes() == 1) {
            post.setLikes(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAlreadyLiked(int idx) {
        PostDto post = db.stream().filter(p -> p.getIdx() == idx).findAny().orElse(null);
        return post != null && post.getLikes() == 1;
    }

    @Override
    public int getLastIdx() {
        return db.isEmpty() ? 0 : db.get(db.size() - 1).getIdx();
    }

    @Override
    public PostDto updatePost(PostDto post) {
        PostDto existingPost = findById(post.getIdx());
        if (existingPost != null) {
            existingPost.setNickname(post.getNickname());
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            existingPost.setLikes(post.getLikes());
        }
        return existingPost;
    }
}
