export class ResumoFinanceiro {
  receitas!: number;
  despesas!: number;
  saldo!: number;
}

export class DespesaCategoriaResumo {
  categotia!: string;
  valor!: string;
}

export class Dashboard {
  mes!: string;
  resumo!: ResumoFinanceiro;
  despesasPorCategoria!: DespesaCategoriaResumo[];
}
