package org.ssafy.d210.halleyGalley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.members.entity.Members;

import java.util.List;
import java.util.Optional;

@Repository
public interface HalleyGalleyRepository extends JpaRepository<HalleyGalley, Long> {

    Boolean existsHalleyGalleyByHalleyIdAndGalleyId(Members member, Members galleyId);

    Optional<HalleyGalley> findHalleyGalleyByGalleyIdAndHalleyId(Members member, Members halleyId);

    Optional<List<HalleyGalley>> findByHalleyId(Members member);

    Optional<List<HalleyGalley>> findByGalleyId(Members member);

    Optional<List<HalleyGalley>> findByHalleyIdAndIsAcceptedIsFalse(Members member);
}
