import {Component, ComponentFactory, ComponentFactoryResolver, Input, OnInit, Type, ViewChild} from '@angular/core';
import {ViewContainerDirective} from './view-container.directive';

/**
 * https://angular.io/guide/dynamic-component-loader
 */

@Component({
  selector: 'app-dyn',
  template: `<ng-template appViewContainer></ng-template>`,
})
export class AnyComponent implements OnInit {

  @Input()
  type: Type<any>;
  @ViewChild(ViewContainerDirective)
  host: ViewContainerDirective;
  hostedComponent: any;

  constructor(protected componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit() {
    const componentRef = this.host.viewContainerRef.createComponent(this.resolveComponentFactory());
    this.hostedComponent = componentRef.instance;
    this.initializeHostedComponent();
  }

  protected resolveComponentFactory(): ComponentFactory<any> {
    return this.componentFactoryResolver.resolveComponentFactory(this.type);
  }

  protected initializeHostedComponent(): void {
  }
}
