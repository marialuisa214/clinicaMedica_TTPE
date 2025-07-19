import axios, { AxiosInstance, AxiosResponse } from 'axios';
import { 
  LoginRequest, TokenResponse, UserInfo, 
  PacienteResponse, PacienteBase, PacienteCreate, PacienteUpdate,
  FuncionarioResponse, FuncionarioCreate, FuncionarioUpdate, FuncionarioListResponse,
  ConsultaResponse, ConsultaCreate, ConsultaUpdate, ConsultaListResponse,
  AgendaMedicoResponse, HorarioDisponivelResponse, StatusConsulta, TipoConsulta
} from '../types';

class ApiService {
  private api: AxiosInstance;

  constructor() {
    // Detectar se está rodando em Docker ou local
    const baseURL = process.env.NODE_ENV === 'production' 
      ? '/api/v1'  // Em produção (Docker), usar proxy do nginx
      : '/api/v1'; // Em desenvolvimento, usar proxy do package.json
    
    this.api = axios.create({
      baseURL,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    // Interceptor para adicionar token automaticamente
    this.api.interceptors.request.use(
      (config) => {
        const token = localStorage.getItem('access_token');
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
          console.log('Token adicionado:', token.substring(0, 50) + '...');
        } else {
          console.log('Nenhum token encontrado no localStorage');
        }
        return config;
      },
      (error) => {
        console.error('Erro no interceptor de request:', error);
        return Promise.reject(error);
      }
    );

    // Interceptor para tratar respostas
    this.api.interceptors.response.use(
      (response) => {
        console.log('Resposta recebida:', response.status, response.config.url);
        return response;
      },
      (error) => {
        console.error('Erro na resposta:', error.response?.status, error.response?.data);
        if (error.response?.status === 401) {
          // Token expirado ou inválido
          console.log('Token inválido, removendo do localStorage');
          localStorage.removeItem('access_token');
          localStorage.removeItem('user_info');
          window.location.href = '/login';
        }
        return Promise.reject(error);
      }
    );
  }

  // Autenticação
  async login(credentials: LoginRequest): Promise<TokenResponse> {
    console.log('Fazendo login...');
    const response: AxiosResponse<TokenResponse> = await this.api.post('/auth/login', credentials);
    
    // Salvar token
    localStorage.setItem('access_token', response.data.access_token);
    console.log('Token salvo no localStorage:', response.data.access_token.substring(0, 50) + '...');
    
    return response.data;
  }

  async getCurrentUser(): Promise<UserInfo> {
    console.log('Buscando usuário atual...');
    const response: AxiosResponse<UserInfo> = await this.api.get('/auth/me');
    
    // Salvar informações do usuário
    localStorage.setItem('user_info', JSON.stringify(response.data));
    console.log('Informações do usuário salvas:', response.data);
    
    return response.data;
  }

  async logout(): Promise<void> {
    await this.api.post('/auth/logout');
    localStorage.removeItem('access_token');
    localStorage.removeItem('user_info');
  }

  // ============= PACIENTES =============
  async getPacientes(params: {
    skip?: number;
    limit?: number;
    search?: string;
  } = {}): Promise<PacienteResponse[]> {
    const response: AxiosResponse<PacienteResponse[]> = await this.api.get('/pacientes/', {
      params
    });
    return response.data;
  }

  async getPacienteById(id: number): Promise<PacienteResponse> {
    const response: AxiosResponse<PacienteResponse> = await this.api.get(`/pacientes/${id}`);
    return response.data;
  }

  async createPaciente(paciente: PacienteCreate): Promise<PacienteResponse> {
    const response: AxiosResponse<PacienteResponse> = await this.api.post('/pacientes/', paciente);
    return response.data;
  }

  async updatePaciente(id: number, paciente: PacienteUpdate): Promise<PacienteResponse> {
    const response: AxiosResponse<PacienteResponse> = await this.api.put(`/pacientes/${id}`, paciente);
    return response.data;
  }

  async deletePaciente(id: number): Promise<void> {
    await this.api.delete(`/pacientes/${id}`);
  }

  async searchPacientesByCPF(cpf: string): Promise<PacienteResponse[]> {
    const response: AxiosResponse<PacienteResponse[]> = await this.api.get(`/pacientes/search/cpf/${cpf}`);
    return response.data;
  }

  // Funcionários
  async getFuncionarios(params: {
    skip?: number;
    limit?: number;
    tipo?: string;
    search?: string;
  } = {}): Promise<FuncionarioListResponse> {
    const response: AxiosResponse<FuncionarioListResponse> = await this.api.get('/funcionarios/', {
      params
    });
    return response.data;
  }

  async getMedicos(): Promise<FuncionarioResponse[]> {
    const response: AxiosResponse<FuncionarioResponse[]> = await this.api.get('/funcionarios/medicos');
    return response.data;
  }

  async getFuncionarioById(id: number): Promise<FuncionarioResponse> {
    const response: AxiosResponse<FuncionarioResponse> = await this.api.get(`/funcionarios/${id}`);
    return response.data;
  }

  async createFuncionario(funcionario: FuncionarioCreate): Promise<FuncionarioResponse> {
    const response: AxiosResponse<FuncionarioResponse> = await this.api.post('/funcionarios/', funcionario);
    return response.data;
  }

  async updateFuncionario(id: number, funcionario: FuncionarioUpdate): Promise<FuncionarioResponse> {
    const response: AxiosResponse<FuncionarioResponse> = await this.api.put(`/funcionarios/${id}`, funcionario);
    return response.data;
  }

  async deleteFuncionario(id: number): Promise<void> {
    await this.api.delete(`/funcionarios/${id}`);
  }

  // Consultas
  async getConsultas(params: {
    skip?: number;
    limit?: number;
    medico_id?: number;
    paciente_id?: number;
    status?: StatusConsulta;
    data_inicio?: string;
    data_fim?: string;
  } = {}): Promise<ConsultaListResponse> {
    const response: AxiosResponse<ConsultaListResponse> = await this.api.get('/consultas/', {
      params
    });
    return response.data;
  }

  async getConsultasMedicoHoje(): Promise<ConsultaResponse[]> {
    const response: AxiosResponse<ConsultaResponse[]> = await this.api.get('/consultas/medico/hoje');
    return response.data;
  }

  async getConsultaById(id: number): Promise<ConsultaResponse> {
    const response: AxiosResponse<ConsultaResponse> = await this.api.get(`/consultas/${id}`);
    return response.data;
  }

  async createConsulta(consulta: ConsultaCreate): Promise<ConsultaResponse> {
    const response: AxiosResponse<ConsultaResponse> = await this.api.post('/consultas/', consulta);
    return response.data;
  }

  async updateConsulta(id: number, consulta: ConsultaUpdate): Promise<ConsultaResponse> {
    const response: AxiosResponse<ConsultaResponse> = await this.api.put(`/consultas/${id}`, consulta);
    return response.data;
  }

  async cancelarConsulta(id: number): Promise<void> {
    await this.api.delete(`/consultas/${id}`);
  }

  // Agenda médica
  async getHorariosDisponiveis(medico_id: number, data: string): Promise<HorarioDisponivelResponse> {
    const response: AxiosResponse<HorarioDisponivelResponse> = await this.api.get(
      `/consultas/agenda/medico/${medico_id}/horarios-disponiveis`,
      { params: { data } }
    );
    return response.data;
  }

  async getAgendaMedico(
    medico_id: number, 
    data_inicio?: string, 
    data_fim?: string
  ): Promise<AgendaMedicoResponse[]> {
    const params: any = {};
    if (data_inicio) params.data_inicio = data_inicio;
    if (data_fim) params.data_fim = data_fim;
    
    const response: AxiosResponse<AgendaMedicoResponse[]> = await this.api.get(
      `/consultas/agenda/medico/${medico_id}`,
      { params }
    );
    return response.data;
  }
}

export const apiService = new ApiService();
export default apiService; 