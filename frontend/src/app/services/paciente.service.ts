
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Paciente {
  idPaciente?: number;
  nombre: string;
  apellido: string;
  email: string;
  fechaNacimiento: string;
  genero: string;
  direccion?: string;
  telefono?: string;
}

@Injectable({ providedIn: 'root' })
export class PacienteService {
  private apiUrl = 'https://clinica-backend-nzqw.onrender.com/api/pacientes';

  constructor(private http: HttpClient) { }

  getPacientes(): Observable<Paciente[]> {
    return this.http.get<Paciente[]>(this.apiUrl);
  }

  createPaciente(paciente: Paciente): Observable<Paciente> {
    return this.http.post<Paciente>(this.apiUrl, paciente);
  }

  updatePaciente(id: number, paciente: Paciente): Observable<Paciente> {
    return this.http.put<Paciente>(`${this.apiUrl}/${id}`, paciente);
  }

  deletePaciente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}