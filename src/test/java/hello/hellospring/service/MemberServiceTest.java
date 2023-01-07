package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository MemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        MemberRepository.clearStore();
    }


    /*
    * 테스트 코드의 메서드명은 한글로 기입해도 가능
    * */
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring1");
        // when

        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicateException(){
        // given
            Member member1 = new Member();
            member1.setName("spring1");
            Member member2 = new Member();
            member2.setName("spring1");
        // when
            memberService.join(member1);
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*            try {
                memberService.join(member2);
                org.junit.jupiter.api.Assertions.fail();
            }
            catch (IllegalStateException e){
                Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
            }
*/

        // then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}