import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { retry, catchError } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';
@Injectable()
export class InterceptorService implements HttpInterceptor {
  constructor(
    private toastr: ToastrService,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token: string
    if (localStorage.getItem("token") === null) {
      token = "";
    } else {
      token = localStorage.getItem("token")
    }
    let reqUrl = environment.apiBaseUrl;
    req = req.clone({
      setHeaders: { "Authorization": "Bearer " + token },
      url: reqUrl + "" + req.url
    });
    return next.handle(req).pipe(
      // retry(1),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // 401 handled in auth.interceptor
          // this.toastr.error(error.error.errMsg); 
          this.router.navigate(['/login', { isError: true, errMsg: error.error.errMsg, errDetail: error.error.errDetail}]);
          this.authenticationService.logout();

        }
        return throwError(error);
      })
    );
  }
}