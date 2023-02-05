package nl.hu.inno.stock.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PrepareRequest {
    @NotNull
    public List<Long> dishIds;
}
