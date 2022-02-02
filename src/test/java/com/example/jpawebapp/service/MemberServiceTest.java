package com.example.jpawebapp.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.jpawebapp.entity.Member;
import com.example.jpawebapp.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 처리가 된다.")
    public void joinTest() {
        //Given
        Member member = new Member("KIM");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertThat(saveId).isEqualTo(memberRepository.findOne(saveId).getId());
    }

    @Test()
    @DisplayName("중복회원은 예외 처리 된다.")
    public void duplicateExceptionTest() {
        //Given
        Member member1 = new Member("KIM");
        Member member2 = new Member("KIM");

        //When
        memberService.join(member1);

        //Then
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
            () -> memberService.join(member1));

        System.out.println("illegalStateException = " + illegalStateException);

        assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));
    }

}