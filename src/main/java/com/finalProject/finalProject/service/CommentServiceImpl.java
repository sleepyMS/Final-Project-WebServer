package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.CommentDao;
import com.finalProject.finalProject.dao.UserDao;
import com.finalProject.finalProject.dao.UserDaoImple;
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
    @Autowired
    private UserDao userDao;

    @Override
    public boolean insertComment(int postIdx, String commentContent, UserDto currentUser) {
        CommentDto comment = new CommentDto();

        try {
            // 수정할 것
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

        return commentDao.getCommentByPostIdx(idx);
    }

    @Override
    public List<CommentDto> getCommentsByUserIdx(int idx) {
        return null;
    }

    @Override
    public void updateComment(int commentIdx, int postIdx, int userIdx, String updateContent) {
        CommentDto commentDto = new CommentDto();
        UserDto userDto = userDao.getUserById(userIdx);

        try {
            commentDto.setIdx(commentIdx);
            commentDto.setUserIdx(userIdx);
            commentDto.setContent(updateContent);
            commentDto.setPostIdx(postIdx);
            commentDto.setMbti(userDto.getMbti());
            commentDto.setNick(userDto.getNick());
        } catch (Exception e) {

        }

        commentDao.updateComment(commentDto);
    }

    @Override
    public void deleteComment(long idx) {
        commentDao.deleteComment(idx);
    }

    @Override
    public void deleteCommentsByPostIdx(long idx) {

    }
}
