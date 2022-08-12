import React from 'react';
import './style.css';
import useUser from '../../hooks/useUser';
import {
  Link,
  useHistory,
  useLocation
} from 'react-router-dom';

function Button() {
  const {
    verificaToken,
    deslogar,
    setTipoTabelao,
  } = useUser();
  const history = useHistory();
  const pagAtual = useLocation().pathname.replace('/', '');
  const pagDestino = pagAtual === "pedidos" ? "clientes" :"pedidos";
  
  async function mudarView() {
   const tokenValido = await verificaToken();
   console.log("token valido " + tokenValido);
    if(!tokenValido){
      deslogar();
      history.push(`/`)
      return;
    }
    console.log("atual - " + pagAtual);
    console.log("destino - " +pagDestino);

    setTipoTabelao(pagDestino);
    history.push(`/${pagDestino}`)
  }

  return (
    <div>
      <Link className="button-trocar-view" onClick={() => mudarView()}>{pagDestino.toUpperCase()}</Link>
    </div>
  )
}

export default Button;