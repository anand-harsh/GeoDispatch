package com.geodispatch.app.strategies;

import com.geodispatch.app.entities.enums.PaymentMethod;
import com.geodispatch.app.strategies.impl.CashPaymentStrategy;
import com.geodispatch.app.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {

        return switch (paymentMethod) {

            case CASH -> cashPaymentStrategy;

            case WALLET -> walletPaymentStrategy;

            default -> throw new IllegalArgumentException(
                    "Unsupported payment method: " + paymentMethod
            );
        };
    }
}