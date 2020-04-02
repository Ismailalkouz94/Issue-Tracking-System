import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
@Injectable()
export class InterceptorService implements HttpInterceptor {

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
    return next.handle(req);
  }
}