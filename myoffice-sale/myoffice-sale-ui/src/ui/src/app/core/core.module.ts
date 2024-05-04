import {CommonModule} from '@angular/common';
import {HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import {NgModule, Optional, SkipSelf} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {MaterialComponentsModule} from 'shared/component/material/material-components.module';
import {Notifier} from './notification/notifier.service';
import {LoginComponent} from './auth/component/login/login.component';
import {SignupComponent} from './auth/component/signup/signup.component';
import {AuthService} from './auth/service/auth-service.service';
import {AuthGuard} from './auth/guard/auth-guard.service';
import {RoleGuard} from './auth/guard/role-guard.service';

@NgModule({
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    MaterialComponentsModule,
    ReactiveFormsModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'csrf-token',
      headerName: 'csrf-token'
    })
  ],
  declarations: [
    LoginComponent,
    SignupComponent,
  ],
  exports: [
    LoginComponent,
  ],
  providers: [
    Notifier,
    AuthService,
    AuthGuard,
    RoleGuard,
    // {provide: ErrorHandler, useClass: DefaultErrorHandler}
  ],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule, notifier: Notifier) {
    // Import guard
    if (parentModule) {
      throw new Error(`${parentModule} has already been loaded. Import Core module in the AppModule only.`);
    }
    notifier.initialize();
  }
}
