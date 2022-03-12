package com.example.covenant.journey.api.filters.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SearchCriteria {

    private final String key;
    private final FilteringType filteringType;
    private final Object value;

    public SearchCriteria(String key, FilteringType filteringType, Object value) {
        this.key = key;
        this.filteringType = filteringType;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public FilteringType getFilteringType() {
        return filteringType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return value == null ? null : value.getClass();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getKey());
        builder.append(getFilteringType());
        builder.append(getValue());
        return builder.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SearchCriteria)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final SearchCriteria otherObject = (SearchCriteria) o;
        return new EqualsBuilder()
                .append(this.key, otherObject.key)
                .append(this.filteringType, otherObject.filteringType)
                .append(this.value, otherObject.value)
                .isEquals();
    }

    @Override
    public String toString() {
        return "SearchCriteria[" + "key='" + key + '\'' + ", filteringType='" + filteringType + '\'' + ", value=" + value + ']';
    }
}