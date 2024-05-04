import { Input, Output, EventEmitter } from '@angular/core';
import { Required } from 'shared/util/decorator/required-decorator';

export abstract class BaseListItemComponent<T, I> {

  @Input() @Required protected data: T;
  @Input() @Required protected id: I;
  @Output() onRemove: EventEmitter<I> = new EventEmitter<I>();

  getData(): T {
    return this.data;
  }

  remove(): void {
    this.onRemove.emit(this.id);
  }
}
