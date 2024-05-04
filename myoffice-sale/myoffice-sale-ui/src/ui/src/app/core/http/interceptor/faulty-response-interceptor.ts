import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notifier } from 'core/notification/notifier.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const NO_AUTH_CODE = 401;
const NOT_AUTHORIZED_CODE = 403;
const DOMAIN_PROBLEM_CODE = 400;

@Injectable()
export class FaultyResponseInterceptor implements HttpInterceptor {

  constructor(private notifier: Notifier) { // FIXME to replace by Store(LastProblemState)
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(tap(event => event, error => this.handleError(error, this.notifier)));
  }

  private handleError(error: HttpErrorResponse, notifier: Notifier) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      notifier.failure(error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      this.handleHttpError(error, notifier);
    }
  }

  private handleHttpError(error: HttpErrorResponse, notifier: Notifier) {
    switch (error.status) {
      case NO_AUTH_CODE:
        break;
      case NOT_AUTHORIZED_CODE:
        break;
      case DOMAIN_PROBLEM_CODE:
        break;
      default:
        break;
    }
    notifier.failure(error.message);
  }
}
