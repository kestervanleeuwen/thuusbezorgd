package nl.hu.inno.thuusbezorgd.delivery.core.application;

import nl.hu.inno.thuusbezorgd.delivery.core.application.query.*;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.Rider;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.exception.RiderNotFoundException;
import nl.hu.inno.thuusbezorgd.delivery.core.ports.storage.DeliveryRepository;
import nl.hu.inno.thuusbezorgd.delivery.core.ports.storage.RiderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryQueryHandler {
    private final DeliveryRepository deliveryRepository;
    private final RiderRepository riderRepository;

    public DeliveryQueryHandler(DeliveryRepository deliveryRepository, RiderRepository riderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.riderRepository = riderRepository;
    }

    public Delivery handle(GetDeliveryById query) {
        return this.deliveryRepository.findById(query.getId())
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
    }

    public List<Delivery> handle(GetDeliveries query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.deliveryRepository.findAll(sort);
    }

    public Rider handle(GetRiderById query) {
        return this.riderRepository.findById(query.getId()).
                orElseThrow(() -> new RiderNotFoundException("Rider not found"));
    }

    public List<Rider> handle(GetRiders query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.riderRepository.findAll(sort);
    }

    public List<Rider> handle(GetRidersByName query) {
        return this.riderRepository.findAllByName(query.getName());
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
