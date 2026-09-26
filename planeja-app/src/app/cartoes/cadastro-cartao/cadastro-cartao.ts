import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CartaoService } from '../cartao-service';
import { DadosCartaoForm, DetalhesCartao } from '../dados-cartao';

interface CadastroCartaoForm {
  nome: FormControl<string>;
  bandeira: FormControl<string>;
}
@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-cadastro-cartao',
  styleUrl: './cadastro-cartao.css',
  templateUrl: './cadastro-cartao.html',
})
export class CadastroCartao implements OnInit {
  form!: FormGroup<CadastroCartaoForm>;
  service = inject(CartaoService);

  ngOnInit(): void {
    this.form = new FormGroup<CadastroCartaoForm>({
      nome: new FormControl('', { nonNullable: true, validators: Validators.required }),
      bandeira: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    console.log(this.form.value);
    const dadosCartao = this.form.value as DadosCartaoForm;
    this.service.criar(dadosCartao).subscribe({
      next: (response: DetalhesCartao) => {
        console.log('recebendo a resposta do servidor: ', response);
      },
      error: (error) => console.log('ocorreu um erro: ', error),
    });
  }
}
