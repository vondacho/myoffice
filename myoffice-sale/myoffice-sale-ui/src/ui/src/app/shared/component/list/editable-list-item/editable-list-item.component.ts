import { BaseListItemComponent } from '../base-list-item/base-list-item.component';

export abstract class EditableListItemComponent<T, I> extends BaseListItemComponent<T, I> {
  abstract isValid(): boolean;
}
