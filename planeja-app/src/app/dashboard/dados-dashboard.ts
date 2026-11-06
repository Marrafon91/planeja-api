export class ResumoFinanceiro {
  receitas!: number;
  despesas!: number;
  saldo!: number;
}

export class DespesaCategoriaResumo {
  categotia!: string;
  valor!: string;
}

export class Dashboar {
  mes!: string;
  resumo!: string;
  despesasPorCategoria!: DespesaCategoriaResumo[];
}
