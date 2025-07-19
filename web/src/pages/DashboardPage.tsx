import React from 'react';
import {
  Box,
  Typography,
  Grid,
  Card,
  CardContent,
  CardActions,
  Button,
  Avatar,
  Chip
} from '@mui/material';
import {
  People as PeopleIcon,
  CalendarToday as CalendarIcon,
  LocalHospital as HospitalIcon,
  PersonAdd as PersonAddIcon
} from '@mui/icons-material';
import { useAuth } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const DashboardPage: React.FC = () => {
  const { user } = useAuth();
  const navigate = useNavigate();

  const getQuickActions = () => {
    const actions = [];

    switch (user?.tipo) {
      case 'administrador':
        actions.push(
          {
            title: 'Gerenciar Funcionários',
            description: 'Cadastrar e gerenciar funcionários do sistema',
            icon: <PeopleIcon />,
            path: '/admin/funcionarios',
            color: 'primary'
          },
          {
            title: 'Gerenciar Pacientes',
            description: 'Visualizar e gerenciar pacientes',
            icon: <PersonAddIcon />,
            path: '/atendente/pacientes',
            color: 'secondary'
          }
        );
        break;

      case 'medico':
        actions.push(
          {
            title: 'Minha Agenda',
            description: 'Visualizar consultas do dia e agenda',
            icon: <CalendarIcon />,
            path: '/medico/agenda',
            color: 'primary'
          }
        );
        break;

      case 'atendente':
        actions.push(
          {
            title: 'Gerenciar Consultas',
            description: 'Agendar e gerenciar consultas',
            icon: <CalendarIcon />,
            path: '/atendente/consultas',
            color: 'primary'
          },
          {
            title: 'Gerenciar Pacientes',
            description: 'Cadastrar e gerenciar pacientes',
            icon: <PersonAddIcon />,
            path: '/atendente/pacientes',
            color: 'secondary'
          }
        );
        break;

      default:
        actions.push(
          {
            title: 'Sistema de Clínica',
            description: 'Bem-vindo ao sistema de gestão',
            icon: <HospitalIcon />,
            path: '/dashboard',
            color: 'primary'
          }
        );
    }

    return actions;
  };

  const getTipoColor = (tipo: string) => {
    switch (tipo) {
      case 'administrador': return 'error';
      case 'medico': return 'primary';
      case 'enfermeiro': return 'success';
      case 'atendente': return 'warning';
      case 'farmaceutico': return 'info';
      default: return 'default';
    }
  };

  return (
    <Box>
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4" gutterBottom>
          Dashboard
        </Typography>
        
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          <Avatar sx={{ bgcolor: 'primary.main', width: 56, height: 56 }}>
            {user?.nome?.charAt(0).toUpperCase()}
          </Avatar>
          <Box>
            <Typography variant="h6">
              Bem-vindo, {user?.nome}!
            </Typography>
            <Chip 
              label={user?.tipo?.charAt(0).toUpperCase() + user?.tipo?.slice(1)} 
              color={getTipoColor(user?.tipo || '')}
              size="small"
            />
          </Box>
        </Box>
      </Box>

      <Grid container spacing={3}>
        {getQuickActions().map((action, index) => (
          <Grid item xs={12} sm={6} md={4} key={index}>
            <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
              <CardContent sx={{ flexGrow: 1 }}>
                <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                  <Avatar sx={{ bgcolor: `${action.color}.main`, mr: 2 }}>
                    {action.icon}
                  </Avatar>
                  <Typography variant="h6" component="h2">
                    {action.title}
                  </Typography>
                </Box>
                <Typography variant="body2" color="text.secondary">
                  {action.description}
                </Typography>
              </CardContent>
              <CardActions>
                <Button 
                  size="small" 
                  color={action.color}
                  onClick={() => navigate(action.path)}
                >
                  Acessar
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Box sx={{ mt: 4 }}>
        <Typography variant="h6" gutterBottom>
          Informações do Sistema
        </Typography>
        <Card>
          <CardContent>
            <Typography variant="body1" paragraph>
              Este é o sistema de gestão da clínica médica. Aqui você pode gerenciar
              pacientes, funcionários, consultas e muito mais, dependendo do seu nível de acesso.
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Para suporte técnico, entre em contato com a equipe de TI.
            </Typography>
          </CardContent>
        </Card>
      </Box>
    </Box>
  );
};

export default DashboardPage; 