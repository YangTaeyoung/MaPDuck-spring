package com.mapduck.serivce;

import com.mapduck.domain.Member;
import com.mapduck.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;
    private final  RestTemplate restTemplate;

    @Override
    public Member getMember(Long id) {
        Optional<Member> member = this.memberRepository.findById(id);
        if(member.isEmpty()){
            throw new RuntimeException("user not found");
        }
        return member.get();
    }

    @Override
    public void saveMember(Member member) {
        this.memberRepository.save(member);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: id를 기준으로 member를 삭제하는 함수
     * @param id: 삭제할 member의 id값
     */
    @Override
    public void deleteMember(Long id) {
        Member member = getMember(id);
        this.memberRepository.delete(member);
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * @param user: UserDetails를 상속받는 Spring의 User객체 (우리가 구현한 것 아님)
     * @return member: 이메일을 기준으로 조회한 member Entity 객체
     */
    @Transactional
    @Override
    public Member metaUserToMember(User user) {
        System.out.println(user.getUsername());
       return memberRepository.findByEmail(user.getUsername());
    }
}
