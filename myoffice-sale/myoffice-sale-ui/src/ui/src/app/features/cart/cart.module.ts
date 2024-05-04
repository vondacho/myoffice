import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialComponentsModule } from 'shared/component/material/material-components.module';
import { CartRoutingModule } from './cart-routing.module';
import { ListModule } from 'shared/component/list/list.module';
import { CartContentComponent } from './component/cart-content/cart-content.component';
import { CartItemEditComponent } from './component/cart-item-edit/cart-item-edit.component';
import { CartItemComponent } from './component/cart-item/cart-item.component';
import { CartListComponent } from './component/cart-list/cart-list.component';
import { CartShowComponent } from './component/cart-show/cart-show.component';
import { CartComponent } from './component/cart/cart.component';
import { ArticleSelectorComponent } from './component/article-selector/article-selector.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialComponentsModule,
    CartRoutingModule,
    ListModule,
  ],
  declarations: [
    CartContentComponent,
    CartItemEditComponent,
    CartItemComponent,
    CartListComponent,
    CartShowComponent,
    CartComponent,
    ArticleSelectorComponent,
  ],
  exports: [
    CartListComponent,
    CartComponent,
  ],
  entryComponents: [
  ],
  providers: [
  ]
})
export class CartModule { }
