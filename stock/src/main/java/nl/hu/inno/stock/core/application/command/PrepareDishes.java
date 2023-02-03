package nl.hu.inno.stock.core.application.command;

import java.util.List;

public class PrepareDishes {
    private final List<Long> dishIds;

    public PrepareDishes(List<Long> dishIds) {
        this.dishIds = dishIds;
    }

    public List<Long> getDishIds() {
        return dishIds;
    }
}
