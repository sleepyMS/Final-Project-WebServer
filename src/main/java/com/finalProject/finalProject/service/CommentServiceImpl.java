package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.CommentDao;
import com.finalProject.finalProject.dto.CommentDto;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    //    @Autowired
//    private
    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean insertComment(int postIdx, int userIdx, String commentContent, UserDto currentUser) {
        CommentDto comment = new CommentDto();

        try {
            comment.setIdx(commentDao.getCount()+1);
            comment.setPostIdx(postIdx);
            comment.setUserIdx(currentUser.getId());
            comment.setMbti(currentUser.getMbti());
            comment.setNick(currentUser.getNick());
            comment.setContent(commentContent);
        } catch (Exception e) {
            return false;
        }

        commentDao.insertComment(comment);

        return true;
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
