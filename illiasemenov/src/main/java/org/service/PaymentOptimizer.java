package org.service;

import org.model.Order;
import org.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentOptimizer {

    private final Map<String, PaymentMethod> methodMap;
    private final Map<String, BigDecimal> spentPerMethod = new HashMap<>();

    public PaymentOptimizer(List<PaymentMethod> methods) {
        methodMap = methods.stream().collect(Collectors.toMap(PaymentMethod::getName, m -> m));
    }

    public Map<String, BigDecimal> optimize(List<Order> orders) {
        for (Order order : orders) {
            processOrder(order);
        }
        return spentPerMethod;
    }

    private void processOrder(Order order) {
        BigDecimal originalValue = order.getAmount();

        BigDecimal bestFinalPrice = originalValue;
        String bestMethodId = null;
        BigDecimal bestLoyaltyAmount = BigDecimal.ZERO;
        BigDecimal bestCardAmount = BigDecimal.ZERO;

        // Опция 1: полностью оплатить баллами
        PaymentMethod loyalty = methodMap.get("PUNKTY");
        if (loyalty != null && loyalty.limit.compareTo(originalValue) >= 0) {
            BigDecimal finalPrice = originalValue.multiply(BigDecimal.valueOf(1 - loyalty.getDiscount().doubleValue() / 100.0));
            if (finalPrice.compareTo(bestFinalPrice) < 0) {
                bestFinalPrice = finalPrice;
                bestMethodId = "PUNKTY";
                bestLoyaltyAmount = finalPrice;
                bestCardAmount = BigDecimal.ZERO;
            }
        }

        // Опция 2: оплатить полностью одной картой (если доступна скидка)
        for (PaymentMethod method : methodMap.values()) {
            if (method.getName().equals("PUNKTY")) continue;
            if (!method.getName().equals(order.getPaymentMethod())) continue;
            if (method.limit.compareTo(originalValue) >= 0) {
                BigDecimal finalPrice = originalValue.multiply(BigDecimal.valueOf(1 - method.getDiscount().doubleValue() / 100.0));
                if (finalPrice.compareTo(bestFinalPrice) < 0) {
                    bestFinalPrice = finalPrice;
                        bestMethodId = method.getName();
                    bestLoyaltyAmount = BigDecimal.ZERO;
                    bestCardAmount = finalPrice;
                }
            }
        }

        // Опция 3: частично баллами (>=10%) + остальное картой (без скидки)
        if (loyalty != null && loyalty.limit.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal tenPercent = originalValue.multiply(BigDecimal.valueOf(0.10));
            BigDecimal maxPointsToUse = originalValue.min(loyalty.limit);
            if (maxPointsToUse.compareTo(tenPercent) >= 0) {
                BigDecimal discountedValue = originalValue.multiply(BigDecimal.valueOf(0.90)); // 10% скидка
                BigDecimal remaining = discountedValue.subtract(maxPointsToUse).max(BigDecimal.ZERO);
                PaymentMethod anyCard = findAnyCardWithEnoughLimit(remaining);
                if (anyCard != null) {
                    BigDecimal finalPrice = maxPointsToUse.add(remaining);
                    if (finalPrice.compareTo(bestFinalPrice) < 0) {
                        bestFinalPrice = finalPrice;
                        bestMethodId = anyCard.getName();
                        bestLoyaltyAmount = maxPointsToUse;
                        bestCardAmount = remaining;
                    }
                }
            }
        }

        // Сохраняем выбранные методы и обновляем лимиты
        if (bestLoyaltyAmount.compareTo(BigDecimal.ZERO) > 0) {
            PaymentMethod points = methodMap.get("PUNKTY");
            points.limit = points.limit.subtract(bestLoyaltyAmount);
            spentPerMethod.merge("PUNKTY", bestLoyaltyAmount, BigDecimal::add);
        }

        if (bestCardAmount.compareTo(BigDecimal.ZERO) > 0 && bestMethodId != null && !bestMethodId.equals("PUNKTY")) {
            PaymentMethod card = methodMap.get(bestMethodId);
            card.limit = card.limit.subtract(bestCardAmount);
            spentPerMethod.merge(bestMethodId, bestCardAmount, BigDecimal::add);
        }
    }

    private PaymentMethod findAnyCardWithEnoughLimit(BigDecimal needed) {
        for (PaymentMethod method : methodMap.values()) {
            if (!method.getName().equals("PUNKTY") && method.limit.compareTo(needed) >= 0) {
                return method;
            }
        }
        return null;
    }
}
