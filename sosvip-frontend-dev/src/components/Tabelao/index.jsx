import "./style.css";
import useUser from '../../hooks/useUser';
import iconEditar from '../../assets/icone-editar.svg'
import iconDelete from '../../assets/icone-delete.svg'
import adicionaPedido from '../../assets/lista.png'
import { CircularProgress } from '@mui/material'
import { useHistory } from 'react-router-dom';
import {useState} from 'react'
import ModalCadastrarProduto from '../../components/ModalCadastrarProduto'

function Tabelao() {

  const { 
    tipoTabelao,
    pedidos,
    clientes,
    carregando, 
    setModalExcluirOpen, 
    handleAbrirModalEditarCliente, 
    setClienteEditar,
    setTipoModalCliente ,
    formatCpf,
    formatPhone,
    formatDate,
    setIdClientePorPedido,
    pedidosPorCliente,
    setTipoTabelao
  } = useUser();
  

  const [modalCadastrarProduto, setModalCadastrarProduto] = useState(false)
  const [clienteATerPedidoCadastrado, setClienteATerPedidoCadastrado] = useState([])
  const history = useHistory();

  function handleAbrirModalExcluir(e) {
    const clienteExcluir = clientes.find(cliente => cliente.id === Number(e.target.id));
    setClienteEditar(clienteExcluir);
    setModalExcluirOpen(true);
  }
  
  function handleListarPedidosPorCliente(e) {
    console.log(e.target.id)
    setIdClientePorPedido(e.target.id);
    setTipoTabelao("PedidosPorCliente");
    history.push(`/clientes/lista-pedidos/${e.target.id}`)
  }

 
  function formartarDataPedido(data) {
    const ano = data.substr(0, 4);
    const mes = data.substr(5, 2);
    const dia = data.substr(8, 2);
    return `${dia}/${mes}/${ano}`;
  }

  function handleCadastrarProduto(e) {
    const clienteACadastrarPedido = clientes.find(cliente => cliente.id === Number(e.target.id));
    console.log(clienteACadastrarPedido)
    setClienteATerPedidoCadastrado(clienteACadastrarPedido)
    setModalCadastrarProduto(true)
  }

return (
    <div>
      <table className="tabelao" border="0">
        <thead className="headTabelao">
          <tr>
          {tipoTabelao==="clientes" && 
           <th className="atributoTabelao">Cliente ID</th>
           }
            <th className="atributoTabelao">
              {tipoTabelao === "clientes" ? "Cliente" : "ID Pedido"}
            </th>
            <th className="atributoTabelao">
              {tipoTabelao === "clientes" ? "Email" : "Descrição"}
            </th>
            <th className="atributoTabelao">
              {tipoTabelao === "clientes" ? "Telefone" : "Valor Total"}
            </th>
            <th className="atributoTabelao">
               {tipoTabelao === "clientes" ? "CPF" : "Data Criação"}
            </th>
            <th className="atributoTabelao">
              {tipoTabelao === "clientes" ? "Data Nascimento" : "Status"}
            </th>
            <th className="atributoTabelao">
              {tipoTabelao === "clientes" ? "" : "ID Cliente"}
            </th>
          </tr>
        </thead>
        <tbody className="bodyTabelao">
          <hr className="efeitoLinhaTabelao" />

          {tipoTabelao === "clientes" ?
            carregando? <CircularProgress /> :
              clientes.map((cliente, indice) => {
              
                return (
                  <>
                    <tr key={indice}>
           {tipoTabelao==="clientes" && 
            <td className="itemTabelao nomeclicavel" id={cliente.id} onClick={e => handleListarPedidosPorCliente(e)}>{cliente.id}</td>
           }
                      <td className="itemTabelao">{cliente.name}</td>
                      <td className={`itemTabelao`}>{cliente.email}</td>
                      <td className={`itemTabelao`}>{formatPhone(cliente.phone)}</td>
                      <td className={`itemTabelao`}>{formatCpf(cliente.cpf)}</td>
                      <td className={`itemTabelao`}>{cliente.birthdate != null?formatDate(cliente.birthdate):""}</td>
                      <td className="itemTabelao">
                       <div className="contemEditaExcluiTabelao">
                       <img
                          src={adicionaPedido}
                          alt="icone adiciona pedido"
                          style={{ cursor: 'pointer' }}
                          id={cliente.id}
                          onClick={e => handleCadastrarProduto(e)}
                        />

                         <img
                          id={cliente.id}
                          src={iconEditar}
                          alt="icone editar"
                          style={{ cursor: 'pointer' }}
                          onClick={e => {setTipoModalCliente("edicao"); handleAbrirModalEditarCliente(e); }}
                          />
                        <img
                          src={iconDelete}
                          alt="icone deletar"
                          style={{ cursor: 'pointer' }}
                          id={cliente.id}
                          onClick={e => handleAbrirModalExcluir(e)}
                        />
                        </div>
                      </td>
                    </tr>
                    <hr className="efeitoLinhaTabelao"/>
                  </>
                );
            })
            :
            tipoTabelao === "pedidos"?
            carregando ? <CircularProgress /> 
            :
            pedidos.map((pedido, indice) => {      
                return (
                  <>
                      <tr key={indice}>
                      <td className="itemTabelao nomeclicavel"> {pedido.id}</td>
                      <td className={`itemTabelao`}>{pedido.description}</td>
                      <td className={`itemTabelao`}>{`R$ ${(pedido.totalAmount).toFixed(2).toString().replace(".", ",")}`}</td>
                      <td className={`itemTabelao`}>{formartarDataPedido(pedido.date)}</td>
                      <td className="itemTabelao">{pedido.status ? "Confirmado" : "Criado"}</td>
                      <td className={`itemTabelao`}>{pedido.clientId}</td>
                    </tr>
                    <hr className="efeitoLinhaTabelao" />
                  </>
                );
              })
            :
            pedidosPorCliente.map((pedido, indice) => {
            return (
              <>
                  <tr key={indice}>
                  <td className="itemTabelao nomeclicavel"> {pedido.id}</td>
                  <td className={`itemTabelao`}>{pedido.description}</td>
                  <td className={`itemTabelao`}>{`R$ ${(pedido.totalAmount).toFixed(2).toString().replace(".", ",")}`}</td>
                  <td className={`itemTabelao`}>{formartarDataPedido(pedido.date)}</td>
                  <td className="itemTabelao">{pedido.status ? "Confirmado" : "Criado"}</td>
                  <td className={`itemTabelao`}>{pedido.clientId}</td>
                </tr>
                <hr className="efeitoLinhaTabelao" />
              </>
            );
          })
            }; 
        
              
              
            
        </tbody>
        
      </table>

      {modalCadastrarProduto && <ModalCadastrarProduto 
      setModalCadastrarProduto={setModalCadastrarProduto}
      clienteATerPedidoCadastrado={clienteATerPedidoCadastrado} />}

    </div>
  );
}

export default Tabelao;