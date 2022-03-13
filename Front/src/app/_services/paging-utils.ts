import { Filter } from '../models/filter-model';
import { KeyValuePair } from '../shared/constants/key-value-pair.model';

export class PagingUtils {

  public static buildFilteringFields(fieldsList: KeyValuePair[]): string {
    return fieldsList.filter(field => field.key && field.value).
      map(value => value.key + value.value).join(',');
  }

  public static buildSortingField(field: string, sortOption: boolean = true): string {
    let sortingValue  = '';
    if (field) {
      sortingValue = field + ',' + (sortOption ? 'DESC' : 'ASC');
    }
    return sortingValue ;
  }

  public static getFilterValue(filter: Filter): string {
    return filter.key + filter.operation + filter.value;
  }

  public static buildSortingFields(fields: string[], sortOption: boolean = true): string[] {
    const sortingValues = [''];
    for (const field of fields) {
      sortingValues.push(this.buildSortingField(field, sortOption));
    }

    return sortingValues.filter(field => field);
  }

}
