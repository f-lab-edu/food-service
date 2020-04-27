package me.naming.foodservice.service;

import me.naming.foodservice.domain.Member;
import me.naming.foodservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  @Autowired
  private MemberRepository memberRepository;

  public Member save(Member member) {
    return memberRepository.save(member);
  }

}
