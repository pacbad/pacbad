import {Injectable} from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class AuthHttpInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let header: string = this.getToken();
    if (header) {
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + header)
      });
    }

    return next.handle(req).catch(err => {
      let handled: boolean = false;
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401 && err.url.indexOf('/auth/login') === -1) {
          // JWT expired, go to login
          // Observable.throw(err);
          localStorage.removeItem('currentUser');
          window.location.href = '/login?redirect=' + window.location.href;
          handled = true;
        }
        if (err.status === 403) {
          // Accès interdit
          window.location.href = '/acces-interdit';
          handled = true;
        }
      }
      return new Observable(observer => {
        if (!handled) {
          observer.error(err);
        }
      });
    });
  }

  getToken(): string {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      return currentUser.token;
    }
    return null;
  }
}
