import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {Store} from '@ngxs/store';
import {AuthState} from 'core/auth/state/auth-state';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private store: Store, private router: Router) {
  }

  canActivate(): boolean {
    const authenticated: boolean | undefined = this.store.selectSnapshot(AuthState.isAuthenticated)();
    if (!authenticated) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
