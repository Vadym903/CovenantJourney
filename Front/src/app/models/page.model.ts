export class Page<T> {
  public items?: T[];
  public count?: number;

  constructor(
    items?: T[],
    count?: number
  ) {
    this.items = items;
    this.count = count;
  }
}
