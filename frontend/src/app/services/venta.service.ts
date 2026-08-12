import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface VentaResponse {
  idVenta: number;
  fecha: string;
  idPaciente: number;
  idMedico: number;
  total: number;
  estado: string;
}

@Injectable({ providedIn: 'root' })
export class VentaService {
  private apiUrl = 'https://clinica-backend-nzqw.onrender.com/api/ventas';
  constructor(private http: HttpClient) { }
  getVentas(): Observable<VentaResponse[]> {
    return this.http.get<VentaResponse[]>(this.apiUrl);
  }
}