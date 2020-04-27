package me.naming.foodservice.controller;

import javax.validation.Valid;
import me.naming.foodservice.domain.Member;
import me.naming.foodservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

  @Autowired
  private MemberService memberService;

  @PostMapping
  public ResponseEntity create(@Valid @RequestBody Member member) {
    return ResponseEntity.ok(memberService.save(member));
  }

}