import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CartaoService } from '../cartao-service';
import { DadosCartaoForm, DetalhesCartao } from '../dados-cartao';
import { ValidationErrorResponse } from '../../common/validation/validation-error-model';

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
      error: (error) => this.onApiError(error),
    });
  }

  private aplicarErrosValidacao(error: ValidationErrorResponse) {
    error.camposInvalidos.forEach((ci) => {
      const control = this.form.get(ci.campo);
      if (control) {
        control.setErrors({ apiError: ci.erro });
        control.markAsTouched();
      }
    });
  }

  private onApiError(response: any): void {
    if (response.status === 422) {
      this.aplicarErrosValidacao(response.error);
      return;
    }
    
  }
}
