export type TipoLancanmento = 'RECEITA' | 'DESPESA';

export class DadosLancamentosForm {
  categoriaId!: string;
  data!: string;
  valor!: number;
  tipo!: TipoLancanmento;
  cartaoId?: string | null;
}

export class DetalhesLancamento {
  id!: string;
  categoriaId!: string;
  categoriaNome!: string;
  data!: string;
  valor!: number;
  tipo!: TipoLancanmento;
  cartaoId?: string | null;
  cartaoNome?: string;
}

export interface FiltroLancamento {
  mes?: string;
  tipo?: TipoLancanmento;
  categoriaId?: string;
  page?: number;
  size?: number;
}
