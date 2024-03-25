package org.ssafy.d210.halleyGalley.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.entity.Mission;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface HalleyGalleyRepository extends JpaRepository<HalleyGalley, Long> {

    Boolean existsHalleyGalleyByHalleyIdAndGalleyId(Members member, Members galleyId);

    HalleyGalley findHalleyGalleyByGalleyIdAndHalleyId(Members member, Members halleyId);

    List<HalleyGalley> findByHalleyId(Members member);

    List<HalleyGalley> findByGalleyId(Members member);

    List<HalleyGalley> findByHalleyIdAndIsAcceptedIsFalse(Members member);
}
