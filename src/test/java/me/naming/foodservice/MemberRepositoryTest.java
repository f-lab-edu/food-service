package me.naming.foodservice;

import me.naming.foodservice.domain.Member;
import me.naming.foodservice.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void add () {
    memberRepository.save(new Member("DELETE", "naming", "naming@gmail.com", "01022021812"));
  }
}
