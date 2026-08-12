import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VentaService, ItemVentaDTO, VentaRequest } from '../../services/venta.service';

@Component({
  selector: 'app-venta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>Nueva Venta</h2>

    <div>
      <label>Paciente ID:
        <input type="number" [(ngModel)]="venta.idPaciente" />
      </label>
    </div>

    <div>
      <label>Médico ID:
        <input type="number" [(ngModel)]="venta.idMedico" />
      </label>
    </div>

    <hr />

    <div>
      <label>Medicamento ID:
        <input type="number" #medId />
      </label>
      <label>Cantidad:
        <input type="number" #cant />
      </label>
      <button (click)="agregarItem(medId.value, cant.value)">Agregar Item</button>
    </div>

    <ul>
      <li *ngFor="let item of venta.items; let i = index">
        Medicamento {{ item.idMedicamento }} x {{ item.cantidad }}
        <button (click)="eliminarItem(i)">X</button>
      </li>
    </ul>

    <hr />

    <button (click)="procesar()">Procesar Venta</button>

    <div *ngIf="resultado">
      <h3>Resultado:</h3>
      <pre>{{ resultado | json }}</pre>
    </div>
  `
})
export class VentaComponent {
  venta: VentaRequest = {
    idPaciente: 0,
    idMedico: 0,
    items: [],
    observaciones: ''
  };

  resultado: any = null;

  constructor(private ventaService: VentaService) { }

  agregarItem(medId: string, cant: string) {
    const id = Number(medId);
    const cantidad = Number(cant);
    if (id > 0 && cantidad > 0) {
      this.venta.items.push({ idMedicamento: id, cantidad });
    } else {
      alert('Ingresa valores válidos');
    }
  }

  eliminarItem(index: number) {
    this.venta.items.splice(index, 1);
  }

  procesar() {
    if (this.venta.idPaciente === 0 || this.venta.idMedico === 0 || this.venta.items.length === 0) {
      alert('Completa todos los campos y agrega al menos un medicamento');
      return;
    }

    this.ventaService.procesarVenta(this.venta).subscribe({
      next: (res) => {
        this.resultado = res;
        // Reiniciar el formulario (opcional)
        this.venta = { idPaciente: 0, idMedico: 0, items: [], observaciones: '' };
      },
      error: (err) => {
        this.resultado = { error: err.message };
        console.error(err);
      }
    });
  }
}