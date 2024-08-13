package com.example.omg_project.domain.reviewpost.controller;

import com.example.omg_project.domain.reviewpost.dto.ReviewPostDto;
import com.example.omg_project.domain.reviewpost.service.ReviewPostService;
import com.example.omg_project.domain.user.entity.User;
import com.example.omg_project.domain.user.service.UserService;
import com.example.omg_project.global.jwt.util.JwtTokenizer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviewPost")
public class ReviewPostController {
    private final ReviewPostService reviewPostService;
    private final UserService userService;
    private final JwtTokenizer jwtTokenizer;

    @GetMapping("/createPost")
    public String createPost(HttpServletRequest request, Model model) {
        // 로그인한 사용자 정보 가져오기
        String accessToken = jwtTokenizer.getAccessTokenFromCookies(request);
        if(accessToken != null){
            String username = jwtTokenizer.getUsernameFromToken(accessToken);
            User user = userService.findByUsername(username).orElse(null);
            model.addAttribute("user", user);
        }
        return "review/createpost";
    }

    @GetMapping("/")
    public String listPosts(HttpServletRequest request, Model model) {
        // 로그인한 사용자 정보 가져오기
        String accessToken = jwtTokenizer.getAccessTokenFromCookies(request);
        if(accessToken != null){
            String username = jwtTokenizer.getUsernameFromToken(accessToken);
            User user = userService.findByUsername(username).orElse(null);
            model.addAttribute("user", user);
        }
        return "review/listposts";
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable Long postId, HttpServletRequest request, Model model) {
        // 게시글 가져오기
        ReviewPostDto.Response post = reviewPostService.findReviewPostById(postId);
        model.addAttribute("post", post);

        // 로그인한 사용자 정보 가져오기
        String accessToken = jwtTokenizer.getAccessTokenFromCookies(request);
        if(accessToken != null){
            String username = jwtTokenizer.getUsernameFromToken(accessToken);
            User user = userService.findByUsername(username).orElse(null);
            model.addAttribute("user", user);
        }

        return "review/viewpost";
    }

    @GetMapping("/{postId}/updatePost")
    public String updatePost(@PathVariable Long postId, HttpServletRequest request, Model model) {
        model.addAttribute("post", reviewPostService.findReviewPostById(postId));
        // 로그인한 사용자 정보 가져오기
        String accessToken = jwtTokenizer.getAccessTokenFromCookies(request);
        if(accessToken != null){
            String username = jwtTokenizer.getUsernameFromToken(accessToken);
            User user = userService.findByUsername(username).orElse(null);
            model.addAttribute("user", user);
        }
        return "review/updatepost";
    }

}