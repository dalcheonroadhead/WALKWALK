package org.ssafy.d210.wallets._payment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.wallets._payment.entity.Payment;

import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.partner_order_id = :partner_order_id")
    Optional<Payment> findFirstByPartnerOrderId(String partner_order_id);

    Optional<Payment> findPaymentByTid(String tid);
}
