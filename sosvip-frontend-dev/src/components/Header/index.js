import React from 'react';
import './style.css';
import IconSair from '../../assets/sair.svg'
import useUser from '../../hooks/useUser';
import {useHistory, useLocation} from 'react-router-dom';
import Button from '../Button';


function Header({titulo}) {
  const history = useHistory();
  const { deslogar} = useUser();

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
      <div>
        <h1>{`LISTA DE ${useLocation().pathname.replace('/', '').toUpperCase()}`}</h1>
      </div>

      <div className="iconSair">
        <img src={IconSair} alt="icone sair" onClick={e => handleDeslogarSubmit(e)} />
      </div>
    </header>
  );
}

export default Header;
