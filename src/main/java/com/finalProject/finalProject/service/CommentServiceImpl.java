package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.CommentDao;
import com.finalProject.finalProject.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean insertComment(int postIdx, int userIdx, String commentContent) {

        return false;
    }

    @Override
    public List<CommentDto> getCommentsByPostIdx(int idx) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentsByUserIdx(int idx) {
        return null;
    }

    @Override
    public void updateComment(CommentDto commentDto) {

    }

    @Override
    public void deleteComment(long idx) {

    }

    @Override
    public void deleteCommentsByPostIdx(long idx) {

    }
}
