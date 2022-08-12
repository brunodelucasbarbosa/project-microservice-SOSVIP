import React from 'react';
import useUser from '../../hooks/useUser';
import iconCloseModal from '../../assets/icon-close-modal.svg';
import InputMask from "react-input-mask";
import './style.css';

function ModalCadastrarCliente({tipoModalCliente}) {
  const { telefoneModalCliente,
    setTelefoneModalCliente,
    nomeModalCliente,
    setNomeModalCliente,
    emailModalCliente,
    setEmailModalCliente,
    cpfModalCliente,
    setCpfModalCliente,
    dataModalCliente, 
    setDataModalCliente,
    handleDataMask,
    handleTelefoneMask,
    handleCriarCliente, 
    handleEditarCliente,
    setModalOpenCliente } = useUser();


  function handleLimparModal(e) {
    if(e)
    e.preventDefault();
    setTelefoneModalCliente('');
    setNomeModalCliente('');
    setEmailModalCliente('');
    setDataModalCliente('');
    setCpfModalCliente('');
  }

  return (
    <div className="modal-open">
      <div className="container-modal">
        <img className="icone-close-modal" src={iconCloseModal} alt="fechar-modal" onClick={() => {handleLimparModal(); setModalOpenCliente(false);}} />
        <h2 className="title-modal">{tipoModalCliente==="cadastro"?"Novo Cliente":"Edição Cliente"}</h2>
        <form>
          <input
            className="input-modal"
            type="text"
            placeholder="Nome"
            name="nome"
            value={nomeModalCliente}
            onChange={(e) => setNomeModalCliente(e.target.value)} />
          <input
            className="input-modal"
            type="email"
            placeholder="E-Mail"
            name="email"
            value={emailModalCliente}
            onChange={(e) => setEmailModalCliente(e.target.value)} />
          <InputMask
            className="input-modal"
            type="text"
            mask="999.999.999-99"
            placeholder="CPF"
            name="cpf"
            value={cpfModalCliente}
            onChange={(e) => setCpfModalCliente(e.target.value)} />
          <InputMask
            className="input-modal"
            type="text"
            mask={handleTelefoneMask()}
            placeholder="Telefone"
            name="telefone"
            value={telefoneModalCliente}
            onChange={(e) => setTelefoneModalCliente(e.target.value)} />

              <InputMask
                className="input-modal"
                mask={handleDataMask()}
                type="text"
                required
                placeholder="Data de nascimento"
                onChange={(e) => setDataModalCliente(e.target.value)}
                value={dataModalCliente}
              />
          <button
            className="button-modal adicionar"
            onClick={e => tipoModalCliente==="cadastro"?handleCriarCliente(e):handleEditarCliente(e)}>{tipoModalCliente==="cadastro"?"ADICIONAR":"EDITAR"}</button>

          <button
            className="button-modal limpar"
            onClick={e => handleLimparModal(e)}
          >LIMPAR</button>
        </form>
      </div>
    </div>
  );
}

export default ModalCadastrarCliente;
