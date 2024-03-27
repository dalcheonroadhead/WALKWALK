package org.ssafy.d210.wallets.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.BaseTime;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210.wallets._payment.dto.request.PaymentExchangeRequest;
import org.ssafy.d210.wallets.dto.request.PutEggMoneyRequest;
import org.ssafy.d210.wallets.dto.request.PutEggRequest;
import org.ssafy.d210.wallets.dto.request.PutHalleyGalleyMoneyRequest;
import org.ssafy.d210.wallets.dto.response.PutEggMoneyResponse;

import static org.ssafy.d210._common.exception.ErrorType.NOT_ENOUGH_EGG;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberAccount extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "member_account_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String eoa;

    @Column(name = "public_key", columnDefinition = "LONGTEXT", nullable = false)
    private String publicKey;

    @Column
    @ColumnDefault("0")
    private Integer egg;

    @Column
    @ColumnDefault("0")
    private Integer money;

    public Integer putEgg(PutEggRequest putEggRequest, boolean operation) {
        // operation true(1): 획득, operation false(0): 차감
        if (operation) {
            this.egg += putEggRequest.getPutEggValue();
        } else {
            if (putEggRequest.getPutEggValue() < 0 || this.egg < putEggRequest.getPutEggValue()) {
                throw new CustomException(NOT_ENOUGH_EGG);
            }

            this.egg -= putEggRequest.getPutEggValue();
        }

        return egg;
    }

    public PutEggMoneyResponse putEggMoney(PutEggMoneyRequest putEggMoneyRequest, boolean operation) {
        // operation true(1): 획득
        if (operation) {
            this.egg += putEggMoneyRequest.getPutEggValue();
            this.money += putEggMoneyRequest.getPutMoneyValue();
        }

        return PutEggMoneyResponse.of(egg, money);
    }

    public Integer putMoney(PutHalleyGalleyMoneyRequest putHalleyGalleyMoneyRequest, boolean operation) {
        // operation true(1): 획득, operation false(0): 차감
        if (operation) {
            this.money += putHalleyGalleyMoneyRequest.getPutMoneyValue();
        } else {
            if (putHalleyGalleyMoneyRequest.getPutMoneyValue() < 0 || this.money < putHalleyGalleyMoneyRequest.getPutMoneyValue()) {
                throw new CustomException(NOT_ENOUGH_EGG);
            }

            this.money -= putHalleyGalleyMoneyRequest.getPutMoneyValue();
        }

        return money;
    }

    public Integer exchangeMoney(PaymentExchangeRequest paymentExchangeRequest) {
        if (paymentExchangeRequest.getExchangeMoneyValue() < 0 || this.money < paymentExchangeRequest.getExchangeMoneyValue()) {
            throw new CustomException(NOT_ENOUGH_EGG);
        }

        this.money -= paymentExchangeRequest.getExchangeMoneyValue();

        return money;
    }
}