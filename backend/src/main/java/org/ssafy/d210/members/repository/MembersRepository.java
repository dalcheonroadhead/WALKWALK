package org.ssafy.d210.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;

import java.util.Optional;


// JpaRepository<사용할 Entity 이름, PK의 타입>
@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByEmail(String email);

    Optional<Members> findById(Long id);

    Optional<Members> findByEmailAndDeletedAtIsNull(String googleEmail);

    @Query(value = "UPDATE members set updated_at = now() where member_id = :id", nativeQuery = true)
    void updateById(@Param("id") Long id);

    Optional<Members> findByNicknameAndDeletedAtIsNull(String nickname);

    
}
