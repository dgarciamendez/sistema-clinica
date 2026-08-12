import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-medicamento-list',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Medicamentos</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        <p>Lista de medicamentos (pendiente de implementación)</p>
      </mat-card-content>
    </mat-card>
  `
})
export class MedicamentoListComponent { }