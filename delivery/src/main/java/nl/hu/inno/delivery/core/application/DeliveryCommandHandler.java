package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.command.AddOrderToDelivery;
import nl.hu.inno.delivery.core.application.command.FinishDelivery;
import nl.hu.inno.delivery.core.application.command.NewDelivery;
import nl.hu.inno.delivery.core.application.command.NewRider;
import nl.hu.inno.delivery.core.domain.Delivery;
import nl.hu.inno.delivery.core.domain.Rider;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.hu.inno.delivery.core.domain.exception.RiderNotFoundException;
import nl.hu.inno.delivery.core.ports.messaging.DeliveryEventPublisher;
import nl.hu.inno.delivery.core.ports.storage.DeliveryRepository;
import nl.hu.inno.delivery.core.ports.storage.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryCommandHandler {
    private final DeliveryRepository deliveryRepository;
    private final RiderRepository riderRepository;
    private final DeliveryEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, RiderRepository riderRepository, DeliveryEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.riderRepository = riderRepository;
        this.eventPublisher = eventPublisher;
    }

    public Delivery handle(NewDelivery command) {

        Rider rider = riderRepository.findOneRandomRider();

        if (rider != null) {
            Delivery delivery = new Delivery(rider);
            delivery.setOrder(command.getOrder());

            Delivery savedDelivery = this.deliveryRepository.save(delivery);
            savedDelivery.orderReceived(savedDelivery.getId());
            publishEvents(savedDelivery);
            return savedDelivery;
        } else {
            throw new RiderNotFoundException("No riders available");
        }
    }

    public Rider handle(NewRider command) {
        Rider rider = new Rider(command.getName());
        return this.riderRepository.save(rider);
    }

    public Delivery handle(AddOrderToDelivery command) {
        Delivery delivery = deliveryRepository.findById(command.getDeliveryId())
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));

        delivery.setOrder(command.getOrderId());
        publishEvents(delivery);
        return this.deliveryRepository.save(delivery);
    }

    public Delivery handle(FinishDelivery command) {
        Delivery delivery = deliveryRepository.findById(command.getId())
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));

        delivery.markCompleted();
        publishEvents(delivery);
        return this.deliveryRepository.save(delivery);
    }

    private void publishEvents(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }

}
