import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, MatToolbarModule, MatButtonModule],
  template: `
    <mat-toolbar color="primary">
      <span>Gestión Clínica</span>
      <span style="flex: 1 1 auto;"></span>
      <a mat-button routerLink="/pacientes">Pacientes</a>
      <a mat-button routerLink="/medicos">Médicos</a>
      <a mat-button routerLink="/medicamentos">Medicamentos</a>
      <a mat-button routerLink="/venta">Venta</a>
    </mat-toolbar>
    <router-outlet></router-outlet>
  `
})
export class AppComponent { }