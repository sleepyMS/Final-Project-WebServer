package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @RequestMapping("/write")
    public String write(@RequestParam("postIdx") int postIdx,
                        @RequestParam("userIdx") int userIdx,
                        @RequestParam("content") String content,
                        HttpSession session) {

        UserDto currentUser = (UserDto) session.getAttribute("currentUserDto");
        commentService.insertComment(postIdx, content, currentUser);

        return "redirect:/post/read/" + postIdx;
    }

//    @RequestMapping("/update/{postIdx}/{commentIdx}")
//    public String update(@PathVariable("postIdx") int postIdx,
//                         @PathVariable("commentIdx") int commentIdx) {
//
//        commentService.updateComment(commentIdx);
//
//        return "redirect:/post/read/" + postIdx;
//    }

    @RequestMapping("/delete/{postIdx}/{commentIdx}")
    public String delete(@PathVariable("postIdx") int postIdx,
                         @PathVariable("commentIdx") int commentIdx) {

        commentService.deleteComment(commentIdx);

        return "redirect:/post/read/" + postIdx;
    }
}
