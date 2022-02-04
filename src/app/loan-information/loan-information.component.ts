import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-loan-information',
  templateUrl: './loan-information.component.html',
  styleUrls: ['./loan-information.component.css']
})
export class LoanInformationComponent {
  loanInformationForm = this.fb.group({
    company: null,

    existingAccount: [null, Validators.required],

    accountType: [null, Validators.required],
    /* postalCode: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(5)])
    ], */
    collaterals: ['collateral1', Validators.required]
  });

  hasUnitNumber = false;

  accountTypes = [
    {name: 'Individual Account', abbreviation: 'LAI'},
    {name: 'Joint Account', abbreviation: 'LAJ'},
    {name: 'Trust Account', abbreviation: 'LAT'}
  ];

  constructor(private fb: FormBuilder) {}

  onSubmit(): void {
    alert('Thank You for submitting the loan Information!');
  }
}
