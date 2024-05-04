import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {Store} from '@ngxs/store';
import {AuthState} from 'core/auth/state/auth-state';

@Injectable()
export class RoleGuard {

  constructor(private store: Store, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const authenticated: boolean | undefined = this.store.selectSnapshot(AuthState.isAuthenticated)();
    const hasRole: boolean | undefined = authenticated && this.store.selectSnapshot(AuthState.hasRole)(expectedRole);

    if (!hasRole) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}
