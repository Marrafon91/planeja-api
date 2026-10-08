import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { DadosLancamentosForm, DetalhesLancamento } from './dados-lancamentos';
import { Observable } from 'rxjs';
import { DetalhesCategoria } from '../categorias/dados-categoria';
import { DetalhesCartao } from '../cartoes/dados-cartao';

@Service()
export class LancamentoService {
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/lancamentos';

  criar(dados: DadosLancamentosForm): Observable<DetalhesLancamento> {
    return this.http.post<DetalhesLancamento>(this.baseUrl, dados);
  }

  listarCategoriasDisponiveis(): Observable<DetalhesCategoria[]> {
    return this.http.get<DetalhesCategoria[]>(`${this.baseUrl}/categorias-disponiveis`);
  }

  listarCartoesDisponiveis(): Observable<DetalhesCartao[]> {
    return this.http.get<DetalhesCartao[]>(`${this.baseUrl}/cartoes-disponiveis`);
  }
}
