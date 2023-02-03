package nl.hu.inno.thuusbezorgd.delivery.core.application.command;

import nl.hu.inno.thuusbezorgd.delivery.core.domain.Rider;

public class NewDelivery {
    private final Long order;

    public NewDelivery(Long order) {
        this.order = order;
    }

    public Long getOrder() {
        return order;
    }
}
