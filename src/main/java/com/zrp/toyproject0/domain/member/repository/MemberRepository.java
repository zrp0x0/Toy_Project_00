package com.zrp.toyproject0.domain.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zrp.toyproject0.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    boolean existsByUsername(String username);
    boolean existsByUserEmail(String userEmail);
    Optional<Member> findByUsername(String username);
    
}
