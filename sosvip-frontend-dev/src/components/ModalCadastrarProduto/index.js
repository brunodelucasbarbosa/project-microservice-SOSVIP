import React, { useState, useEffect } from 'react';
import InputMask from "react-input-mask";
import useUser from '../../hooks/useUser';

function ModalCadastrarProduto({ setModalCadastrarProduto, clienteATerPedidoCadastrado }) {

  const { token, ToastContainer, toast } = useUser()

  const [descricao, setDescricao] = useState('');
  const [valorTotal, setValorTotal] = useState('');

  useEffect(() => {
    setDescricao('');
  }, []);

  function handleLimparModal(e) {
    e.preventDefault();
    setDescricao('');
    setModalCadastrarProduto(false);
  }

  async function handleCriarProduto(e) {
    e.preventDefault()
    if (!valorTotal) {
      return toast.error('Digite o valor do pedido!', {
        position: "top-right",
        autoClose: false,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      })
    }

    const dados = {
      clientId: parseInt(clienteATerPedidoCadastrado.id),
      totalAmount: valorTotal,
      description: descricao,
      date: new Date().toISOString().split('T')[0]
    }

    try {
      await fetch(`http://localhost:8081/client/order`, {
        method: 'POST',
        body: JSON.stringify(dados),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
      }).then(response => {
        if (response.status === 200)
          toast.success('Pedido enviado!', {
            position: "top-right",
            autoClose: false,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: true,
            progress: undefined,
          });
      });

    } catch (error) {
      toast.error('Erro ao realizar pedido!', {
        position: "top-right",
        autoClose: false,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      console.log(error)
    }
  }

  return (
    <div className="modal-open">
      <ToastContainer className="toast-error"
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
      <div className="container-modal">
        <h2 className="title-modal">Novo Produto</h2>
        <div>
          <input
            className="input-modal"
            style={{ marginBottom: '2rem', backgroundColor: 'darkgray', color: 'black' }}
            disabled={true}
            value={`Cliente: ${clienteATerPedidoCadastrado.name}`} />
        </div>
        <form>
          <input
            className="input-modal"
            type="text"
            placeholder="Descrição do Produto"
            name="descricao"
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)} />
          <InputMask
            className="input-modal"
            type="text"
            required
            placeholder="Data de nascimento"
            disabled={true}
            value={new Date().toLocaleDateString('pt-BR', { year: 'numeric', month: 'numeric', day: 'numeric' })}
          />
          <InputMask
            className="input-modal"
            placeholder="Valor Total R$"
            name="valorTotal"
            value={valorTotal}
            onChange={(e) => setValorTotal(e.target.value)} />

          <button
            className="button-modal adicionar"
            onClick={e => handleCriarProduto(e)}>ADICIONAR</button>

          <button
            className="button-modal limpar"
            onClick={e => handleLimparModal(e)}
          >CANCELAR</button>

        </form>
      </div>
    </div>


  );
}

export default ModalCadastrarProduto;
