package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService  {

    private ArrayList<PostDto> db = new ArrayList();

    public PostServiceImpl() {
        System.out.println("PostServiceImpl 객체 생성");
        db.add(new PostDto(1, "ISTJ준형", "집에서 시간 때우는 법", "잠을 많이 잔다." ,0));
        db.add(new PostDto(2, "ENFP철수", "축제 즐기는 방법", "부어라 마셔라", 0));
        db.add(new PostDto(3, "ESTJ영희", "조원을 통솔하는 방법", "강하게 말한다", 0));    }

    @Override
    public ArrayList<PostDto> findAll() {
        return db;
    }

    @Override
    public PostDto findById(int idx) {
        PostDto find = db.stream().filter(p -> p.getIdx() == idx).findAny().get();
        return find;
    }

    @Override
    public void delete(int idx) {
        db.remove(db.stream().filter(p -> p.getIdx() == idx).findAny().get());
    }

    @Override
    public PostDto save(PostDto post) {
        if ( post.getIdx() == -1) {
            int idx = db.get(db.size()-1).getIdx() + 1;
            post.setIdx(idx);
            db.add(post);
            return post;
        } else {
            PostDto temp = db.stream().filter(p -> p.getIdx() == post.getIdx()).findAny().get();
            temp.setNickname((post.getNickname()));
            temp.setTitle(post.getTitle());
            temp.setContent(post.getContent());
            return temp;
        }
    }

    @Override
    public int count() {
        return 0;
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