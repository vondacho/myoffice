import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Credentials} from 'shared/model/auth/credentials';
import {UserInfo} from 'shared/model/auth/user';

const ENDPOINT_URL = '/api/auth/v1/token';

export interface AuthInfo {
  token?: string;
  userInfo?: UserInfo;
  expiresAt?: string;
}

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(credentials: Credentials): Observable<AuthInfo> {
    return of({token: 'test', userInfo: {username: 'me', roles: ['user']}, expiredAt: undefined});
  }

  public logout(token: string | undefined): Observable<any> {
    return of({});
  }
}
