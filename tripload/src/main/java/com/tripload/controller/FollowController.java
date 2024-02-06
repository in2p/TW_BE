package com.tripload.controller;

import com.tripload.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/api/subscribe/{toMemberId}")
    public ResponseEntity<String> follow(@PathVariable Long toUserId, Principal principal){
        followService.saveFollow(toUserId, Long.valueOf(principal.getName()));
        return ResponseEntity.ok().body("구독성공");
    }

    @DeleteMapping("/api/subscribe/{toMemberId}")
    public ResponseEntity<String> unFollow(@PathVariable Long toUserId, Principal principal){
        followService.deleteSubscribe(toUserId, Long.valueOf(principal.getName()));
        return ResponseEntity.ok().body("구독해제성공");
    }
}
