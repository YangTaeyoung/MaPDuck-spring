package com.mapduck.serivce;

import com.mapduck.domain.Member;
import com.mapduck.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Member> getMembers(String keyword) {
        if(keyword == null || keyword.length() == 0) {
            return this.memberRepository.findAll();
        }else{
            return this.memberRepository.search(keyword);
        }
    }

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

    @Override
    public void deleteMember(Long id) {
        Member member = getMember(id);
        this.memberRepository.delete(member);
    }
}
