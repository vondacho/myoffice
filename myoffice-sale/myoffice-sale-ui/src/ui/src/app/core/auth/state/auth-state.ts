import {Action, Selector, State, StateContext} from '@ngxs/store';
import {tap} from 'rxjs/operators';
import {UserInfo} from 'shared/model/auth/user';
import {Login, Logout} from 'core/auth/command/auth-commands';
import {AuthInfo, AuthService} from 'core/auth/service/auth-service.service';

export interface AuthStateModel {
  token?: string;
  userInfo?: UserInfo;
  expiresAt?: string;
}

@State<AuthStateModel>({
  name: 'auth',
  defaults: {}
})
export class AuthState {

  constructor(private authService: AuthService) {
  }

  @Selector()
  static isAuthenticated(state: AuthStateModel) {
    return () => true;
  }

  @Selector()
  static xxisAuthenticated(state: AuthStateModel) {
    // Get the time the token expires
    const expiresAt: string | undefined = state.expiresAt;
    // If there's no expiresAt value, make
    // the user log in
    if (!expiresAt) {
      return false;
    }
    // Our indication as to whether the user is authenticated or not
    // is if they have an unexpired token. Return a boolean that compares
    // the current time with the token expiry time. The expiresAt value
    // needs to be parsed because it is stored as a string
    // tslint:disable-next-line:radix
    return new Date().getTime() < parseInt(expiresAt);
  }

  @Selector()
  static roles(state: AuthStateModel) {
    return state.userInfo && state.userInfo.roles;
  }

  @Selector()
  static hasRole(state: AuthStateModel) {
    return (expectedRole: string) => {
      return state.userInfo && (state.userInfo.roles.filter(role => role === expectedRole).length > 0);
    };
  }

  @Selector()
  static expiresAt(state: AuthStateModel) {
    return state.expiresAt;
  }

  @Selector()
  static token(state: AuthStateModel) {
    return state.token;
  }

  @Action(Login)
  login(ctx: StateContext<AuthStateModel>, command: Login) {
    return this.authService.login(command.credentials).pipe(
      tap((result: AuthInfo) => ctx.patchState({...result})));
  }

  @Action(Logout)
  logout(ctx: StateContext<AuthStateModel>) {
    const {token} = ctx.getState();
    return this.authService.logout(token).pipe(
      tap(() => ctx.setState({})));
  }
}
