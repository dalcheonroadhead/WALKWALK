package org.ssafy.d210.friends.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.friends.entity.FriendList;

public interface FriendListRepository extends JpaRepository<FriendList, Long> {

}
