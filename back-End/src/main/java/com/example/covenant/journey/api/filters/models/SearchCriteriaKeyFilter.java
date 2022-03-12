package com.example.covenant.journey.api.filters.models;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchCriteriaKeyFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCriteriaKeyFilter.class);
    private ConversionService conversionService;

    @Autowired
    public SearchCriteriaKeyFilter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public <T> List<SearchCriteria> filtrateCriteria(String searchQuery, List<FilterProperty<T>> filterableProperties) {
        String[] searchParams = searchQuery.split(",");
        List<SearchCriteria> searchCriterias = new ArrayList<>();

        for (String searchParameter : searchParams) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|[!<>_]=?|=)(.*)");
            Matcher matcher = pattern.matcher(searchParameter);
            while (matcher.find()) {
                String key = matcher.group(1);
                String operationStr = matcher.group(2);
                FilteringType operation = FilteringType.fromString(operationStr);
                String value = matcher.group(3);

                Optional<FilterProperty<T>> filterableProperty = filterableProperties.stream().filter(
                        property -> property.getPropertyName().equals(key)
                ).findFirst();

                if (filterableProperty.isPresent()) {
                    Object convertedValue;
                    if ("null".equals(value) || StringUtils.isEmpty(value)) {
                        convertedValue = null;
                    } else {
                        convertedValue = conversionService.convert(value, filterableProperty.get().getType());
                    }
                    // check if a FilterableOperation is supported
                    if (!filterableProperty.get().getFilteringTypes().contains(operation)) {
                        throw new RuntimeException("Operation '" + operation + "' is not supported for property " + key);
                    }
                    searchCriterias.add(new SearchCriteria(key, operation, convertedValue));
                } else {
                    LOGGER.warn("Filtering on property '{}' has been skipped because it's absent in filterableProperties", key);
                }
            }
        }
        return searchCriterias;
    }
}
