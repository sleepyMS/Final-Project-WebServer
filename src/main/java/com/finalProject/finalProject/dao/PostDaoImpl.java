package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;

import java.util.ArrayList;

public class PostDaoImpl implements PostDao {
    private ArrayList<PostDto> db = new ArrayList<>();

    public PostDaoImpl() {
        System.out.println("PostDaoImpl 객체 생성");
        db.add(new PostDto(1, "ISTJ준형", "집에서 시간 때우는 법", "잠을 많이 잔다.", 0, "user1"));
        db.add(new PostDto(2, "ENFP철수", "축제 즐기는 방법", "부어라 마셔라", 0, "user2"));
        db.add(new PostDto(3, "ESTJ영희", "조원을 통솔하는 방법", "강하게 말한다", 0, "user3"));
    }

    @Override
    public ArrayList<PostDto> findAll() {
        return null;
    }

    @Override
    public PostDto findById(int id) {
        return null;
    }

    @Override
    public void delete(int idx) {

    }

    @Override
    public PostDto insertPost(PostDto post) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean increaseLikes(int idx) {
        return false;
    }
}
