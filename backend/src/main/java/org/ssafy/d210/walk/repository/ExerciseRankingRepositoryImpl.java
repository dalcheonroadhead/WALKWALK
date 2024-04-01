package org.ssafy.d210.walk.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.walk.dto.response.FriendRankingResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExerciseRankingRepositoryImpl implements ExerciseRankingRepository{

    private final EntityManager entityManager;

    @Override
    public Slice<FriendRankingResponseDto> findStepsRankingByPage(Long myId, Pageable pageable, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT m.member_id, m.nickname, m.profile_url, COALESCE(SUM(e.steps), 0) as steps, " +
                "RANK() OVER (ORDER BY COALESCE(SUM(e.steps), 0) DESC) as ranking " +
                "FROM members m LEFT OUTER JOIN exercise e ON m.member_id = e.member_id AND e.exercise_day BETWEEN ?2 AND ?3 " +
                "WHERE (m.member_id = ?1 OR m.member_id IN (SELECT f.receiver_id FROM friend_list f WHERE f.sender_id = ?1)) " +
                "AND e.exercise_day BETWEEN ?2 AND ?3 " +
                "GROUP BY m.member_id, m.nickname, m.profile_url " +
                "ORDER BY steps DESC";

        // 페이징 처리를 위한 쿼리 실행
        @SuppressWarnings("unchecked")
        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, myId)
                .setParameter(2, java.sql.Date.valueOf(startDate))
                .setParameter(3, java.sql.Date.valueOf(endDate))
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize() + 1) // 다음 페이지 확인을 위해 +1
                .getResultList();

        // 결과를 DTO로 변환
        List<FriendRankingResponseDto> dtos = results.stream().map(result -> new FriendRankingResponseDto(
                ((Number) result[0]).longValue(), // id
                (String) result[1], // nickname
                (String) result[2], // profileUrl
                ((Number) result[3]).longValue(), // steps
                ((Number) result[4]).longValue() // ranking
        )).collect(Collectors.toList());

        // 다음 페이지 존재 여부 확인
        boolean hasNext = dtos.size() > pageable.getPageSize();
        if (hasNext) {
            // 마지막 항목 제거
            dtos.remove(dtos.size() - 1);
        }

        // Slice 반환
        return new SliceImpl<>(dtos, pageable, hasNext);
    }

    @Override
    public Slice<FriendRankingResponseDto> findStreakRankingByPage(Long myId, Pageable pageable, LocalDate yesterday) {
        // 네이티브 쿼리 준비 (연속 운동 일수에 따른 순위 계산 포함)
        String sql = "SELECT m.member_id, m.nickname, m.profile_url, COALESCE(e.streak, 0) AS streak, " +
                "RANK() OVER (ORDER BY COALESCE(e.streak, 0) DESC) as rank " +
                "FROM members m LEFT OUTER JOIN exercise e ON m.member_id = e.member_id " +
                "WHERE (m.member_id = ?1 OR m.member_id IN (SELECT f.receiver_id FROM friend_list f WHERE f.sender_id = ?1 AND f.is_friend = true)) " +
                "AND e.exercise_day = ?2 " +
                "ORDER BY streak DESC";

        // 페이징 처리를 위한 쿼리 실행
        @SuppressWarnings("unchecked")
        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, myId)
                .setParameter(2, java.sql.Date.valueOf(yesterday))
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize() + 1) // 다음 페이지 확인을 위해 +1
                .getResultList();

        // 결과를 DTO로 변환
        List<FriendRankingResponseDto> content = results.stream().map(result -> new FriendRankingResponseDto(
                ((Number) result[0]).longValue(), // id
                (String) result[1], // nickname
                (String) result[2], // profileUrl
                ((Number) result[3]).longValue(), // streak
                ((Number) result[4]).longValue() // rank
        )).collect(Collectors.toList());

        // 다음 페이지 존재 여부 확인
        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            // 마지막 항목 제거하여 결과를 pageSize에 맞춤
            content.remove(content.size() - 1);
        }

        // Slice 객체로 결과 반환
        return new SliceImpl<>(content, pageable, hasNext);
    }
}
