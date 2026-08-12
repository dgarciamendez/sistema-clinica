import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <h1>Gestión Clínica</h1>
    <nav>
      <a routerLink="/pacientes">Pacientes</a> |
      <a routerLink="/medicos">Médicos</a> |
      <a routerLink="/medicamentos">Medicamentos</a> |
      <a routerLink="/venta">Venta</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent { }