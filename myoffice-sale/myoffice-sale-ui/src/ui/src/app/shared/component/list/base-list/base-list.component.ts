import { EventEmitter, Input, Output, Predicate, QueryList, ViewChildren } from '@angular/core';
import { Consumer, ifDefinedDo, Optional } from 'shared/util/functional';
import { Holder } from 'shared/util/holder';
import { Identity } from 'shared/util/identity';
import { BaseListItemComponent } from '../base-list-item/base-list-item.component';

type Model<T, I> = Holder<T> & Identity<I>;

export abstract class BaseListComponent<T, I, C extends BaseListItemComponent<T, I>> {

  items: Model<T, I>[] = [];

  @Output('onAdd') addEventPublisher = new EventEmitter();
  @Output('onRemove') removeEventPublisher = new EventEmitter();

  @ViewChildren('listItem')
  private itemComponents: QueryList<C>;

  @Input()
  set data(dataSet: T[]) {
    this.setData(dataSet);
  }

  trackByItemId(index: number, item: Model<T, I>): I {
    return item.id;
  }

  setData(dataSet: T[]) {
    this.items = dataSet.map(data => this.toModel(data));
  }

  getData(): T[] {
    return this.items.map(it => it.data);
  }

  removeData(id: I): void {
    ifDefinedDo(this.items.find(it => it.id === id), item => {
      this.items = this.items.filter(it => it.id !== id);
      this.removeEventPublisher.emit(item.data);
    });
  }

  addData(data: T): void {
    this.items.push(this.toModel(data));
    this.addEventPublisher.emit(data);
    this.sort();
  }

  isEmpty(): boolean {
    return this.items.length === 0;
  }

  protected mapComponents<U>(fn: (c: C) => U): U[] {
      return this.itemComponents ? this.itemComponents.map(it => fn(it)) : [];
  }

  protected forEachComponent(fn: Consumer<C>): void {
    if (this.itemComponents) {
      this.itemComponents.forEach(it => fn(it));
    }
  }

  protected findComponent(predicate: Predicate<C>): Optional<C> {
    return this.itemComponents && this.itemComponents.find(it => predicate(it));
  }

  abstract sort(): void;
  abstract toModel(data: T): Holder<T> & Identity<I>;
}

