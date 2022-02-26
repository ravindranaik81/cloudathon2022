import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { DOCUMENT } from '@angular/common';

import { User } from './_models';
import { AccountService } from './_services';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  user: User = new User;  
  form!: FormGroup;
  loading = false;
  submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private accountService: AccountService,
      @Inject(DOCUMENT) private document: Document
  ) { }


  ngOnInit() {
      this.form = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required]
      }); 
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
        return;
    }

    this.loading = true;
    this.accountService.login(this.f['username'].value, this.f['password'].value)
        .pipe(first())
        .subscribe({
            next: (user) => {
                if(user.username == 'opsUser'){
                  alert("This is an authenticated opsUser")
                  //Redirect to ops micro front end url
                  //this.document.location.href = 'https://stackoverflow.com';
                } else if(user.username == 'creditUser'){
                  alert("This is an authenticated creditUser")
                  //Redirect to Credit micro front end url
                  //this.document.location.href = 'https://stackoverflow.com';
                } else {
                  alert("This is an unauthenticated user. Try again")
                }
            },
            error: error => {
                this.loading = false;
            }
        });
  } 
}
