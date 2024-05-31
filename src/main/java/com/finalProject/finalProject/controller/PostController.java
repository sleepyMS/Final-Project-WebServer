package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.PostDto;
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

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boards", postService.getAllBoards()); // 모든 게시판 정보를 가져옴
        model.addAttribute("recentPosts", postService.getRecentPosts()); // 최근 게시글 3개씩을 가져옴
        return "list";
    }

    @GetMapping("/board/{boardId}")
    public String viewBoard(@PathVariable int boardId, Model model) {
        List<PostDto> posts = postService.getPostsByBoardId(boardId); // 해당 게시판의 모든 게시글을 가져옴
        model.addAttribute("posts", posts);
        model.addAttribute("boardId", boardId);
        return "board";
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

    @GetMapping("/createBoardForm")
    public String createBoardForm() {
        return "createBoardForm";
    }

    @PostMapping("/createBoard")
    public String createBoard(@RequestParam String title) {
        postService.createBoard(title);
        return "redirect:/post/list";
    }

    @GetMapping("/board/{boardId}/createPostForm")
    public String createPostForm(@PathVariable int boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "createPostForm";
    }

    @PostMapping("/board/{boardId}/createPost")
    public String createPost(@PathVariable int boardId, PostDto postDto) {
        postDto.setBoardId(boardId);
        postService.save(postDto);
        return "redirect:/post/board/{boardId}";
    }

    @GetMapping("/board")
    public String board(Model model) {
        // 서비스에서 게시글 목록을 가져온다고 가정
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "board";
    }
}
