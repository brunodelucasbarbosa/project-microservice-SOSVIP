import './style.css';
import useUser from '../../hooks/useUser';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Header from '../../components/Header';
import { useEffect, useState } from 'react';
import ModalCliente from '../../components/ModalCliente'
import ModalExcluirCliente from '../../components/ModalExcluirCliente'
import Paginacao from '../../components/Paginacao';
import { CircularProgress } from '@mui/material';
import Tabelao from '../../components/Tabelao';

function Clientes() {
  const { listarClientes, carregando, modalOpenCliente, setModalOpenCliente, modalExcluirOpen, tipoModalCliente,setTipoModalCliente } = useUser();

  const [numeroPagina, setNumeroPagina] = useState(0)
  const [conteudoPaginacao, setConteudoPaginacao] = useState([])

  useEffect(() => {
    listarClientes(numeroPagina, setConteudoPaginacao);
    // eslint-disable-next-line
  }, []);

  useEffect(() => {
    listarClientes(numeroPagina, setConteudoPaginacao);
    // eslint-disable-next-line
  }, [modalOpenCliente, modalExcluirOpen, numeroPagina]);



  // function handleListarPedidos(e) {
  //   history.push(`/clientes/lista-pedidos/${e.target.id}`)
  // }

  function handleTrocarPagina(novaPagina) {
    setNumeroPagina(novaPagina)
  }

  return (
    <main className="tela-contatos">
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
      <Header />
      <section className="section-contatos">
        <div className="div-botao">
          <button className="button-adicionar-contato" onClick={() => {setModalOpenCliente(true); setTipoModalCliente("cadastro");}}>Adicionar</button>
        </div>

        {carregando ? <></> : <>
          <Paginacao conteudoPaginacao={conteudoPaginacao} handleTrocarPagina={handleTrocarPagina} />
        </>}

        {carregando ? <CircularProgress /> : <Tabelao/>}
        
      </section>

      {modalOpenCliente && <ModalCliente tipoModalCliente={tipoModalCliente}/>}
      {modalExcluirOpen && <ModalExcluirCliente />}

    </main>
  );
}

export default Clientes;