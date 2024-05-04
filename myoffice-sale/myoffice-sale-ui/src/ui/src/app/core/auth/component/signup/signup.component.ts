import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public fg: FormGroup;
  public signupLoading = false;
  public emailValidating = false;
  public signupResult: any;

  constructor(public fb: FormBuilder) {
  }

  ngOnInit() {
    this.createForm();
  }

  signUp(): void {

  }

  private createForm(): void {
    this.fg = this.fb.group({
      email: new FormControl('', {
        validators: [Validators.required, Validators.email]
      }),
      username: new FormControl('', {
        validators: [Validators.required]
      }),
      password: new FormControl('', {
        validators: [Validators.required]
      })
    });
  }
}
