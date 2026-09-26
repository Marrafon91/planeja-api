import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

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

  ngOnInit(): void {
    this.form = new FormGroup<CadastroCartaoForm>({
      nome: new FormControl('', { nonNullable: true, validators: Validators.required }),
      bandeira: new FormControl('', { nonNullable: true, validators: Validators.required }),
    });
  }

  handleSubmit() {
    console.log(this.form.value);
  }
}
