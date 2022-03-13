package com.example.covenant.journey.api.models.general;

import java.util.List;

public class AbstractResponsePage< T > {
    private List< T > items;
    private long count;

    public AbstractResponsePage(List< T > items, long count) {
        this.items = items;
        this.count = count;
    }

    public List< T > getItems() {
        return items;
    }

    public long getCount() {
        return count;
    }
}