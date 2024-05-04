import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Store} from '@ngxs/store';
import {Login} from 'core/auth/command/auth-commands';
import {Credentials} from 'shared/model/auth/credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  fg: FormGroup;

  constructor(private fb: FormBuilder, private store: Store, private router: Router) {
  }

  ngOnInit() {
    this.createForm();
  }

  signIn(): void {
    const credentials: Credentials = this.fg.value as Credentials;
    this.store.dispatch(new Login(credentials)).subscribe(
      () => this.router.navigate(['folders']),
      () => alert('Invalid credentials'));
  }

  private createForm() {
    this.fg = this.fb.group({
      'usernameOrEmail': ['', Validators.required],
      'password': [''],
    });
  }
}
