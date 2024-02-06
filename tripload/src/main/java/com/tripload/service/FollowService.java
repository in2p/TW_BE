package com.tripload.service;

import com.tripload.entity.Follow;
import com.tripload.entity.Member;
import com.tripload.exception.GlobalException;
import com.tripload.repository.FollowRepository;
import com.tripload.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    GlobalException globalException;

    @Transactional
    public void saveFollow(Long toMemberId, Long fromMemberId) {

        Optional<Member> memberOptional = memberRepository.findById(fromMemberId);

        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            Follow follow = new Follow(toMemberId, member);
            followRepository.save(follow);
        }
    }

    @Transactional
    public void deleteSubscribe(Long toMemberId, Long fromMemberId) {
        followRepository.deleteByToMemberIdAndFromMemberId(toMemberId, fromMemberId);
    }
}
