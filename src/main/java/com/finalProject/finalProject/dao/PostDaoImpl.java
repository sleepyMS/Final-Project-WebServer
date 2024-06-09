package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Repository
public class PostDaoImpl implements PostDao {
    private Map<String, List<PostDto>> db = new HashMap<>();

    // 생성자에서 초기 데이터 설정
    public PostDaoImpl() {
        System.out.println("PostDaoImpl 객체 생성");

        // E와 I 게시판 데이터 (카테고리 1)
        List<PostDto> db1 = new ArrayList<>();
        db1.add(new PostDto(1, "ISFP정오", "나랑 만나서 놀 I형 사람을 구해요~", "저는 처음 보는 사람한테도 말 잘 걸어요. 연락주세요! 010-1234-5678", 0, 1, "E와 I 게시판"));
        db1.add(new PostDto(5, "ISTJ준형", "팀플할때 너무 힘들어요ㅠ", "제가 조장이 되었습니다. 어떻게 해야할지 댓글로 조언 부탁드려요.", 0, 2, "E와 I 게시판"));
        db1.add(new PostDto(7, "ENFJ밍식", "조원을 통솔하는 방법", "강하게 조원을 밀어붙치고, 따라오지 않을 경우 버리지않고, 챙겨야합니다.", 0, 0, "E와 I 게시판"));
        db.put("E와 I 게시판", db1);

        // T와 F 게시판 데이터 (카테고리 2)//
        List<PostDto> db2 = new ArrayList<>();
        db2.add(new PostDto(2, "ESTJ민재", "운다고 해결되는게 아니야!!", "문제해결을 위해 직접 나서서 해야 뭐든것이 풀린다고! 울지마 뚝!", 0, 3, "T와 F 게시판"));
        db2.add(new PostDto(9, "ESFJ흥민", "슬픈 영화를 보면 왜 눈물이 날까?", "나는 눈물이 많아ㅠㅠㅠ 나도 가끔은 이성적이고 싶다구~", 0, 4, "T와 F 게시판"));
        db2.add(new PostDto(10, "ISFJ희찬", "나는 슈퍼인싸! 친구들을 다 챙기고싶어", "친구들이 너무 좋고, 공감받으며 나도 행복해!!", 0, 5, "T와 F 게시판"));
        db.put("T와 F 게시판", db2);

        // P와 J 게시판 데이터 (카테고리 3)
        List<PostDto> db3 = new ArrayList<>();
        db3.add(new PostDto(3, "ISTP철수", "나는 계획따위 없어 무계획이야", "너는 다 계획이 있니? 나는 없어ㅎㅎ", 0, 6, "P와 J 게시판"));
        db3.add(new PostDto(4, "ESTP짱구", "오늘은 눈에 보이는 친구들 괴롭혀야지~", "항상 즉흥적으로 괴롭히는건 즐거워!", 0, 7, "P와 J 게시판"));
        db3.add(new PostDto(8, "INTJ맹구", "나는 지금을 항상 생각해!", "항상 어떤일을 할때 계획을 세워 난. 너희는 어떠니?", 0, 8, "P와 J 게시판"));
        db.put("P와 J 게시판", db3);

        // N와 S 게시판 데이터 (카테고리 4)
        List<PostDto> db4 = new ArrayList<>();
        db4.add(new PostDto(6, "ENFJ강인", "내가 만약 좀비가 된다면???", "내가 좀비가 된다면 당장 친구들부터 잡아먹어야지", 0, 9, "N와 S 게시판"));
        db4.add(new PostDto(11, "ISFJ정우", "취업예정입니다. 어떤 공부해야할까요?", "우선 자격증과 포트폴리오부터 시작해보려고 합니다. 조언 댓글로 부탁해요~", 0, 10, "N와 S 게시판"));
        db4.add(new PostDto(12, "INTJ인범", "바퀴벌래가 되면 난 바로 누구든 잡아버릴래", "바로 에프킬라로 잡아버릴테니 내 눈앞에 띄지마!!!", 0, 11, "N와 S 게시판"));
        db.put("N와 S 게시판", db4);
    }


    @Override
    public Map<String, List<PostDto>> findAllPosts() {
        return db;
    }

    // 모든 게시글을 반환하는 메서드
    @Override
    public List<PostDto> findPostsByCategory(String category) { // 메서드 이름 변경
        return new ArrayList<>(db.getOrDefault(category, new ArrayList<>()));
    }

    // 유저의 게시글을 반환하는 메서드
    @Override
    public List<PostDto> findPostsByUserId(int userId) { // 메서드 이름 변경
        return db.values().stream()
                .flatMap(List::stream)
                .filter(post -> post.getUserIdx() == userId)
                .collect(Collectors.toList());
    }

    // 특정 idx에 해당하는 게시글을 반환하는 메서드
    @Override
    public PostDto findById(String category, int idx) {
        return db.getOrDefault(category, new ArrayList<>()).stream()
                .filter(post -> post.getIdx() == idx)
                .findAny()
                .orElse(null);
    }

    // 특정 idx에 해당하는 게시글을 삭제하는 메서드
    @Override
    public void delete(String category, int idx) {
        List<PostDto> posts = db.get(category);
        if (posts != null) {
            posts.removeIf(post -> post.getIdx() == idx);
        }
    }

    // 새로운 게시글을 추가하거나 업데이트하는 메서드
    @Override
    public PostDto insertPost(PostDto post) {
        String category = post.getCategory();
        List<PostDto> posts = db.computeIfAbsent(category, k -> new ArrayList<>());
        if (post.getIdx() == -1) {
            post.setIdx(getLastIdx(category) + 1);
        }
        posts.add(post);
        return post;
    }

    @Override
    public PostDto updatePost(PostDto post) {
        String category = post.getCategory();
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
    public boolean increaseLikes(String category, int idx) {
        PostDto post = findById(category, idx);
        if (post != null && post.getLikes() == 0) {
            post.setLikes(1);
            return true;
        }
        return false;
    }

    // 특정 게시글의 좋아요 수를 감소시키는 메서드
    @Override
    public boolean decreaseLikes(String category, int idx) {
        PostDto post = findById(category, idx);
        if (post != null && post.getLikes() == 1) {
            post.setLikes(0);
            return true;
        }
        return false;
    }

    // 특정 게시글이 이미 좋아요를 눌렀는지 확인하는 메서드
    @Override
    public boolean isAlreadyLiked(String category, int idx) {
        PostDto post = findById(category,idx);
        return post != null && post.getLikes() == 1;
    }

    // 마지막 게시글의 idx를 반환하는 메서드
    @Override
    public int getLastIdx(String category) {

        int maxIdx = Integer.MIN_VALUE;
        for (List<PostDto> posts : db.values()) {
            for (PostDto post : posts) {
                if (post.getIdx() > maxIdx) {
                    maxIdx = post.getIdx();
                }
            }
        }
        return maxIdx;
    }
}
