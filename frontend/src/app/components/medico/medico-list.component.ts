import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-medico-list',
  standalone: true,
  imports: [CommonModule],
  template: `<h2>Médicos</h2><p>Lista de médicos (pendiente)</p>`
})
export class MedicoListComponent { }