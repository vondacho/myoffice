import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs';

/**
 * Data source for the MainTable view.
 */
export class ListDataSource<T> extends DataSource<T> {

  constructor(private data$: Observable<T[]>) {
    super();
  }

  /**
   * Connect this edu.noia.myoffice.customer.data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<T[]> {
    return this.data$;
  }

  /**
   * Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {
  }
}
