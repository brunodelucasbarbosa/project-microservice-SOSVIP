import './style.css';
import useUser from '../../hooks/useUser';
import { useHistory } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import { Link } from 'react-router-dom';

function CadastrarAdministrador() {
  const {
    nomeCadastroAdministrador,
    setNomeCadastroAdministrador,
    emailCadastroAdministrador,
    setEmailCadastroAdiministrador,
    senhaCadastroAdministrador,
    setSenhaCadastroAdministrador,
    confirmaSenhaCadastroAdministrador,
    setConfirmaSenhaCadastroAdministrador,
    handleCadastrarAdministrador
  } = useUser();
  const history = useHistory();

  async function handleCadastroSubmit(e) {
    e.preventDefault();
    const cadastrado = await handleCadastrarAdministrador(e);

    if (cadastrado)
      history.push('/');
  }
  return (
    <main className="tela-cadastro">
      <ToastContainer
        className="toast-error"
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover={false}
        theme="colored"
      />

      <aside className="aside-direita">
        <h2 className="title-login">Cadastre-se</h2>
        <form>
          <input
            name="nome"
            type="text"
            value={nomeCadastroAdministrador}
            onChange={e => setNomeCadastroAdministrador(e.target.value)}
            placeholder="Nome" />
          <input
            name="email"
            type="text"
            value={emailCadastroAdministrador}
            onChange={e => setEmailCadastroAdiministrador(e.target.value)}
            placeholder="E-mail" />
          <input
            name="senha"
            type="password"
            placeholder="Senha"
            value={senhaCadastroAdministrador}
            onChange={e => setSenhaCadastroAdministrador(e.target.value)}
          />
          <input
            name="ConfirmaSenha"
            type="password"
            placeholder="Confirmar senha"
            value={confirmaSenhaCadastroAdministrador}
            onChange={e => setConfirmaSenhaCadastroAdministrador(e.target.value)}
          />
          <button
            className="button-login"
            onClick={e => handleCadastroSubmit(e)}>CADASTRAR</button>
          <button
            className="button-cancelar"
            onClick={() => { setNomeCadastroAdministrador(''); setEmailCadastroAdiministrador(''); setSenhaCadastroAdministrador(''); setConfirmaSenhaCadastroAdministrador('') }}>CANCELAR</button>
          <div className="rota-login">
            <span>Já tem Cadastro?<nbsp> </nbsp>
              <Link
                to="/"
                sx={{ color: '#5999C0' }}
                onClick={() => { setNomeCadastroAdministrador(''); setEmailCadastroAdiministrador(''); setSenhaCadastroAdministrador(''); setConfirmaSenhaCadastroAdministrador('') }}>
                Clique aqui!
              </Link>
            </span>
          </div>
        </form>
      </aside>
      <aside className="aside-esquerda cadastrar">
      </aside>
    </main>
  );
}

export default CadastrarAdministrador;
