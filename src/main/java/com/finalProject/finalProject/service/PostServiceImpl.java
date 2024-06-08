package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.PostDao;
import com.finalProject.finalProject.dao.UserDao;
import com.finalProject.finalProject.dto.PostDto;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, List<PostDto>> findAllPostsReverse() {
        // 역순으로 만들 DB 그릇
        Map<String, List<PostDto>> reversedDb = new HashMap<>();

        // 역순으로 만들어서 그릇에 담기
        for (Map.Entry<String, List<PostDto>> entry : postDao.findAllPosts().entrySet()) {
            List<PostDto> reversedList = new ArrayList<>(entry.getValue());
            Collections.reverse(reversedList);
            reversedDb.put(entry.getKey(), reversedList);
        }

        return reversedDb;
    }

    @Override
    public List<PostDto> findPostsByCategory(String category) { // 메서드 이름 변경
        return postDao.findPostsByCategory(category); // 카테고리에 해당하는 모든 게시글 반환
    }
    @Override
    public List<PostDto> findPostsByUserId(int userId) { // 메서드 이름 변경
        return postDao.findPostsByUserId(userId); // 유저에 해당하는 모든 게시글 반환
    }

    @Override
    public PostDto findById(String category, int idx) {
        return postDao.findById(category, idx);
    }

    @Override
    public void delete(String category, int idx) {
        postDao.delete(category, idx);
    }

    @Override
    public PostDto save(PostDto post) {
        UserDto user = userDao.getUserById(post.getUserIdx());
        post.setNickname(user.getMbti() + user.getName());
        String category = post.getCategory();
        if (post.getIdx() == -1) {
            post.setIdx(postDao.getLastIdx(category) + 1); // 해당 카테고리의 마지막 idx를 가져와서 새로운 idx를 설정
            return postDao.insertPost(post); // 해당 카테고리에 새로운 게시글 추가
        } else {
            return postDao.updatePost(post);
        }
    }

    @Override
    public int count() {
        return postDao.count();
    }

    @Override
    public boolean increaseLikes(String category, int idx) {
        return postDao.increaseLikes(category, idx);
    }

    @Override
    public boolean decreaseLikes(String category, int idx) {
        return postDao.decreaseLikes(category, idx);
    }

    @Override
    public boolean isAlreadyLiked(String category, int idx) {
        return postDao.isAlreadyLiked(category, idx);
    }
}
