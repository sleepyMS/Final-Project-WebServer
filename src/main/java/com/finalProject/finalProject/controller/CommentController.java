package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @RequestMapping("/write")
    public String write(@RequestParam("category") String category,
                        @RequestParam("postIdx") int postIdx,
                        @RequestParam("content") String content,
                        HttpSession session) {

        UserDto currentUser = (UserDto) session.getAttribute("currentUserDto");
        commentService.insertComment(postIdx, content, currentUser);

        return "redirect:/post/read/" + category + '/' + postIdx;
    }


    @RequestMapping("/delete/{category}/{postIdx}/{commentIdx}")
    public String delete(@PathVariable("category") String category,
                         @PathVariable("postIdx") int postIdx,
                         @PathVariable("commentIdx") int commentIdx) {

        commentService.deleteComment(commentIdx);

        return "redirect:/post/read/" + category + '/' + postIdx;
    }

    @GetMapping("update")
    public String update(@RequestParam("category") String category,
                         @RequestParam("postIdx") int postIdx,
                         @RequestParam("commentIdx") int commentIdx,
                         @RequestParam("userIdx") int userIdx,
                         @RequestParam("updateContent") String updateContent) {

        commentService.updateComment(commentIdx, postIdx, userIdx, updateContent);
        return "redirect:/post/read/" + category + '/' + postIdx;
    }
}
