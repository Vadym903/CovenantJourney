//package com.example.covenant.journey.api.filters.filtering.lot;
//
//import com.example.covenant.journey.api.filters.models.FilteringType;
//import com.example.covenant.journey.api.filters.models.SearchCriteria;
//import com.example.covenant.journey.api.filters.models.SpecificationBuilder;
//import com.example.covenant.journey.models.Lot.LotEntity;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class LotOwnerSpecificationBuilder implements SpecificationBuilder<LotEntity> {
//
//    public static final List<FilteringType> SUPPORTED_OPERATORS = Arrays.asList(
//            FilteringType.EQUAL);
//
//    @Override
//    public Specification<LotEntity> buildSpecification(SearchCriteria searchCriteria) {
//        return new LotOwnerSpecification(searchCriteria);
//    }
//}