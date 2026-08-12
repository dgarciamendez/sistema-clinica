import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Medicamento {
  idMedicamento?: number;
  nombre: string;
  stock: number;
  precioUnitario: number;
}

@Injectable({ providedIn: 'root' })
export class MedicamentoService {
  private apiUrl = 'https://clinica-backend-nzqw.onrender.com/api/medicamentos';
  constructor(private http: HttpClient) { }
  getMedicamentos(): Observable<Medicamento[]> {
    return this.http.get<Medicamento[]>(this.apiUrl);
  }
}