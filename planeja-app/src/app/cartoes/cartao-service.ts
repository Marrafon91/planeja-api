import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DadosCartaoForm, DetalhesCartao } from './dados-cartao';
import { Observable } from 'rxjs';
import { PageResult } from '../common/pagnation/page-result';
import pa from '@angular/common/locales/pa';
import ur from '@angular/common/locales/ur';

@Service()
export class CartaoService {
  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/cartoes';

  criar(dados: DadosCartaoForm): Observable<DetalhesCartao> {
    return this.http.post<DetalhesCartao>(this.baseUrl, dados);
  }

  listar(page: number = 0, size: number = 10): Observable<PageResult<DetalhesCartao>> {
    const url = `${this.baseUrl}?page=${page}&size=${size}`;
    return this.http.get<PageResult<DetalhesCartao>>(url);
  }

  obterPorId(id: string): Observable<DetalhesCartao> {
    return this.http.get<DetalhesCartao>(`${this.baseUrl}/${id}`);
  }
}
