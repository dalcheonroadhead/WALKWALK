package org.ssafy.d210.notifications.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.notifications.dto.request.PutNotificationRequest;
import org.ssafy.d210.notifications.entity.Notification;
import org.ssafy.d210.notifications.repository.EmitterRepository;
import org.ssafy.d210.notifications.repository.NotificationRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    // 기본 타임아웃 설정
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;

    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = createEmitter(userId);

        sendToClient(userId, "EventStream Created. [userId=" + userId + "]");
        return emitter;
    }

    /**
     * 서버의 이벤트를 클라이언트에게 보내는 메서드
     * 다른 서비스 로직에서 이 메서드를 사용해 데이터를 Object event에 넣고 전송하면 된다.
     */
    public void notify(Long userId, Object event) {
        sendToClient(userId, event);
    }

    private void sendToClient(Long id, Object data) {
        SseEmitter emitter = emitterRepository.get(id);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(data));
            } catch (IOException exception) {
                emitterRepository.deleteById(id);
                emitter.completeWithError(exception);
            }
        }
    }

    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);

        emitterRepository.save(id, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;

    }

    @Transactional
    public void insertNotification(Notification notification){
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(Members member){
        return notificationRepository.findAllByReceiverId(member)
                .orElseThrow(()->new CustomException(ErrorType.CANT_FIND_NOTIFICATION));
    }

    @Transactional
    public String putNotifications(PutNotificationRequest request){
        Notification notification = notificationRepository.findNotificationById(request.getNotificationId())
                .orElseThrow(()->new CustomException(ErrorType.CANT_FIND_NOTIFICATION));
        notification.updateIsChecked(true);
        notificationRepository.save(notification);
        return "";
    }
}