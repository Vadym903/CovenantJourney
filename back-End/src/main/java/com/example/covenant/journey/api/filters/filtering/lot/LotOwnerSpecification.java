//package com.example.covenant.journey.api.filters.filtering.lot;
//
//import com.example.covenant.journey.api.filters.models.FilteringType;
//import com.example.covenant.journey.api.filters.models.SearchCriteria;
//import com.example.covenant.journey.models.Lot.LotEntity;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LotOwnerSpecification implements Specification<LotEntity> {
//
//    private static final long serialVersionUID = 7860218411885895782L;
//    private SearchCriteria searchCriteria;
//
//    public LotOwnerSpecification(SearchCriteria searchCriteria) {
//        this.searchCriteria = searchCriteria;
//    }
//
//    @Override
//    public Predicate toPredicate(Root< LotEntity > root, CriteriaQuery< ? > criteriaQuery, CriteriaBuilder criteriaBuilder) {
//        if (searchCriteria.getValue() != null) {
//            List<Predicate> predicates = new ArrayList<>();
//            if (FilteringType.EQUAL == searchCriteria.getFilteringType()) {
//                predicates.add(criteriaBuilder.equal(root.get("userEntity").get("id"), searchCriteria.getValue()));
//            }
//            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
//        }
//        return null;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof LotOwnerSpecification)) {
//            return false;
//        }
//        if (this == o) {
//            return true;
//        }
//        final LotOwnerSpecification otherObject = (LotOwnerSpecification) o;
//
//        return new EqualsBuilder()
//                .append(this.searchCriteria, otherObject.searchCriteria)
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder()
//                .append(searchCriteria)
//                .hashCode();
//    }
//}