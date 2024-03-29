package org.ssafy.d210.notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.notifications.entity.Notification;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<List<Notification>> findAllByReceiverId(Members member);

    Optional<Notification> findNotificationById(Long id);
}
