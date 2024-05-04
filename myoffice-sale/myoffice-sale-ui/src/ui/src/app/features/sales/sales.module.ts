import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialComponentsModule } from 'shared/component/material/material-components.module';
import { SalesRootComponent } from './component/sales-root/sales-root.component';
import { SalesRoutingModule } from './sales-routing.module';
import { FolderSelectorComponent } from './component/folder-selector/folder-selector.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialComponentsModule,
    SalesRoutingModule,
  ],
  declarations: [
    SalesRootComponent,
    FolderSelectorComponent
  ],
  exports: [
    SalesRootComponent,
  ],
  entryComponents: [
  ],
  providers: [
  ]
})
export class SalesModule { }
