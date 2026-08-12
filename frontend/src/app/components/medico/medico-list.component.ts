import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-medico-list',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Médicos</mat-card-title>
      </mat-card-header>
      <mat-card-content>
        <p>Lista de médicos (pendiente de implementación)</p>
      </mat-card-content>
    </mat-card>
  `
})
export class MedicoListComponent { }