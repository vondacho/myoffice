import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Store} from '@ngxs/store';
import {AuthState} from 'core/auth/state/auth-state';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private store: Store, private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request.clone({
      setHeaders: {Authorization: `Bearer ${this.store.selectSnapshot(AuthState.token)}`}
    })).pipe(
      tap(
        (response: HttpEvent<any>) => {
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              this.router.navigate(['login']);
            }
          }
        }
      )
    );
  }
}
