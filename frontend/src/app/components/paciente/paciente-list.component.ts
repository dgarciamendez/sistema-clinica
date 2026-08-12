import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacienteService, Paciente } from '../../services/paciente.service';

@Component({
  selector: 'app-paciente-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Pacientes</h2>
    <ul>
      <li *ngFor="let p of pacientes">
        {{ p.nombre }} {{ p.apellido }} - {{ p.email }}
        <button (click)="eliminar(p.idPaciente!)">Eliminar</button>
      </li>
    </ul>
  `
})
export class PacienteListComponent implements OnInit {
  pacientes: Paciente[] = [];

  constructor(private pacienteService: PacienteService) { }

  ngOnInit() {
    this.pacienteService.getPacientes().subscribe(data => this.pacientes = data);
  }

  eliminar(id: number) {
    this.pacienteService.deletePaciente(id).subscribe(() => {
      this.pacientes = this.pacientes.filter(p => p.idPaciente !== id);
    });
  }
}