import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {JsonCleaner} from 'shared/util/cleaner/json-cleaner';

@Injectable()
export class DirtyRequestInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req.clone({body: JsonCleaner.clean(req.body)}));
  }
}
