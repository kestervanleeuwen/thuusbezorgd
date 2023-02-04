package nl.hu.inno.delivery.infrastructure.driven.messaging;

import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.ports.messaging.DeliveryEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DeliveryEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String thuusbezorgdExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.thuusbezorgdExchange = jobBoardExchange;
    }

    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(thuusbezorgdExchange, event.getEventKey(), event);
    }
}
