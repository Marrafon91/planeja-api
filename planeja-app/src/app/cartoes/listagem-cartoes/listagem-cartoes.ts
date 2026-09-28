import { Component, OnInit, inject } from '@angular/core';
import { CartaoService } from '../cartao-service';
import { Observable } from 'rxjs';
import { PageResult } from '../../common/pagnation/page-result';
import { DetalhesCartao } from '../dados-cartao';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule],
  selector: 'app-listagem-cartoes',
  styleUrl: './listagem-cartoes.css',
  templateUrl: './listagem-cartoes.html',
})
export class ListagemCartoes implements OnInit {
  service = inject(CartaoService);
  listagem$!: Observable<PageResult<DetalhesCartao>>;
  paginaAtual = 0;
  tamanhoPagina = 10;

  ngOnInit(): void {
    this.listarCartoes();
  }

  listarCartoes() {
    this.listagem$ = this.service.listar(this.paginaAtual, this.tamanhoPagina);
  }

  navegar(pagina: number) {
    this.paginaAtual = pagina;
    this.listarCartoes();
  }
}
