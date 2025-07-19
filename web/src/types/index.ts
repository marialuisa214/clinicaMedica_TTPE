// Tipos de dados para autenticação
export interface LoginRequest {
  usuario: string;
  senha: string;
}

export interface TokenResponse {
  access_token: string;
  token_type: string;
}

export interface UserInfo {
  id: number;
  nome: string;
  usuario: string;
  tipo: string;
  email: string;
}

export interface AuthState {
  isAuthenticated: boolean;
  user: UserInfo | null;
  token: string | null;
}

// Tipos para pacientes
export interface PacienteBase {
  nome: string;
  rg: string;
  cpf: string;
  sexo: string;
  data_nascimento: string;
  telefone?: string;
  email?: string;
  cidade_estado?: string;
  endereco?: string;
  patologia?: string;
}

export interface PacienteCreate extends PacienteBase {}

export interface PacienteUpdate extends Partial<PacienteBase> {}

export interface PacienteResponse extends PacienteBase {
  id: number;
  created_at: string;
  updated_at: string;
}

// Tipos para funcionários
export interface FuncionarioBase {
  nome: string;
  usuario: string;
  email: string;
  telefone?: string;
  tipo: string;
  crm?: string;
  especialidade?: string;
  coren?: string;
  crf?: string;
  setor?: string;
}

export interface FuncionarioCreate extends FuncionarioBase {
  senha: string;
}

export interface FuncionarioUpdate extends Partial<Omit<FuncionarioBase, 'usuario' | 'tipo'>> {
  senha?: string;
}

export interface FuncionarioResponse extends FuncionarioBase {
  id: number;
  created_at: string;
  updated_at: string;
}

export interface FuncionarioListResponse {
  funcionarios: FuncionarioResponse[];
  total: number;
  page: number;
  size: number;
}

// Tipos para consultas
export enum StatusConsulta {
  AGENDADA = "agendada",
  CONFIRMADA = "confirmada",
  EM_ANDAMENTO = "em_andamento",
  FINALIZADA = "finalizada",
  CANCELADA = "cancelada"
}

export enum TipoConsulta {
  CONSULTA_NORMAL = "consulta_normal",
  RETORNO = "retorno",
  EMERGENCIA = "emergencia",
  EXAME = "exame"
}

export interface ConsultaBase {
  paciente_id: number;
  medico_id: number;
  data_hora: string;
  tipo?: TipoConsulta;
  motivo?: string;
  observacoes?: string;
}

export interface ConsultaCreate extends ConsultaBase {}

export interface ConsultaUpdate extends Partial<ConsultaBase> {
  status?: StatusConsulta;
  diagnostico?: string;
  prescricao?: string;
}

export interface ConsultaResponse extends ConsultaBase {
  id: number;
  status: StatusConsulta;
  diagnostico?: string;
  prescricao?: string;
  atendente_id?: number;
  created_at: string;
  updated_at: string;
  paciente_nome?: string;
  medico_nome?: string;
  atendente_nome?: string;
}

export interface ConsultaListResponse {
  consultas: ConsultaResponse[];
  total: number;
  page: number;
  size: number;
}

// Tipos para agenda médica
export interface AgendaMedicoBase {
  medico_id: number;
  data: string;
  hora_inicio: string;
  hora_fim: string;
  disponivel?: boolean;
  motivo_indisponibilidade?: string;
}

export interface AgendaMedicoCreate extends AgendaMedicoBase {}

export interface AgendaMedicoUpdate extends Partial<Pick<AgendaMedicoBase, 'disponivel' | 'motivo_indisponibilidade'>> {}

export interface AgendaMedicoResponse extends AgendaMedicoBase {
  id: number;
  created_at: string;
  updated_at: string;
  medico_nome?: string;
}

export interface HorarioDisponivelResponse {
  data: string;
  horarios: string[];
}

// Tipos para estatísticas
export interface EstatisticasConsultas {
  total_consultas: number;
  consultas_hoje: number;
  consultas_semana: number;
  consultas_mes: number;
  consultas_por_status: Record<string, number>;
  consultas_por_tipo: Record<string, number>;
} 