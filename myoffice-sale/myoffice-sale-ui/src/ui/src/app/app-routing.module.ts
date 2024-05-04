import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from 'core/auth/component/login/login.component';
import {AuthGuard} from 'core/auth/guard/auth-guard.service';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'sales',
    pathMatch: 'full'
  },
  {
    path: 'sales',
    loadChildren: './features/sales/sales.module#SalesModule',
    canActivate: [AuthGuard]
  },
  {
    path: 'login', component: LoginComponent
  },
  {path: '**', redirectTo: 'carts'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: false, useHash: false})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
