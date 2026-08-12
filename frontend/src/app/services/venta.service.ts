import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ItemVentaDTO {
  idMedicamento: number;
  cantidad: number;
}

export interface VentaRequest {
  idPaciente: number;
  idMedico: number;
  items: ItemVentaDTO[];
  observaciones?: string;
}

export interface VentaResponse {
  idVenta: number;
  fecha: string;
  idPaciente: number;
  idMedico: number;
  total: number;
  estado: string;
  observaciones?: string;
}

@Injectable({ providedIn: 'root' })
export class VentaService {
  private apiUrl = 'https://clinica-backend-nzqw.onrender.com/api/ventas';

  constructor(private http: HttpClient) { }

  // Obtener todas las ventas
  getVentas(): Observable<VentaResponse[]> {
    return this.http.get<VentaResponse[]>(this.apiUrl);
  }

  // Procesar una nueva venta (POST)
  procesarVenta(venta: VentaRequest): Observable<VentaResponse> {
    return this.http.post<VentaResponse>(this.apiUrl, venta);
  }

  // Anular una venta (DELETE)
  anularVenta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}