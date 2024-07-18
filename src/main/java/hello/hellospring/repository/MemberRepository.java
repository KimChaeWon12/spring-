package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member); //저장된 회원 반환
    Optional<Member> findById(Long id); //아이디로 회원 찾기
    Optional<Member> findByName(String name);
    //Optional : null을 감싸서 반환
    List<Member> findAll(); //모든 회원 리스트 반환
}
