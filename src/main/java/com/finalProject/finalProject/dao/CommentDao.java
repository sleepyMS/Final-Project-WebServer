package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.CommentDto;
import com.finalProject.finalProject.dto.SignUpDto;

import java.util.List;

public interface CommentDao {
    public void insertComment(CommentDto CommentDto);
    public List<CommentDto> getCommentByPostIdx(int idx);
    public List<CommentDto> getCommentByUserIdx(int idx);
    public void updateComment(CommentDto commentDto);
    public void deleteComment(long idx);
    public void deleteCommentByPostIdx(long idx);
}
