import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Dashboard } from './dados-dashboard'


@Service()
export class DashboardService {
  http = inject(HttpClient);
  baseUrL = 'http://localhost:8080/dashboard';

  obterDashBoardMesAtual() : Observable<Dashboard> {
    return this.http.get<Dashboard>(this.baseUrL);
  }
}
