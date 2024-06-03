package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PostDaoImpl implements PostDao {
    // E와 I 게시판 데이터를 저장하는 리스트
    private ArrayList<PostDto> db = new ArrayList<>();
    // T와 F 게시판 데이터를 저장하는 리스트
    private ArrayList<PostDto> db2 = new ArrayList<>();

    // 생성자에서 초기 데이터 설정
    public PostDaoImpl() {
        System.out.println("PostDaoImpl 객체 생성");
        // E와 I 게시판 데이터
        db.add(new PostDto(1, "IIII준형", "집에서 시간 때우는 법", "잠을 많이 잔다.", 0, 1, 1));
        db.add(new PostDto(2, "EEEE철수", "축제 즐기는 방법", "부어라 마셔라", 0, 2, 1));
        db.add(new PostDto(3, "EEEE영희", "조원을 통솔하는 방법", "강하게 말한다", 0, 3, 1));

        // T와 F 게시판 데이터
        db2.add(new PostDto(1, "TTTT짱구", "논리적인 사고", "논리적으로 생각한다.", 0, 1, 2));
        db2.add(new PostDto(2, "FFFF훈이", "감정적인 소통", "감정적으로 소통한다.", 0, 2, 2));
        db2.add(new PostDto(3, "TTTT맹구", "효율적인 업무 처리", "효율적으로 처리한다.", 0, 3, 2));
    }

    // 모든 게시글을 반환하는 메서드
    @Override
    public ArrayList<PostDto> findAll() {
        ArrayList<PostDto> allPosts = new ArrayList<>(db);
        return allPosts;
    }

    // T와 F 게시판의 모든 게시글을 반환하는 메서드
    @Override
    public ArrayList<PostDto> findAll2() {
        return new ArrayList<>(db2);
    }

    // 특정 idx에 해당하는 게시글을 반환하는 메서드
    @Override
    public PostDto findById(int idx) {
        PostDto post = db.stream().filter(p -> p.getIdx() == idx).findAny().orElse(null);
        if (post == null) {
            post = db2.stream().filter(q -> q.getIdx() == idx).findAny().orElse(null);
        }
        return post;
    }

    // 특정 idx에 해당하는 게시글을 삭제하는 메서드
    @Override
    public void delete(int idx) {
        boolean removed = db.removeIf(p -> p.getIdx() == idx);
        if (!removed) {
            db2.removeIf(q -> q.getIdx() == idx);
        }
    }


    // 새로운 게시글을 추가하거나 업데이트하는 메서드
    @Override
    public PostDto insertPost(PostDto post) {
        if (post.getIdx() == -1) {
            int lastIdx = getLastIdx(post.getCategory()); // 해당 카테고리의 마지막 인덱스를 가져옵니다.
            post.setIdx(lastIdx + 1); // 새로운 게시글의 인덱스 설정
        }
        if (post.getCategory() == 1) {
            db.add(post); // E와 I 게시판에 새로운 게시글 추가
        } else if (post.getCategory() == 2) {
            db2.add(post); // T와 F 게시판에 새로운 게시글 추가
        }
        return post;
    }


    // 전체 게시글 수를 반환하는 메서드
    @Override
    public int count() {
        return db.size() + db2.size();
    }

    // 특정 게시글의 좋아요 수를 증가시키는 메서드
    @Override
    public boolean increaseLikes(int idx) {
        PostDto post = findById(idx);
        if (post != null && post.getLikes() == 0) {
            post.setLikes(1);
            return true;
        }
        return false;
    }

    // 특정 게시글의 좋아요 수를 감소시키는 메서드
    @Override
    public boolean decreaseLikes(int idx) {
        PostDto post = findById(idx);
        if (post != null && post.getLikes() == 1) {
            post.setLikes(0);
            return true;
        }
        return false;
    }

    // 특정 게시글이 이미 좋아요를 눌렀는지 확인하는 메서드
    @Override
    public boolean isAlreadyLiked(int idx) {
        PostDto post = findById(idx);
        return post != null && post.getLikes() == 1;
    }

    // 마지막 게시글의 idx를 반환하는 메서드
    @Override
    public int getLastIdx(int category) {
        if (category == 1) {
            return db.isEmpty() ? 0 : db.get(db.size() - 1).getIdx();
        } else if (category == 2) {
            return db2.isEmpty() ? 0 : db2.get(db2.size() - 1).getIdx();
        } else {
            // 처리할 수 없는 카테고리인 경우 0을 반환하거나 예외 처리를 수행할 수 있습니다.
            return 0;
        }
    }


    // 특정 게시글을 업데이트하는 메서드
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
