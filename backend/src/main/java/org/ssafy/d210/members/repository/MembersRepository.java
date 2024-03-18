package org.ssafy.d210.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.members.entity.Members;


// JpaRepository<사용할 Entity 이름, PK의 타입>
public interface MembersRepository extends JpaRepository<Members, Long> {

}
