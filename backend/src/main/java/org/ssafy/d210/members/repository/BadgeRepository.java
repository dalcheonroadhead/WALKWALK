package org.ssafy.d210.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Badge;

import java.util.List;
import java.util.Optional;

@Repository
public interface  BadgeRepository extends JpaRepository<Badge,Long> {



}
