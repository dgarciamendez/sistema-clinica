import { Routes } from '@angular/router';
import { PacienteListComponent } from './components/paciente/paciente-list.component';
import { MedicoListComponent } from './components/medico/medico-list.component';
import { MedicamentoListComponent } from './components/medicamento/medicamento-list.component';
import { VentaComponent } from './components/venta/venta.component';

export const routes: Routes = [
    { path: 'pacientes', component: PacienteListComponent },
    { path: 'medicos', component: MedicoListComponent },
    { path: 'medicamentos', component: MedicamentoListComponent },
    { path: 'venta', component: VentaComponent },
    { path: '', redirectTo: '/pacientes', pathMatch: 'full' }
];