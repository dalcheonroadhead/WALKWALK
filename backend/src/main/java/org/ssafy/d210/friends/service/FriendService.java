package org.ssafy.d210.friends.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.friends.dto.response.GetFriendListResponse;
import org.ssafy.d210.friends.dto.request.PostFriendRequest;
import org.ssafy.d210.friends.dto.request.PutFriendRequest;
import org.ssafy.d210.friends.dto.response.GetMemberInfoResponse;
import org.ssafy.d210.friends.dto.response.PutFriendResponse;
import org.ssafy.d210.friends.entity.FriendList;
import org.ssafy.d210.friends.repository.FriendListRepository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendListRepository friendListRepository;
    private final MembersRepository membersRepository;

    public GetMemberInfoResponse getMemberInfo(Members member, Long memberId){
        Members members = membersRepository.findById(memberId).orElse(null);
        return null;
    }

    public List<GetFriendListResponse> getMemberList(Members member){
        List<FriendList> friendList = friendListRepository.findFriendListsBySenderIdAndIsAcceptedIsTrue(member);
        return friendList.stream().map(GetFriendListResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public String postFriend(Members member, PostFriendRequest postFriendRequest){
        Members receiver = membersRepository.findById(postFriendRequest.getMemberId()).orElse(null);
        FriendList friendList = friendListRepository.findFriendListBySenderIdAndReceiverId(member, receiver);

        if(friendList == null) {
            friendListRepository.save(
                    FriendList.builder()
                            .senderId(member)
                            .receiverId(receiver)
                            .isAccepted(false)
                            .build()
            );
            friendListRepository.save(
                    FriendList.builder()
                            .senderId(receiver)
                            .receiverId(member)
                            .isAccepted(false)
                            .build()
            );
        }
        else{
            throw new CustomException(ErrorType.ALREADY_SEND_FRIEND_REQUEST);
        }
        return "";
    }

    @Transactional
    public PutFriendResponse putFriend(Members member, PutFriendRequest putFriendRequest){
        Members friend = membersRepository.findById(putFriendRequest.getMemberId()).orElse(null);
        Boolean isAccepted = putFriendRequest.getIsAccept();

        FriendList friendList1 = friendListRepository.findFriendListBySenderIdAndReceiverId(member, friend);
        FriendList friendList2 = friendListRepository.findFriendListBySenderIdAndReceiverId(friend, member);
        friendList1.updateIsAccepted(isAccepted);
        friendList2.updateIsAccepted(isAccepted);

        friendListRepository.save(friendList1);
        friendListRepository.save(friendList2);

        return PutFriendResponse.builder().isFriend(isAccepted).build();
    }
}
