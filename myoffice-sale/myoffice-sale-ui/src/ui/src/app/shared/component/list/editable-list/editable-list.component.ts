import { BaseListComponent } from '../base-list/base-list.component';
import { EditableListItemComponent } from '../editable-list-item/editable-list-item.component';

export abstract class EditableListComponent<T, I, C extends EditableListItemComponent<T, I>>
extends BaseListComponent<T, I, C> {

  isValid(): boolean {
    const hasInvalid = !!this.findComponent(it => !it.isValid());
    return !hasInvalid;
  }

  getData(): T[] {
    return this.mapComponents(it => it.getData());
  }
}
