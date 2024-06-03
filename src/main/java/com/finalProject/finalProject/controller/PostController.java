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

    @RequestMapping("/delete/{category}/{idx}")
    public String delete(@PathVariable int category, @PathVariable int idx) {
        postService.delete(category, idx);
        return "redirect:/post/list";
    }

    @RequestMapping("/insertForm")
    public String insertForm() {
        return "insertForm";
    }

    @PostMapping("/insert")
    public String insert(PostDto post) {
        if (post.getCategory() == 1) {
            post.setIdx(-1); // 새로운 게시글을 추가할 때 idx를 -1로 설정
            postService.save1(post); // E와 I 게시판에 게시글 저장
        } else if (post.getCategory() == 2) {
            post.setIdx(-1); // 새로운 게시글을 추가할 때 idx를 -1로 설정
            postService.save2(post); // T와 F 게시판에 게시글 저장
        }
        return "redirect:/post/list"; // 게시글 목록 페이지로 리다이렉트
    }


    @RequestMapping("/updateForm/{idx}")
    public String updateForm(@PathVariable int idx, Model model) {
        model.addAttribute("post", postService.findById(idx));
        return "updateForm";
    }

    @PostMapping("/update")
    public String update(PostDto post) {
        if (post.getCategory() == 1) {
            postService.save1(post); // E와 I 게시판의 게시글 수정
        } else if (post.getCategory() == 2) {
            postService.save2(post); // T와 F 게시판의 게시글 수정
        }
        return "redirect:/post/read/" + post.getIdx(); // 수정된 게시글 페이지로 리다이렉트
    }


    @RequestMapping("/like/{idx}")
    public String like(@PathVariable int idx) {
        boolean alreadyLiked = postService.isAlreadyLiked(idx); // 사용자가 이미 해당 게시물을 좋아요 했는지 확인
        if (!alreadyLiked) { // 한 번도 좋아요를 누른 적이 없는 경우에만 증가
            boolean success = postService.increaseLikes(idx); // 좋아요 수 증가
            if (!success) {
                return "redirect:/post/error"; // 다른 예외 처리, 예를 들어 게시물이 존재하지 않는 경우
            }
        } else {
            postService.decreaseLikes(idx); // 이미 좋아요를 누른 경우에는 감소
        }
        return "redirect:/post/read/" + idx; // 게시물 페이지로 리다이렉트
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<PostDto> posts1 = postService.findAll();
        List<PostDto> posts2 = postService.findAll2();
        model.addAttribute("posts1", posts1);
        model.addAttribute("posts2", posts2);
        return "list";
    }
}
