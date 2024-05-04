import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CartComponent } from './component/cart/cart.component';
import { CartListComponent } from './component/cart-list/cart-list.component';

const routes: Routes = [
  {
    path: '',
    component: CartListComponent
  },
  {
    path: ':id',
    component: CartComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class CartRoutingModule {
}
