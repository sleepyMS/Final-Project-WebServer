package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.PostDto;
import com.finalProject.finalProject.service.CommentService;
import com.finalProject.finalProject.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

//    @RequestMapping("/")
//    public  String home() {
//        return "home";
//    }

    @RequestMapping("/about")
    public  String about() {
        return "about";
    }

    @RequestMapping("/list")
    public  String list(Model model) {
        model.addAttribute("posts",
                postService.findAll());
        return "list";
    }

    @RequestMapping("/read/{idx}")
    public String read(@PathVariable int idx, Model model, HttpSession session) {
        // 게시글 정보를 가져옴
        PostDto post = postService.findById(idx);
        model.addAttribute("post", post);

        // 게시글에 대한 댓글 목록을 가져옴
        model.addAttribute("comments", commentService.getCommentsByPostIdx(idx));

        // 현재 로그인 사용자 정보를 세션에서 가져옴
        String currentUser = (String) session.getAttribute("userIndex");
        model.addAttribute("currentUser", currentUser);

        return "read";
    }


    @RequestMapping("/delete/{idx}")
    public  String delete(@PathVariable int idx) {
        postService.delete(idx);
        return "redirect:/post/list";
    }

    @RequestMapping("/insertForm")
    public  String insertForm() {
        return "insertForm";
    }


    // files가 PostDto와 이름이 겹치면 안됨
    @PostMapping(value = "/insert")
    public  String insert(PostDto post)  {
        post.setIdx(-1);
        postService.save(post);
        return "redirect:/post/list";
    }

    @RequestMapping("/updateForm/{idx}")
    public  String updateForm(@PathVariable int idx, Model model) {
        model.addAttribute("post", postService.findById(idx));
        return "updateForm";
    }

    @RequestMapping("/update")
    public  String update(PostDto post) throws IOException {
        //post.setImage("/download/" + originalFilename);
        postService.save(post);
        return "redirect:/post/read/" + post.getIdx();
    }

    @RequestMapping("/like/{idx}")
    public String like(@PathVariable int idx) {
        postService.increaseLikes(idx);
        return "redirect:/post/read/" + idx;
    }

}