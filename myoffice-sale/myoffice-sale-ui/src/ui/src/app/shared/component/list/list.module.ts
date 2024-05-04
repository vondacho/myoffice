import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MaterialComponentsModule } from 'shared/component/material/material-components.module';
import { DynamicComponentModule } from '../dynamic/dynamic-component.module';
import { AnyListItemComponent } from './any-list-item/any-list-Item-component';
import { MatListComponent } from './mat-list/mat-list.component';

@NgModule({
  imports: [
    CommonModule,
    MaterialComponentsModule,
    DynamicComponentModule
  ],
  declarations: [
    MatListComponent,
    AnyListItemComponent,
  ],
  exports: [
    MatListComponent,
    AnyListItemComponent,
  ],
  providers: [],
})
export class ListModule {
}
