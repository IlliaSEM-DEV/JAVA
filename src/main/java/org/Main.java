package org;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.model.Order;
import org.model.PaymentMethod;
import org.service.PaymentOptimizer;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        // Проверка, что передано 2 аргумента командной строки
        if (args.length < 2) {
            System.err.println("Ошибка: необходимо указать 2 пути к файлам (заказы и методы оплаты).");
            System.err.println("Пример: java -jar приложение.jar orders.json methods.json");
            return; // Завершаем выполнение программы
        }

        // Проверяем, существует ли файл с заказами
        File ordersFile = new File(args[0]);
        if (!ordersFile.exists() || !ordersFile.isFile()) {
            System.err.println("Ошибка: файл с заказами не найден по следующему пути: " + args[0]);
            return;
        }

        // Проверяем, существует ли файл с методами оплаты
        File methodsFile = new File(args[1]);
        if (!methodsFile.exists() || !methodsFile.isFile()) {
            System.err.println("Ошибка: файл с методами оплаты не найден по следующему пути: " + args[1]);
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        // Чтение данных из файлов
        List<Order> orders = mapper.readValue(ordersFile, new TypeReference<>() {});
        List<PaymentMethod> methods = mapper.readValue(methodsFile, new TypeReference<>() {});

        // Инициализация PaymentOptimizer
        PaymentOptimizer optimizer = new PaymentOptimizer(methods);

        // Оптимизация платежей
        Map<String, Double> result = optimizer.optimize(orders).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().doubleValue()));

        // Вывод результата
        result.forEach((method, amount) ->
                System.out.println(method + " " + BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_HALF_UP))
        );
    }
}