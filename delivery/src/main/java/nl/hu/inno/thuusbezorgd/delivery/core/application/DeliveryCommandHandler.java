package nl.hu.inno.thuusbezorgd.delivery.core.application;

import nl.hu.inno.thuusbezorgd.delivery.core.application.command.AddOrderToDelivery;
import nl.hu.inno.thuusbezorgd.delivery.core.application.command.ChangeStatusDelivery;
import nl.hu.inno.thuusbezorgd.delivery.core.application.command.NewDelivery;
import nl.hu.inno.thuusbezorgd.delivery.core.application.command.NewRider;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.Rider;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.exception.RiderNotFoundException;
import nl.hu.inno.thuusbezorgd.delivery.core.ports.messaging.DeliveryEventPublisher;
import nl.hu.inno.thuusbezorgd.delivery.core.ports.storage.DeliveryRepository;
import nl.hu.inno.thuusbezorgd.delivery.core.ports.storage.RiderRepository;
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

        Rider rider = riderRepository.findRiderWithLeastDeliveries();

        if (rider != null) {
            Delivery delivery = new Delivery(rider);
            delivery.setOrder(command.getOrder());

            rider.addDelivery(delivery);
            riderRepository.save(rider);
            publishEventsAndSave(delivery);
            return this.deliveryRepository.save(delivery);
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
        publishEventsAndSave(delivery);
        return this.deliveryRepository.save(delivery);
    }

    public Delivery handle(ChangeStatusDelivery command) {
        Delivery delivery = deliveryRepository.findById(command.getId())
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));

        delivery.setStatus(command.isCompleted());
        publishEventsAndSave(delivery);
        return this.deliveryRepository.save(delivery);
    }

    private void publishEventsAndSave(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
    }

}
