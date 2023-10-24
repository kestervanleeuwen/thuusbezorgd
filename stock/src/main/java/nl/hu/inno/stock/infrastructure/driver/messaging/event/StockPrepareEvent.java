package nl.hu.inno.stock.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class StockPrepareEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public List<Long> dishIds;
}

