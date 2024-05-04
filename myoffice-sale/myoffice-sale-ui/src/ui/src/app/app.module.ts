import {APP_INITIALIZER, NgModule} from '@angular/core';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material';
import {
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
  MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter
} from '@angular/material-moment-adapter';
import {BrowserModule} from '@angular/platform-browser';
import {NgxsModule} from '@ngxs/store';
import {NgxsStoragePluginModule, StorageOption} from '@ngxs/storage-plugin';
import {MaterialComponentsModule} from 'shared/component/material/material-components.module';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from 'core/core.module';
import {AuthState} from 'core/auth/state/auth-state';
import {environment} from 'environments/environment';
import {TokenInterceptor} from 'core/auth/interceptor/token-interceptor';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {FaultyResponseInterceptor} from 'core/http/interceptor/faulty-response-interceptor';
import {DirtyRequestInterceptor} from 'core/http/interceptor/dirty-request-interceptor';
import {XhrInterceptor} from 'core/http/interceptor/xhr-interceptor';
import {ConfigurationService} from 'core/configuration/configuration.service';
import {LastProblemState} from 'core/problem/state/last-problem-state';
import { Notifier } from 'core/notification/notifier.service';

@NgModule({
  imports: [
    BrowserModule,
    CoreModule,
    AppRoutingModule,
    MaterialComponentsModule,
    NgxsModule.forRoot([AuthState, LastProblemState], {
      developmentMode: !environment.production
    }),
    NgxsStoragePluginModule.forRoot({
      key: ['auth.token', 'auth.expiresAt'],
      storage: StorageOption.LocalStorage
    })
  ],
  declarations: [
    AppComponent,
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'fr'},
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}},
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: FaultyResponseInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: DirtyRequestInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: XhrInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: loadConfigurations,
      deps: [ConfigurationService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function loadConfigurations(configService: ConfigurationService) {
  return () => configService.getConfigs();
}

export function initializeNotifier(notifier: Notifier) {
  return () => notifier.initialize();
}
