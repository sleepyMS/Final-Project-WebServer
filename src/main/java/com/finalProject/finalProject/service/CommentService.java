package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.CommentDto;
import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface CommentService {

    public boolean insertComment(int postIdx, String commentContent, UserDto currentUser);


    public List<CommentDto> getCommentsByPostIdx(int idx);
    public List<CommentDto> getCommentsByUserIdx(int idx);
    public void updateComment(CommentDto commentDto);
    public void deleteComment(long idx);
    public void deleteCommentsByPostIdx(long idx);
}
