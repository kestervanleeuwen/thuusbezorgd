package nl.hu.inno.delivery.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.delivery.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.thuusbezorgd}")
    private String thuusbezorgdExchange;

    @Value("${messaging.queue.order-delivery}")
    private String orderDeliveryQueueName;
    @Value("${messaging.queue.delivery-order}")
    private String deliveryOrderQueueName;

    @Value("${messaging.routing-key.order-delivery}")
    private String orderDeliverRoutingKey;
    @Value("${messaging.routing-key.delivery-order}")
    private String deliveryOrderRoutingKey;
    @Bean
    public TopicExchange jobBoardExchange() {
        return new TopicExchange(thuusbezorgdExchange);
    }

    @Bean
    public Queue orderDeliveryQueue() {
        return QueueBuilder.durable(orderDeliveryQueueName).build();
    }

    @Bean
    public Binding candidatesKeywordsBinding() {
        return BindingBuilder
                .bind(orderDeliveryQueue())
                .to(jobBoardExchange())
                .with(orderDeliverRoutingKey);
    }

    @Bean
    public Queue deliveryOrderQueue() {
        return QueueBuilder.durable(deliveryOrderQueueName).build();
    }

    @Bean
    public Binding deliveryOrderBinding() {
        return BindingBuilder
                .bind(deliveryOrderQueue())
                .to(jobBoardExchange())
                .with(deliveryOrderRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, thuusbezorgdExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}