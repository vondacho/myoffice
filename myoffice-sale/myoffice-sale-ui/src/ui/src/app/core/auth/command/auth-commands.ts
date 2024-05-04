import {Credentials} from 'shared/model/auth/credentials';

export class Login {
  static readonly type = 'Command.Login';
  constructor(public credentials: Credentials) {
  }
}

export class Logout {
  static readonly type = 'Command.Logout';
}
