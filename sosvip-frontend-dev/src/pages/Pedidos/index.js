import { useEffect, useState } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Header from '../../components/Header';
import Paginacao from '../../components/Paginacao';
import Tabelao from '../../components/Tabelao';
import useUser from '../../hooks/useUser';

import { CircularProgress } from '@mui/material';

function Pedidos() {
  const { listarPedidos, carregando, modalOpen, modalEditarOpen,  modalExcluirOpen } = useUser();

  const [numeroPagina, setNumeroPagina] = useState(0)
  const [conteudoPaginacao, setConteudoPaginacao] = useState([])

  useEffect(() => {
    listarPedidos(numeroPagina, setConteudoPaginacao);
    //eslint-disable-next-line
  }, []);

  useEffect(() => {
    listarPedidos(numeroPagina, setConteudoPaginacao);
    //eslint-disable-next-line
  }, [modalOpen, modalEditarOpen, modalExcluirOpen, numeroPagina]);

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

        {carregando ? <></> : <div className="div-botao">
          <Paginacao conteudoPaginacao={conteudoPaginacao} handleTrocarPagina={handleTrocarPagina} />
        </div>}

        {carregando ?
        
         <div className="carregando"><CircularProgress /></div> : <Tabelao />}
      </section>


    </main>
  );
}

export default Pedidos;