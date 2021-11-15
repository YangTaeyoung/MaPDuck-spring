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
       return memberRepository.findByEmail(user.getUsername());
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: 동일한 이메일이 DB에 있는지 확인하는 함수 없으면 True, 중복이면 False
     * @param email: 체크할 이메일
     * @return true: 동일한 이메일이 없음, false: 동일한 이메일이 존재함.
     */
    @Override
    public boolean checkEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member != null;
    }
    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: 동일한 핸드폰이 DB에 있는지 확인하는 함수 없으면 True, 중복이면 False
     * @param phone: 체크할 이메일
     * @return true: 동일한 이메일이 없음, false: 동일한 핸드폰이 존재함.
     */
    @Override
    public boolean checkPhone(String phone) {
        Member member = memberRepository.findByPhone(phone);
        return member != null;
    }
}
