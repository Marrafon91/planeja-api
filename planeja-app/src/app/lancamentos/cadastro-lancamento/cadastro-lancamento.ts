import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TipoLancanmento } from '../dados-lancamentos';
import { ToastrService } from 'ngx-toastr';
import { LancamentoService } from '../lancamento-service';
import { DetalhesCategoria } from '../../categorias/dados-categoria';
import { DetalhesCartao } from '../../cartoes/dados-cartao';
import { forkJoin } from 'rxjs';

interface CadastroLancamentoForm {
  categoriaId: FormControl<string>;
  data: FormControl<string>;
  valor: FormControl<string>;
  tipo: FormControl<TipoLancanmento | ''>;
  cartaoId: FormControl<string>;
}

@Component({
  imports: [],
  selector: 'app-cadastro-lancamento',
  styleUrl: './cadastro-lancamento.css',
  templateUrl: './cadastro-lancamento.html',
})
export class CadastroLancamento implements OnInit {
  form!: FormGroup<CadastroLancamentoForm>;
  toast = inject(ToastrService);
  service = inject(LancamentoService);

  categoriasAtivas: DetalhesCategoria[] = [];
  cartoesAtivos: DetalhesCartao[] = [];

  ngOnInit(): void {
    this.form = new FormGroup<CadastroLancamentoForm>({
      categoriaId: new FormControl<string>('', { nonNullable: true, validators: Validators.required }),
      data: new FormControl<string>('', { nonNullable: true, validators: Validators.required }),
      valor: new FormControl<string>('', { nonNullable: true, validators: Validators.required }),
      tipo: new FormControl<TipoLancanmento | ''>('', { nonNullable: true, validators: Validators.required }),
      cartaoId: new FormControl<string>('', { nonNullable: true}),
    });

    this.inicializarDropDowns();
  }

  inicializarDropDowns() {
    forkJoin({
      categorias: this.service.listarCategoriasDisponiveis(),
      cartoes: this.service.listarCartoesDisponiveis(),
    }).subscribe({
      next: (resultado) => {
        this.categoriasAtivas = resultado.categorias;
        this.cartoesAtivos = resultado.cartoes;
      },
      error: () => this.toast.error('Erro ao carregar categorias e cartões');
    });
  }
}
