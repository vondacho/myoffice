import {Directive, ViewContainerRef} from '@angular/core';

/**
 * https://angular.io/guide/dynamic-component-loader
 */

@Directive({
  selector: '[appViewContainer]'
})
export class ViewContainerDirective {
  constructor(public viewContainerRef: ViewContainerRef) {
  }
}
