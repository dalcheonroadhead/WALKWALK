package org.ssafy.d210.items.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.items.dto.request.PostUseItemRequest;
import org.ssafy.d210.items.dto.response.GetItemListResponse;
import org.ssafy.d210.items.dto.response.PostUseItemResponse;
import org.ssafy.d210.items.entity.Items;
import org.ssafy.d210.items.entity.MemberItemHistory;
import org.ssafy.d210.items.repository.ItemsRepository;
import org.ssafy.d210.items.repository.MemberItemHistoryRepository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.ssafy.d210._common.exception.ErrorType.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final MembersRepository membersRepository;
    private final MemberAccountRepository memberAccountRepository;
    private final MemberItemHistoryRepository memberItemHistoryRepository;

    public List<GetItemListResponse> getList() {
        return itemsRepository.findAll()
                .stream().map(GetItemListResponse::of)
                .collect(Collectors.toList());
    }

    public PostUseItemResponse useItem(@AuthenticationPrincipal UserDetailsImpl userDetails, PostUseItemRequest postUseItemRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        Items item = findItemsById(postUseItemRequest.getItemId());
        int price = item.getEggPrice();

        memberAccount.putEgg(price, false);

        memberItemHistoryRepository.save(MemberItemHistory.of(1, item.getType(), member, item));

        return PostUseItemResponse.of(memberAccount.getEgg());
    }

    public Items findItemsById(Long itemsId) {
        return itemsRepository.findItemsById(itemsId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_ITEM));
    }

    public Members findByEmailAndDeletedAtIsNull(String email) {
        return membersRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER));
    }

    public MemberAccount findMemberAccountByMembers(Long memberAccountId) {
        return memberAccountRepository.findMemberAccountById(memberAccountId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ACCOUNT));
    }
}
