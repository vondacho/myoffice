import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AnyComponent} from './any.component';
import { ViewContainerDirective } from './view-container.directive';

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [
    AnyComponent,
    ViewContainerDirective
  ],
  exports: [
    AnyComponent,
    ViewContainerDirective
  ],
  providers: [],
})
export class DynamicComponentModule {
}
