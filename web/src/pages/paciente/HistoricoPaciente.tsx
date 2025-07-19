import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Card,
  CardContent,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Tabs,
  Tab,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Divider,
  Avatar,
} from '@mui/material';
import {
  Person,
  LocalHospital,
  Assignment,
  Receipt,
  Visibility,
  Download,
  CalendarToday,
  AccessTime,
} from '@mui/icons-material';
import { PacienteResponse, ConsultaMarcadaResponse } from '../../types';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`patient-tabpanel-${index}`}
      aria-labelledby={`patient-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
}

export const HistoricoPaciente: React.FC = () => {
  const [selectedPatient, setSelectedPatient] = useState<PacienteResponse | null>(null);
  const [tabValue, setTabValue] = useState(0);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedConsulta, setSelectedConsulta] = useState<any>(null);

  // Mock data para demonstração
  const mockPaciente: PacienteResponse = {
    id: 1,
    nome: 'Maria Silva Santos',
    rg: '12.345.678-9',
    cpf: '123.456.789-00',
    sexo: 'F' as any,
    data_nascimento: '1985-03-15',
    telefone: '(11) 99999-9999',
    email: 'maria.santos@email.com',
    cidade_estado: 'São Paulo-SP',
    endereco: 'Rua das Flores, 123 - Centro',
    patologia: 'Hipertensão, Diabetes Tipo 2',
    idade: 38,
    created_at: '2023-01-15T10:00:00Z',
  };

  const mockConsultas = [
    {
      id: 1,
      data: '2024-01-15',
      horario: '14:00',
      medico: 'Dr. João Cardiologista',
      especialidade: 'Cardiologia',
      status: 'concluida',
      observacoes: 'Pressão arterial controlada. Manter medicação atual.',
      receitas: ['Losartana 50mg', 'Hidroclorotiazida 25mg'],
    },
    {
      id: 2,
      data: '2024-01-08',
      horario: '09:30',
      medico: 'Dra. Ana Endocrinologista',
      especialidade: 'Endocrinologia',
      status: 'concluida',
      observacoes: 'Glicemia estável. Continuar dieta e exercícios.',
      receitas: ['Metformina 850mg'],
    },
    {
      id: 3,
      data: '2024-01-20',
      horario: '16:00',
      medico: 'Dr. Carlos Clínico Geral',
      especialidade: 'Clínica Geral',
      status: 'agendada',
      observacoes: 'Consulta de rotina agendada.',
      receitas: [],
    },
  ];

  const mockExames = [
    {
      id: 1,
      nome: 'Hemograma Completo',
      data: '2024-01-10',
      status: 'concluido',
      resultado: 'Normal',
      observacoes: 'Todos os parâmetros dentro da normalidade.',
    },
    {
      id: 2,
      nome: 'Glicemia de Jejum',
      data: '2024-01-10',
      status: 'concluido',
      resultado: '95 mg/dL',
      observacoes: 'Glicemia normal.',
    },
    {
      id: 3,
      nome: 'Eletrocardiograma',
      data: '2024-01-12',
      status: 'concluido',
      resultado: 'Normal',
      observacoes: 'Ritmo sinusal normal.',
    },
  ];

  useEffect(() => {
    setSelectedPatient(mockPaciente);
  }, []);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTabValue(newValue);
  };

  const handleConsultaClick = (consulta: any) => {
    setSelectedConsulta(consulta);
    setOpenDialog(true);
  };

  const getStatusChip = (status: string) => {
    switch (status) {
      case 'concluida':
        return <Chip label="Concluída" color="success" size="small" />;
      case 'agendada':
        return <Chip label="Agendada" color="info" size="small" />;
      case 'cancelada':
        return <Chip label="Cancelada" color="error" size="small" />;
      default:
        return <Chip label={status} size="small" />;
    }
  };

  if (!selectedPatient) {
    return <Typography>Carregando...</Typography>;
  }

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Histórico do Paciente
      </Typography>

      {/* Informações do Paciente */}
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Grid container spacing={3}>
            <Grid item xs={12} md={2}>
              <Avatar
                sx={{
                  width: 80,
                  height: 80,
                  bgcolor: 'primary.main',
                  fontSize: '2rem',
                }}
              >
                {selectedPatient.nome.charAt(0)}
              </Avatar>
            </Grid>
            <Grid item xs={12} md={10}>
              <Typography variant="h5" gutterBottom>
                {selectedPatient.nome}
              </Typography>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="body2" color="textSecondary">
                    CPF
                  </Typography>
                  <Typography variant="body1">{selectedPatient.cpf}</Typography>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="body2" color="textSecondary">
                    Idade
                  </Typography>
                  <Typography variant="body1">{selectedPatient.idade} anos</Typography>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="body2" color="textSecondary">
                    Telefone
                  </Typography>
                  <Typography variant="body1">{selectedPatient.telefone}</Typography>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="body2" color="textSecondary">
                    Email
                  </Typography>
                  <Typography variant="body1">{selectedPatient.email}</Typography>
                </Grid>
                <Grid item xs={12}>
                  <Typography variant="body2" color="textSecondary">
                    Patologias
                  </Typography>
                  <Typography variant="body1">{selectedPatient.patologia || 'Nenhuma'}</Typography>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </CardContent>
      </Card>

      {/* Tabs do Histórico */}
      <Paper>
        <Tabs value={tabValue} onChange={handleTabChange} indicatorColor="primary">
          <Tab icon={<LocalHospital />} label="Consultas" />
          <Tab icon={<Assignment />} label="Exames" />
          <Tab icon={<Receipt />} label="Receitas" />
        </Tabs>

        {/* Tab de Consultas */}
        <TabPanel value={tabValue} index={0}>
          <List>
            {mockConsultas.map((consulta, index) => (
              <React.Fragment key={consulta.id}>
                <ListItem
                  button
                  onClick={() => handleConsultaClick(consulta)}
                  sx={{
                    border: '1px solid #e0e0e0',
                    borderRadius: 1,
                    mb: 1,
                    '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.04)' },
                  }}
                >
                  <ListItemIcon>
                    <CalendarToday color="primary" />
                  </ListItemIcon>
                  <ListItemText
                    primary={
                      <Box display="flex" alignItems="center" gap={2}>
                        <Typography variant="subtitle1">
                          {new Date(consulta.data).toLocaleDateString('pt-BR')}
                        </Typography>
                        <Typography variant="body2" color="textSecondary">
                          {consulta.horario}
                        </Typography>
                        <Typography variant="body1">
                          {consulta.medico}
                        </Typography>
                        {getStatusChip(consulta.status)}
                      </Box>
                    }
                    secondary={
                      <Box>
                        <Typography variant="body2" color="textSecondary">
                          {consulta.especialidade}
                        </Typography>
                        <Typography variant="body2">
                          {consulta.observacoes}
                        </Typography>
                      </Box>
                    }
                  />
                  <Button
                    startIcon={<Visibility />}
                    onClick={() => handleConsultaClick(consulta)}
                  >
                    Ver Detalhes
                  </Button>
                </ListItem>
              </React.Fragment>
            ))}
          </List>
        </TabPanel>

        {/* Tab de Exames */}
        <TabPanel value={tabValue} index={1}>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Data</TableCell>
                  <TableCell>Exame</TableCell>
                  <TableCell>Status</TableCell>
                  <TableCell>Resultado</TableCell>
                  <TableCell>Ações</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {mockExames.map((exame) => (
                  <TableRow key={exame.id}>
                    <TableCell>
                      {new Date(exame.data).toLocaleDateString('pt-BR')}
                    </TableCell>
                    <TableCell>{exame.nome}</TableCell>
                    <TableCell>{getStatusChip(exame.status)}</TableCell>
                    <TableCell>{exame.resultado}</TableCell>
                    <TableCell>
                      <Button
                        size="small"
                        startIcon={<Download />}
                        disabled={exame.status !== 'concluido'}
                      >
                        Baixar
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </TabPanel>

        {/* Tab de Receitas */}
        <TabPanel value={tabValue} index={2}>
          <List>
            {mockConsultas
              .filter(consulta => consulta.receitas.length > 0)
              .map((consulta) => (
                <Card key={consulta.id} sx={{ mb: 2 }}>
                  <CardContent>
                    <Typography variant="h6" gutterBottom>
                      Receita - {new Date(consulta.data).toLocaleDateString('pt-BR')}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" gutterBottom>
                      Prescrita por: {consulta.medico}
                    </Typography>
                    <List dense>
                      {consulta.receitas.map((receita, index) => (
                        <ListItem key={index}>
                          <ListItemIcon>
                            <Receipt color="primary" />
                          </ListItemIcon>
                          <ListItemText primary={receita} />
                        </ListItem>
                      ))}
                    </List>
                    <Box sx={{ mt: 2 }}>
                      <Button startIcon={<Download />} size="small">
                        Download PDF
                      </Button>
                    </Box>
                  </CardContent>
                </Card>
              ))}
          </List>
        </TabPanel>
      </Paper>

      {/* Dialog de Detalhes da Consulta */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          Detalhes da Consulta - {selectedConsulta && new Date(selectedConsulta.data).toLocaleDateString('pt-BR')}
        </DialogTitle>
        <DialogContent>
          {selectedConsulta && (
            <Box sx={{ pt: 2 }}>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Data"
                    value={new Date(selectedConsulta.data).toLocaleDateString('pt-BR')}
                    disabled
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Horário"
                    value={selectedConsulta.horario}
                    disabled
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Médico"
                    value={selectedConsulta.medico}
                    disabled
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Especialidade"
                    value={selectedConsulta.especialidade}
                    disabled
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    multiline
                    rows={4}
                    label="Observações"
                    value={selectedConsulta.observacoes}
                    disabled
                  />
                </Grid>
                {selectedConsulta.receitas.length > 0 && (
                  <Grid item xs={12}>
                    <Typography variant="h6" gutterBottom>
                      Medicamentos Prescritos:
                    </Typography>
                    {selectedConsulta.receitas.map((receita: string, index: number) => (
                      <Chip key={index} label={receita} sx={{ mr: 1, mb: 1 }} />
                    ))}
                  </Grid>
                )}
              </Grid>
            </Box>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Fechar</Button>
          <Button variant="contained" startIcon={<Download />}>
            Baixar Relatório
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}; 