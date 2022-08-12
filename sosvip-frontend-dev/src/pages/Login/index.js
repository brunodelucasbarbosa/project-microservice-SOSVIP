import './styles.css';
import useUser from '../../hooks/useUser';
import { useHistory } from 'react-router-dom';
import { Link } from 'react-router-dom';


function Login() {

  const {
    emailLoginAdministrador,
    setEmailLoginAdiministrador,
    senhaLoginAdministrador,
    setSenhaLoginAdministrador,
    ToastContainer,
    handleLogin
  } = useUser();

  const history = useHistory();

  async function handleLoginSubmit(e) {
    e.preventDefault();
    const logado = await handleLogin(e);
    //const logado = true;
     if (logado)
       history.push('/clientes');
  }

  return (
    <main className="tela-login">
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
      <aside className="aside-esquerda">
      </aside>

      <aside className="aside-direita">
        <span className="bem-vindo">Bem vindo</span>
        <h2 className="title-login">Faça o login com sua conta</h2>

        <form>
          <input
            name="email"
            type="text"
            value={emailLoginAdministrador}
            onChange={e => setEmailLoginAdiministrador(e.target.value)}
            placeholder="E-mail" />
          <input
            name="senha"
            type="password"
            placeholder="Senha"
            value={senhaLoginAdministrador}
            onChange={e => setSenhaLoginAdministrador(e.target.value)}
          />

          <button
            className="button-login"
            onClick={e => handleLoginSubmit(e)}
          >
            LOGIN
          </button>

          <div className="rota-cadastrar">
            <span>Não tem cadastro?<nbsp> </nbsp>
              <Link
                to="/cadastrar"
                onClick={() => { setEmailLoginAdiministrador(''); setSenhaLoginAdministrador(''); }}>
                Clique aqui!
              </Link>
            </span>
          </div>
        </form>
      </aside>
    </main>
  );
}

export default Login;
