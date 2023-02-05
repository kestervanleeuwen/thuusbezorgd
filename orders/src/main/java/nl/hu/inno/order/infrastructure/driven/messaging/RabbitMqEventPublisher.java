package nl.hu.inno.order.infrastructure.driven.messaging;

import nl.hu.inno.order.core.domain.event.OrderEvent;
import nl.hu.inno.order.core.ports.messaging.OrderEventPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String thuusbezorgdExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.thuusbezorgdExchange = jobBoardExchange;
    }

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(thuusbezorgdExchange, event.getEventKey(), event);
    }
}

