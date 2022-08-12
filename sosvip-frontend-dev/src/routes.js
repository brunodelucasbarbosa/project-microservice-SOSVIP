import './styles.css';
import Login from './pages/Login';
import CadastrarAdministrador from './pages/CadastrarAdministrador';
import Clientes from './pages/Clientes';
import Pedidos from './pages/Pedidos';
import PedidosPorCliente from './pages/PedidosPorCliente';
import useUser from './hooks/useUser';

import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom'

function RotasProtegida(props) {  
  return (
    <Route 
      render={() => props.token ? (props.children) : (<Redirect to="/" />)}
    />
  );
};

function App() {
  const {token} = useUser();
  
  return (
    <div className="App">
        <Router>
          <Switch>              
              <Route path="/cadastrar" exact component={CadastrarAdministrador} />
              <Route path="/" exact component={Login} />
              <RotasProtegida token={token}>
                <Route path="/clientes" exact component={Clientes} />
                <Route path="/clientes/lista-pedidos/:id" exact component={PedidosPorCliente}  />
                <Route path="/pedidos" exact component={Pedidos} />
              </RotasProtegida>
          </Switch>
        </Router>
    </div>
  );
}

export default App;