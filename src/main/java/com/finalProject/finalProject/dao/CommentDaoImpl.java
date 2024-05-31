package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentDaoImpl implements CommentDao {
    private List<CommentDto> db = new ArrayList<>();
    public CommentDaoImpl() {
        System.out.println("CommentDaoImple 객체 생성");
        db.add(new CommentDto(1, 1, 1, "ENFJ", "min", "test1"));
        db.add(new CommentDto(1, 1, 1, "ENFJ", "min", "test2"));
        db.add(new CommentDto(1, 1, 1, "ENFJ", "min", "test3"));
    }

//    Create
    @Override
    public void insertComment(CommentDto CommentDto) { db.add(CommentDto); }

//    Read
    @Override
    public List<CommentDto> getCommentByPostIdx(int idx) {
        return db.stream()
                .filter(comment -> comment.getPostIdx() == idx)
                .collect(Collectors.toList());
    }
    @Override
    public List<CommentDto> getCommentByUserIdx(int idx) {
        return db.stream()
                .filter(comment -> comment.getPostIdx() == idx)
                .collect(Collectors.toList());
    }

//    Update
    @Override
    public void updateComment(CommentDto commentDto) {
        db.stream()
                .filter(comment -> comment.getIdx() == commentDto.getIdx())
                .findFirst()
                .ifPresent(comment -> comment.setContent(commentDto.getContent()));
    }

//    Delete
    @Override
    public void deleteComment(long idx) {
        db.stream()
                .filter(comment -> comment.getIdx() == idx)
                .findFirst()
                .ifPresent(db::remove);
    }
    @Override
    public void deleteCommentByPostIdx(long idx) {
        db = db.stream()
                .filter(comment -> comment.getPostIdx() != idx)
                .collect(Collectors.toList());
    }
}
