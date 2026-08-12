import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Medico {
  idMedico?: number;
  nombre: string;
  apellido: string;
  especialidad: string;
  email: string;
}

@Injectable({ providedIn: 'root' })
export class MedicoService {
  private apiUrl = 'https://clinica-backend-nzqw.onrender.com/api/medicos';
  constructor(private http: HttpClient) { }
  getMedicos(): Observable<Medico[]> {
    return this.http.get<Medico[]>(this.apiUrl);
  }
}