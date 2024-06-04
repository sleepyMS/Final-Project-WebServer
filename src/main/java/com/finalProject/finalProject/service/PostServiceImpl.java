package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.PostDao;
import com.finalProject.finalProject.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Override
    public Map<String, List<PostDto>> findAllPosts() {
        return postDao.findAllPosts();
    }

    @Override
    public List<PostDto> findPostsByCategory(String category) { // 메서드 이름 변경
        return postDao.findPostsByCategory(category); // 카테고리에 해당하는 모든 게시글 반환
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
