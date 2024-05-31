package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.CommentDto;
import com.finalProject.finalProject.dto.PostDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.CommentService;
import com.finalProject.finalProject.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


    @RequestMapping("/read/{idx}")
    public String read(@PathVariable int idx, Model model, HttpSession session) {
        PostDto post = postService.findById(idx);
        UserDto currentUser = (UserDto) session.getAttribute("currentUserDto");
        List<CommentDto> comments = commentService.getCommentsByPostIdx(post.getIdx());

        model.addAttribute("post", post);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("comments", comments);
        return "read";
    }

    @RequestMapping("/delete/{idx}")
    public String delete(@PathVariable int idx) {
        postService.delete(idx);
        return "redirect:/post/list";
    }

    @RequestMapping("/insertForm")
    public String insertForm() {
        return "insertForm";
    }

    @PostMapping("/insert")
    public String insert(PostDto post) {
        post.setIdx(-1); // 새로운 게시글을 추가할 때 idx를 -1로 설정
        postService.save(post); // 게시글 저장
        return "redirect:/post/list"; // 게시글 목록 페이지로 리다이렉트
    }

    @RequestMapping("/updateForm/{idx}")
    public String updateForm(@PathVariable int idx, Model model) {
        model.addAttribute("post", postService.findById(idx));
        return "updateForm";
    }

    @PostMapping("/update")
    public String update(PostDto post) {
        postService.save(post); // 게시글 수정
        return "redirect:/post/read/" + post.getIdx(); // 수정된 게시글 페이지로 리다이렉트
    }

    @RequestMapping("/like/{idx}")
    public String like(@PathVariable int idx) {
        boolean success = postService.increaseLikes(idx);
        if (success) {
            return "redirect:/post/read/" + idx; // 좋아요가 성공적으로 증가했을 경우 게시글 페이지로 리다이렉트
        } else {
            return "redirect:/post/error"; // 좋아요가 이미 눌려있는 경우 등 예외 처리
        }
    }
}
