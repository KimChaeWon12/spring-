package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //메소드가 실행이 끝날 때마다 동작, 콜백메소드
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");  //이름 지정

        repository.save(member);  //repository에 member 저장

        Member result = repository.findById(member.getId()).get();  //제대로 저장 되었는지 검증
        //get()을 통해 바로 꺼내기(test라 이렇게)
        System.out.println("result = " + (result == member)); //값이 같으면 참
        Assertions.assertEquals(member, result);  //틀리면 다음 단계로 못 넘어가게 함
        assertThat(member).isEqualTo(result);
    } //save가 잘 작동하는지 테스트

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();  //spring1 찾기
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
