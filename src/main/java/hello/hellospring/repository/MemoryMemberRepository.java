package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //실무에는 동시성의 문제가 발생할 수 있어 concurrent 사용
    private static long sequence = 0l; //sequence : 키 값 생성
    //실무에는 동시성 문제를 고려하여 AtomicLong 사용
    @Override
    public Member save(Member member) { //이름은 넘어온 상태
        member.setId(++sequence); //store에 넣기 전 member에 id 값 세팅
        store.put(member.getId(),member); //store에 저장 -> Map에 저장
        return member; //스펙에 따라 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store에서 꺼내기
        //Optional.ofNullable : 결과가 null이여도 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member에서 member.getName이 여기 파라미터로 넘어온 Name과 같은지 확인, 같은 경우에만 필터링
                .findAny(); //그 중에서 찾으면 반환
    } //루프 돌리기

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values = 멤버들
    }

    public void clearStore(){
        store.clear();  //톨을 싹 비움
    }
}
