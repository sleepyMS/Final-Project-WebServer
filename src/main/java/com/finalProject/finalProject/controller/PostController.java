package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.PostDto;
import com.finalProject.finalProject.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String home(HttpSession session) {
        session.setAttribute("userIndex", "user1");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "list";
    }

    @RequestMapping("/read/{idx}")
    public String read(@PathVariable int idx, Model model, HttpSession session) {
        PostDto post = postService.findById(idx);
        model.addAttribute("post", post);
        String currentUser = (String) session.getAttribute("userIndex");
        model.addAttribute("currentUser", currentUser);
        return "read";
    }

    @RequestMapping("/delete/{idx}")
    public String delete(@PathVariable int idx, HttpSession session) {
        PostDto post = postService.findById(idx);
        String currentUser = (String) session.getAttribute("userIndex");
        if (post != null && post.getUserIndex().equals(currentUser)) {
            postService.delete(idx);
        }
        return "redirect:/list";
    }

    @RequestMapping("/insertForm")
    public String insertForm() {
        return "insertForm";
    }

    @PostMapping(value = "/insert")
    public String insert(PostDto post, HttpSession session) {
        post.setIdx(-1);
        String currentUser = (String) session.getAttribute("userIndex");
        post.setUserIndex(currentUser);
        postService.save(post);
        return "redirect:/list";
    }

    @RequestMapping("/updateForm/{idx}")
    public String updateForm(@PathVariable int idx, Model model, HttpSession session) {
        PostDto post = postService.findById(idx);
        String currentUser = (String) session.getAttribute("userIndex");
        if (post != null && post.getUserIndex().equals(currentUser)) {
            model.addAttribute("post", post);
            return "updateForm";
        }
        return "redirect:/list";
    }

    @PostMapping("/update")
    public String update(PostDto post, HttpSession session) {
        String currentUser = (String) session.getAttribute("userIndex");
        PostDto existingPost = postService.findById(post.getIdx());
        if (existingPost != null && existingPost.getUserIndex().equals(currentUser)) {
            postService.save(post);
        }
        return "redirect:/read/" + post.getIdx();
    }

    @RequestMapping("/like/{idx}")
    public String like(@PathVariable int idx) {
        postService.increaseLikes(idx);
        return "redirect:/read/" + idx;
    }
}
