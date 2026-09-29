import { Component, OnInit, inject } from '@angular/core';
import { CartaoService } from '../cartao-service';
import { Observable } from 'rxjs';
import { PageResult } from '../../common/pagnation/page-result';
import { DetalhesCartao } from '../dados-cartao';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  imports: [CommonModule],
  selector: 'app-listagem-cartoes',
  styleUrl: './listagem-cartoes.css',
  templateUrl: './listagem-cartoes.html',
})
export class ListagemCartoes implements OnInit {
  service = inject(CartaoService);
  router = inject(Router);
  listagem$!: Observable<PageResult<DetalhesCartao>>;
  paginaAtual = 0;
  tamanhoPagina = 3;

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

  navegarProximo(listagem: PageResult<DetalhesCartao>) {
    if (!listagem.last) {
      this.navegar(listagem.number + 1);
    }
  }

  navegarAnterior(listagem: PageResult<DetalhesCartao>) {
    if (!listagem.first) {
      this.navegar(listagem.number - 1);
    }
  }

  paginas(totalPages: number): number[] {
    return Array.from({ length: totalPages }, (valor, index) => index);
  }

  registroInicial(listagem: PageResult<DetalhesCartao>) {
    if (listagem.totalElements === 0) {
      return 0;
    }
    return listagem.number * listagem.size + 1;
  }

  registroFinal(listagem: PageResult<DetalhesCartao>) {
    if (listagem.totalElements === 0) {
      return 0;
    }
    return Math.min((listagem.number + 1) * listagem.size, listagem.totalElements);
  }

  prepararEdicao(idCartao: string) {
    this.router.navigate(['/paginas/cadastro-cartoes'], {
      queryParams: {
        id: idCartao,
      },
    });
  }
}
