package org.ssafy.d210.friends.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.friends.entity.FriendList;
import org.ssafy.d210.members.entity.Members;

import java.util.List;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {

    List<FriendList> findFriendListsBySenderIdAndIsAcceptedIsTrue(Members member);
    FriendList findFriendListBySenderIdAndReceiverId(Members member1, Members member2);
}
