import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';  // Ruta correcta

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));