import { FilteringOperation } from '../shared/constants/filtering-operations.constants';

export class Filter {

    constructor(
        public key: string,
        public operation: FilteringOperation,
        public value: string
        ) { }

}
