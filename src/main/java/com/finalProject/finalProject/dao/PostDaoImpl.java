package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class PostDaoImpl implements PostDao {
    private Map<Integer, List<PostDto>> db = new HashMap<>();

    // 생성자에서 초기 데이터 설정
    public PostDaoImpl() {
        System.out.println("PostDaoImpl 객체 생성");

        // E와 I 게시판 데이터 (카테고리 1)
        List<PostDto> db1 = new ArrayList<>();
        db1.add(new PostDto(1, "IIII준형", "집에서 시간 때우는 법", "잠을 많이 잔다.", 0, 1, 1));
        db1.add(new PostDto(2, "EEEE철수", "축제 즐기는 방법", "부어라 마셔라", 0, 2, 1));
        db1.add(new PostDto(3, "EEEE영희", "조원을 통솔하는 방법", "강하게 말한다", 0, 3, 1));
        db.put(1, db1);

        // T와 F 게시판 데이터 (카테고리 2)
        List<PostDto> db2 = new ArrayList<>();
        db2.add(new PostDto(1, "TTTT짱구", "논리적인 사고", "논리적으로 생각한다.", 0, 1, 2));
        db2.add(new PostDto(2, "FFFF훈이", "감정적인 소통", "감정적으로 소통한다.", 0, 2, 2));
        db2.add(new PostDto(3, "TTTT맹구", "효율적인 업무 처리", "효율적으로 처리한다.", 0, 3, 2));
        db.put(2, db2);

        // P와 J 게시판 데이터 (카테고리 3)
        List<PostDto> db3 = new ArrayList<>();
        db3.add(new PostDto(1, "PPPP진구", "계획따위 없어", "무계획이야 나는", 0, 1, 3));
        db3.add(new PostDto(2, "JJJJ비실", "오늘 뭐할까", "실천해보자", 0, 2, 3));
        db3.add(new PostDto(3, "JJJJ이슬", "몇시에 할지", "시간단위 생각", 0, 3, 3));
        db.put(3, db3);

        // N와 S 게시판 데이터 (카테고리 4)
        List<PostDto> db4 = new ArrayList<>();
        db4.add(new PostDto(1, "NNNN퉁퉁", "상상력 풍부", "만약에 ~한다면", 0, 1, 4));
        db4.add(new PostDto(2, "SSSS도라", "현실 직시해", "지금 나의 상태는?", 0, 2, 4));
        db4.add(new PostDto(3, "NNNN에몽", "내가 죽으면", "오늘 죽게된다면?", 0, 3, 4));
        db.put(4, db4);
    }

    // 모든 게시글을 반환하는 메서드
    @Override
    public List<PostDto> findAllByCategory(int category) {
        return new ArrayList<>(db.getOrDefault(category, new ArrayList<>()));
    }

    // 특정 idx에 해당하는 게시글을 반환하는 메서드
    @Override
    public PostDto findById(int category, int idx) {
        return db.getOrDefault(category, new ArrayList<>()).stream()
                .filter(post -> post.getIdx() == idx)
                .findAny()
                .orElse(null);
    }

    // 특정 idx에 해당하는 게시글을 삭제하는 메서드
    @Override
    public void delete(int category, int idx) {
        List<PostDto> posts = db.get(category);
        if (posts != null) {
            posts.removeIf(post -> post.getIdx() == idx);
        }
    }




    // 새로운 게시글을 추가하거나 업데이트하는 메서드
    @Override
    public PostDto insertPost(PostDto post) {
        int category = post.getCategory();
        List<PostDto> posts = db.computeIfAbsent(category, k -> new ArrayList<>());
        if (post.getIdx() == -1) {
            post.setIdx(getLastIdx(category) + 1);
        }
        posts.add(post);
        return post;
    }

    @Override
    public PostDto updatePost(PostDto post) {
        int category = post.getCategory();
        List<PostDto> posts = db.get(category);
        if (posts != null) {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getIdx() == post.getIdx()) {
                    posts.set(i, post);
                    return post;
                }
            }
        }
        return null;
    }

    // 전체 게시글 수를 반환하는 메서드
    @Override
    public int count() {
        return db.values().stream().mapToInt(List::size).sum();
    }

    // 특정 게시글의 좋아요 수를 증가시키는 메서드
    @Override
    public boolean increaseLikes(int category, int idx) {
        PostDto post = findById(category, idx);
        if (post != null && post.getLikes() == 0) {
            post.setLikes(1);
            return true;
        }
        return false;
    }

    // 특정 게시글의 좋아요 수를 감소시키는 메서드
    @Override
    public boolean decreaseLikes(int category, int idx) {
        PostDto post = findById(category, idx);
        if (post != null && post.getLikes() == 1) {
            post.setLikes(0);
            return true;
        }
        return false;
    }

    // 특정 게시글이 이미 좋아요를 눌렀는지 확인하는 메서드
    @Override
    public boolean isAlreadyLiked(int category, int idx) {
        PostDto post = findById(category,idx);
        return post != null && post.getLikes() == 1;
    }

    // 마지막 게시글의 idx를 반환하는 메서드
    @Override
    public int getLastIdx(int category) {
        List<PostDto> posts = db.get(category);
        return (posts == null || posts.isEmpty()) ? 0 : posts.get(posts.size() - 1).getIdx();
    }
}
