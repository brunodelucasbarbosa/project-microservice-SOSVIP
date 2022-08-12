import { useEffect, useState } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import HeaderListagem from '../../components/HeaderListagem';
import ModalCadastrarProduto from '../../components/ModalCadastrarProduto';
import Paginacao from '../../components/Paginacao';
import Tabelao from '../../components/Tabelao';
import useUser from '../../hooks/useUser';

import { CircularProgress } from '@mui/material';

function PedidosPorCliente() {
  const {  getPedidosPorClientes, carregando, modalOpen, modalEditarOpen,  modalExcluirOpen } = useUser();

  const [modal, setModal] = useState(false);
  const [numeroPagina, setNumeroPagina] = useState(0)
  const [conteudoPaginacao, setConteudoPaginacao] = useState([])


  useEffect(() => {
    getPedidosPorClientes(numeroPagina, setConteudoPaginacao);
    //eslint-disable-next-line
  }, []);

  useEffect(() => {
    getPedidosPorClientes(numeroPagina, setConteudoPaginacao);
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
      <HeaderListagem />
      <section className="section-contatos">
    <h2>hehe</h2>
        {carregando ? <></> : <div className="div-botao">
          <Paginacao conteudoPaginacao={conteudoPaginacao} handleTrocarPagina={handleTrocarPagina} />
        </div>}

        {carregando ? <CircularProgress /> : <Tabelao />}
      </section>

      {modal && <ModalCadastrarProduto setModal={setModal} />}

    </main>
  );
}

export default PedidosPorCliente;