import { Component, inject, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard-service';
import { Observable } from 'rxjs';
import { Dashboard } from '../dados-dashboard';
import { Header } from '../../common/components/header/header';
import { CommonModule } from '@angular/common';

@Component({
  imports: [Header, CommonModule],
  selector: 'app-dashboard-page',
  styleUrl: './dashboard-page.css',
  templateUrl: './dashboard-page.html',
})
export class DashboardPage implements OnInit {
  service = inject(DashboardService);
  dashboard$!: Observable<Dashboard>;

  ngOnInit(): void {
    this.dashboard$ = this.service.obterDashBoardMesAtual();
  }

  percentual(valor: number, total: number) {
    if (!total) {
      return 0;
    }

    const resultado = (valor / total) * 100;
    return Math.round(resultado);
  }
}
