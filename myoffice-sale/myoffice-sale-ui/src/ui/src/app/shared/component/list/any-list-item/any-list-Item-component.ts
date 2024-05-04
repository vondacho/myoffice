import {Component, ComponentFactoryResolver, Input} from '@angular/core';
import {AnyComponent} from 'shared/component/dynamic/any.component';
import {Required} from 'shared/util/decorator/required-decorator';
import {Holder} from 'shared/util/holder';

/**
 * https://angular.io/guide/dynamic-component-loader
 */

@Component({
  selector: 'app-any-list-item',
  template: `<ng-template appViewContainer></ng-template>`,
})
export class AnyListItemComponent<T> extends AnyComponent {

  @Input()
  @Required
  data: T;

  constructor(componentFactoryResolver: ComponentFactoryResolver) {
    super(componentFactoryResolver);
  }

  protected initializeHostedComponent(): void {
    this.getHostedComponent().data = this.data;
  }

  protected getHostedComponent(): Holder<T> {
    return this.hostedComponent as Holder<T>;
  }
}
