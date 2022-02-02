package com.example.jpawebapp.service;

import com.example.jpawebapp.entity.Member;
import com.example.jpawebapp.repository.MemberRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> list = memberRepository.findByName(member.getName());
        if (!list.isEmpty()) {
            throw new IllegalStateException("이미 존재 하는 회원입니다.");
        }
    }
}
