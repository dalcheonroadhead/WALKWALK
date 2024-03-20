package org.ssafy.d210.halleyGalley.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.entity.Mission;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDateTime;
import java.util.List;

public interface HalleyGalleyRepository extends JpaRepository<HalleyGalley, Long> {

    public interface HalleyGalleyProjection{
        LocalDateTime getCreatedAt();
        Members getGalleyId();
        Mission getMissionId();
    }
    List<HalleyGalleyProjection> findHalleyGalleysByHalleyId(@Param("member") Members member);
}
