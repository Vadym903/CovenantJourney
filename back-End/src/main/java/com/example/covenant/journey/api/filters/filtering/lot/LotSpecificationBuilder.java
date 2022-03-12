//package com.example.covenant.journey.api.filters.filtering.lot;
//
//import com.example.covenant.journey.api.filters.filtering.shared.contain.ContainingSpecificationBuilder;
//import com.example.covenant.journey.api.filters.filtering.shared.equal.EqualingSpecificationBuilder;
//import com.example.covenant.journey.api.filters.models.EntityFilterSpecificationsBuilder;
//import com.example.covenant.journey.api.filters.models.FilterProperty;
//import com.example.covenant.journey.models.Lot.LotCategory;
//import com.example.covenant.journey.models.Lot.LotEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class LotSpecificationBuilder implements EntityFilterSpecificationsBuilder<LotEntity> {
//
//    public static final List<FilterProperty<LotEntity>> FILTERABLE_PROPERTIES = Arrays.asList(
//            new FilterProperty<>("user", Long.class,
//                    LotOwnerSpecificationBuilder.SUPPORTED_OPERATORS, new LotOwnerSpecificationBuilder()),
//            new FilterProperty<LotEntity>("title", String.class,
//                    ContainingSpecificationBuilder.SUPPORTED_OPERATORS, new ContainingSpecificationBuilder<>()),
//            new FilterProperty<LotEntity>("lotCategory", LotCategory.class,
//                    EqualingSpecificationBuilder.SUPPORTED_OPERATORS, new EqualingSpecificationBuilder<>())
//    );
//
//    @Override
//    public List<FilterProperty<LotEntity>> getFilterableProperties() {
//        return FILTERABLE_PROPERTIES;
//    }
//}