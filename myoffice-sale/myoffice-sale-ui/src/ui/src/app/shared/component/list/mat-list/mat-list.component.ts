import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, Sort, SortDirection} from '@angular/material';
import {PageEvent} from '@angular/material/paginator/typings/paginator';
import {EMPTY, Observable, Subscription} from 'rxjs';
import {containsOf, SingleFilter} from 'shared/model/common/filter';
import {PageInfo, pageOf} from 'shared/model/common/pageable';
import {Operation} from 'shared/model/common/resource';
import {SingleSort, sortOf} from 'shared/model/common/sort';
import {Required} from 'shared/util/decorator/required-decorator';
import {Holder} from 'shared/util/holder';
import {ListDataSource} from './mat-list-datasource';

@Component({
  selector: 'app-list',
  templateUrl: './mat-list.component.html',
  styleUrls: ['./mat-list.component.css'],
})
export class MatListComponent<T> implements OnInit, OnDestroy {

  @ViewChild(MatPaginator) matPaginator: MatPaginator;
  @ViewChild(MatSort) matSort: MatSort;

  @Input() @Required columnNames: string[];
  @Input() @Required datasource: ListDataSource<T>;
  @Input() @Required itemDataComponentTypeFn: (data: T) => Holder<T>;
  @Input() @Required pageInfo$: Observable<PageInfo>;
  @Input() operations$: Observable<Operation[]> = EMPTY;
  @Input() information$: Observable<string> = EMPTY;
  @Input() columnNameKeyPrefix = '';

  @Output('create') createCommandPublisher = new EventEmitter();
  @Output('page') pageCommandPublisher = new EventEmitter();
  @Output('refresh') refreshCommandPublisher = new EventEmitter();
  @Output('search') searchCommandPublisher = new EventEmitter();
  @Output('filter') filterCommandPublisher = new EventEmitter();
  @Output('sort') sortCommandPublisher = new EventEmitter();

  private subscriptions: Subscription[] = [];
  private filters: SingleFilter[] = [];
  private sorts: SingleSort[] = [];

  ngOnInit() {
    this.subscriptions = [
      this.matPaginator.page.subscribe((event: PageEvent) => this.onPageEvent(event)),
      this.matSort.sortChange.subscribe((event: Sort) => this.onSortEvent(event)),
      this.pageInfo$.subscribe(page => this.setPaginator(page)),
      this.information$.subscribe(info => this.showInformation(info)),
      this.operations$.subscribe(operations => this.activateOperations(operations))];
  }

  ngOnDestroy() {
    this.subscriptions.forEach(s => s.unsubscribe());
    this.subscriptions = [];
  }

  applyFilter(column: string, value: string): void {
    this.addFilter(column, value);
    this.filterCommandPublisher.emit([pageOf(0, this.matPaginator.pageSize), [...this.filters], [...this.sorts]]);
  }

  applyGlobalFilter(value: string): void {
    this.searchCommandPublisher.emit([value, pageOf(0, this.matPaginator.pageSize), [...this.sorts]]);
  }

  sort(column: string, direction: SortDirection): void {
    this.matSort.sortChange.emit(<Sort>{active: column, direction: direction});
  }

  create(): void {
    this.createCommandPublisher.emit();
  }

  refresh(): void {
    this.removeSorts();
    this.removeFilters();
    this.refreshData();
  }

  private refreshData(): void {
    this.refreshCommandPublisher.emit(pageOf(0, this.matPaginator.pageSize));
  }

  private addFilter(column: string, value: string): void {
    this.filters.push(containsOf(column, value));
  }

  private removeFilters(): void {
    this.filters = [];
  }

  private removeSorts(): void {
    this.sorts = [];
  }

  private onPageEvent(event: PageEvent): void {
    this.pageCommandPublisher.emit([pageOf(event.pageIndex, event.pageSize), [...this.filters], [...this.sorts]]);
  }

  private onSortEvent(event: Sort): void {
    this.addSort(event.active, event.direction);
    this.sortData();
  }

  private addSort(column: string, direction: SortDirection): void {
    this.sorts = [sortOf(column, direction)];
  }

  private sortData(): void {
    this.sortCommandPublisher.emit([pageOf(0, this.matPaginator.pageSize), [...this.filters], [...this.sorts]]);
  }

  private setPaginator(page: PageInfo): void {
    this.matPaginator.pageIndex = page.number;
    this.matPaginator.pageSize = page.size;
    this.matPaginator.length = page.totalElements;
  }

  private activateOperations(actions: string[]): void {
  }

  private showInformation(info: string): void {
  }
}
