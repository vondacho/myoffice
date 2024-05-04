import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SalesRootComponent } from './component/sales-root/sales-root.component';

const routes: Routes = [
  {
    path: '',
    component: SalesRootComponent
  },
  {
    path: 'carts',
    loadChildren: './features/cart/cart.module#CartModule',
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class SalesRoutingModule {
}
