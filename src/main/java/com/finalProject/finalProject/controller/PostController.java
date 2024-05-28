package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.PostDto;
import com.finalProject.finalProject.service.PostService;
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
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public  String home() {
        return "home";
    }

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
    public  String read(@PathVariable int idx, Model model) {
        model.addAttribute("post", postService.findById(idx));
        return "read";
    }

    @RequestMapping("/delete/{idx}")
    public  String delete(@PathVariable int idx) {
        postService.delete(idx);
        return "redirect:/list";
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
        return "redirect:/list";
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
        return "redirect:/read/" + post.getIdx();
    }
}
