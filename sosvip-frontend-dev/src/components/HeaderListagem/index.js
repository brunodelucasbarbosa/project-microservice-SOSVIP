import React from 'react';
import IconSair from '../../assets/sair.svg'
import useUser from '../../hooks/useUser';
import {useHistory, useLocation} from 'react-router-dom';
import Button from '../Button';


function HeaderListagem() {
  const history = useHistory();
  const { deslogar} = useUser();

  const location = useLocation();
  const arrayLocation = location.pathname.split("/");

  function handleDeslogarSubmit(e) {
    e.preventDefault();
    deslogar();
    history.push('/')
  }




  return (
    <header>
      <div className="botao__trocar">
        <Button />
      </div>
      <div style={{width: 'auto'}}>
        <h1>{`LISTA DE PEDIDOS DO CLIENTE: ${arrayLocation[3].toUpperCase()} `}</h1>
      </div>

      <div className="iconSair">
        <img src={IconSair} alt="icone sair" onClick={e => handleDeslogarSubmit(e)} />
      </div>
    </header>
  );
}

export default HeaderListagem;
