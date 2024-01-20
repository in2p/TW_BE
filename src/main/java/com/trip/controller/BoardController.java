package com.trip.controller;

import com.trip.entity.Member;
import com.trip.oauth.RequestOAuthInfoService;
import com.trip.repository.MemberRepository;
import com.trip.service.OAuthLoginService;
import com.trip.token.AuthTokensGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class BoardController {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final OAuthLoginService oAuthLoginService;

    @GetMapping
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberRepository.findAll());
    }
    @GetMapping("/{accessToken}")
    public ResponseEntity<Member> findByAccessToken(@PathVariable String accessToken) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        return ResponseEntity.ok(memberRepository.findById(memberId).get());
    }

}
