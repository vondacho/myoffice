import {NgModule} from '@angular/core';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatOptionModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatSidenavModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {LayoutModule} from '@angular/cdk/layout';
import {MatMomentDateModule} from '@angular/material-moment-adapter';

const MODULES = [
  LayoutModule,
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatGridListModule,
  MatCardModule,
  MatMenuModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatExpansionModule,
  MatOptionModule,
  MatDatepickerModule,
  MatAutocompleteModule,
  MatSelectModule,
  MatCheckboxModule,
  MatSnackBarModule,
  MatMomentDateModule,
];

@NgModule({
  exports: MODULES,
  declarations: []
})
export class MaterialComponentsModule {
}
