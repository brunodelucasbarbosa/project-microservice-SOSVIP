// require('dotenv').config();
/* eslint-disable no-useless-escape */
import { useState, useEffect } from 'react';
import { useLocalStorage } from 'react-use';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from 'react-router-dom'

function useUserProvider() {
  // cadastro administrador
  const [nomeCadastroAdministrador, setNomeCadastroAdministrador] = useState('');
  const [emailCadastroAdministrador, setEmailCadastroAdiministrador] = useState('');
  const [senhaCadastroAdministrador, setSenhaCadastroAdministrador] = useState('');
  const [confirmaSenhaCadastroAdministrador, setConfirmaSenhaCadastroAdministrador] = useState('');


  // administrador/login
  const [emailLoginAdministrador, setEmailLoginAdiministrador] = useState('');
  const [senhaLoginAdministrador, setSenhaLoginAdministrador] = useState('');

  const [clientes, setClientes] = useState([]);
  const [pedidos, setPedidos] = useState([]);
  const [pedidosPorCliente, setPedidosPorCliente] = useState([]);
  const [carregando, setCarregando] = useState(false);
  const [modalOpenCliente, setModalOpenCliente] = useState(false);
  const [modalExcluirOpen, setModalExcluirOpen] = useState(false);
  const [clienteEditar, setClienteEditar] = useState('');
  const [idClientePorPedido, setIdClientePorPedido] = useState();
  const [telefoneModalCliente, setTelefoneModalCliente] = useState('');
  const [nomeModalCliente, setNomeModalCliente] = useState('');
  const [emailModalCliente, setEmailModalCliente] = useState('');
  const [cpfModalCliente, setCpfModalCliente] = useState('');
  const [dataModalCliente, setDataModalCliente] = useState('');

  const [token, setToken, removeToken] = useLocalStorage('token', '');

  const [tipoAtual, setTipoAtual] = useState('');
  const [tipoTabelao, setTipoTabelao] = useState("clientes");
  const [tipoModalCliente, setTipoModalCliente] = useState("");

  async function verificaToken() {
    try {
      console.log("verificando token..");

      // const promise = await fetch(`${process.env.BD_URL_ADMIN}/verificaToken`, {
      const promise = await fetch(`a042e9cc902ab4cb7ab7bdaa5015bb54-80026177.us-east-1.elb.amazonaws.com/admin/verificaToken`, {
        method: 'GET',
        headers: {
          'Authorization': `${token}`,
        },
      });
      const response = await promise.json();

      if (response.status === 200) {
        return true;
      }
      console.log(response.status);
      return false;
    } catch (err) {
      console.log(err.message);
      return false;
    };
  }


  async function handleCadastrarAdministrador(e) {
    e.preventDefault();
    if (!nomeCadastroAdministrador || !emailCadastroAdministrador || !senhaCadastroAdministrador) {
      toast.warn(`Nome, e-mail e senha necessários!`, {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }
    if (!confirmaSenhaCadastroAdministrador) {
      toast.warn(`Confirme sua senha!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return false;
    }
    if (senhaCadastroAdministrador !== confirmaSenhaCadastroAdministrador) {
      toast.warn(`Senha não cofere!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return false;
    }
    const dados = {
      name: nomeCadastroAdministrador,
      email: emailCadastroAdministrador,
      password: senhaCadastroAdministrador
    };

    // const promise = await fetch(`${process.env.BD_URL_ADMIN}`, {
    const promise = await fetch(`http://a042e9cc902ab4cb7ab7bdaa5015bb54-80026177.us-east-1.elb.amazonaws.com/admin`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(dados)
    })
    
    const response = await promise.json();

    console.log(response);

    if (response.status === 200 || response.status === 201) {
      console.log("administrador cadastrado com sucesso");
      toast.success('administrador cadastrado com sucesso!', {
        position: "top-right",
        autoClose: false,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      setNomeCadastroAdministrador('');
      setEmailCadastroAdiministrador('');
      setSenhaCadastroAdministrador('');
      setConfirmaSenhaCadastroAdministrador('');
      return true;
    }
    toast.warn(`Não foi possivel cadastrar administrador!`, {
      position: "top-right",
      autoClose: 2000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: false,
      draggable: true,
      progress: undefined,
    });
    return false;
  };

  async function handleLogin(e) {
    e.preventDefault();
    try {
      if (!emailLoginAdministrador || !senhaLoginAdministrador) {
        toast.warn(`E-mail e senha necessários!`, {
          position: "top-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
        return false;
      }
      const dados = {
        email: emailLoginAdministrador,
        password: senhaLoginAdministrador
      };

      //const promise = await fetch(`${process.env.BD_URL_ADMIN}/login`, {
      const promise = await fetch(`a042e9cc902ab4cb7ab7bdaa5015bb54-80026177.us-east-1.elb.amazonaws.com/admin/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
      })
      
      const response = await promise.json();

      console.log(response);

      if (response.status !== 200) {
        toast.error('Usuário não encontrado!', {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
        return false;
      }
      toast.success('Login realizado com sucesso!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });

      const { token } = await response.json();
      setToken(token);
      setEmailLoginAdiministrador('');
      setSenhaLoginAdministrador('');
      setTipoTabelao("clientes");
      return true;

    } catch (err) {
      console.log(err.message);
      toast.error('Erro ao conectar-se com o servidor!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return false;
    }
  };

  async function listarClientes(numeroPagina, setConteudoPaginacao) {
    try {
      setCarregando(true);
      const tokenValido = await verificaToken();
      if (!tokenValido) {
        deslogar();
        toast.error('Tempo de conexão expirado, entre novamente!', {
          position: "top-right",
          autoClose: false,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
      }

      // await fetch(`${process.env.BD_URL_CLIENT}?page=${numeroPagina}`, {
      await fetch(`a4a5565ef371b45409b3149010e78197-837387055.us-east-1.elb.amazonaws.com/client?page=${numeroPagina}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      }).then(promise => promise.json()).then(data => {
        setClientes(data.content);
        setConteudoPaginacao(data)
      })
      setCarregando(false);
    } catch (err) {
      //setCarregando(true)
      console.log(err.message);
    };

  };

  async function listarPedidos(numeroPagina, setConteudoPaginacao) {
    try {
      setCarregando(true);
      const tokenValido = await verificaToken();
      if (!tokenValido)
        deslogar();

      //await fetch(`${process.env.BD_URL_ORDER}?page=${numeroPagina}&size=5`, {
      await fetch(`aaf9f8457794848e5959c46c2155b358-527685129.us-east-1.elb.amazonaws.com/order?page=${numeroPagina}&size=5`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      }).then(promise => promise.json()).then(data => {
        setPedidos(data.content);
        setConteudoPaginacao(data)
        setCarregando(false)
        console.log(data)
      })
    } catch (err) {
      console.log(err.message);
    };

  }; // FIM FUNCAO

  async function getPedidosPorClientes(numeroPagina, setConteudoPaginacao) {


    try {
      setCarregando(true);

      //await fetch(`${process.env.BD_URL_ORDER}/client/${idClientePorPedido}?page=${numeroPagina}&size=5`, {
      await fetch(`aaf9f8457794848e5959c46c2155b358-527685129.us-east-1.elb.amazonaws.com/order/client/${idClientePorPedido}?page=${numeroPagina}&size=5`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      }).then(promise => promise.json()).then(data => {
        console.log(data.content);
        setPedidosPorCliente(data.content);
        console.log(pedidosPorCliente);
        setConteudoPaginacao(data)
        setCarregando(false)
      })

    } catch (err) {
      console.log(err.message);
    };

  }


  function separaData(dataString) {
    const separaData = dataString.split("/");
    const dataSeparada = {
      anoNascimeto: separaData[2],
      mesNascimeto: separaData[1],
      diaNascimeto: separaData[0]
    };
    return dataSeparada;
  }
  /*---------------------------------------------------*/
  async function handleCriarCliente(e) {
    e.preventDefault();

    if (!nomeModalCliente || !telefoneModalCliente || !emailModalCliente || !cpfModalCliente || !dataModalCliente) {
      toast.warn(`Todos os campos são necessários!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }

    if (telefoneModalCliente.replace(/\D/g, "").trim().length < 10) {
      toast.warn(`Telefone com numeros insuficientes!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }
    if (cpfModalCliente.replace(/\D/g, "").trim().length !== 11) {
      toast.warn(`CPF com numeros insuficientes!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }
    const data = separaData(dataModalCliente);

    if (data.anoNascimeto.length < 4 || data.diaNascimeto > 31 || data.mesNascimeto > 12 || data.diaNascimeto === 0 || data.mesNascimeto === 0) {
      toast.warn(`Insira uma data valida!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }



    try {
      const dados = {
        name: nomeModalCliente,
        phone: telefoneModalCliente.replace(/\D/g, "").trim(),
        email: emailModalCliente,
        cpf: cpfModalCliente.replace(/\D/g, "").trim(),
        birthdate: new Date(data.anoNascimeto, data.mesNascimeto - 1, data.diaNascimeto).getTime()
      };

      console.log(dados)
      console.log(JSON.stringify(dados))

      //const promise = await fetch(`${process.env.BD_URL_CLIENT}`, {
      const promise = await fetch(`a4a5565ef371b45409b3149010e78197-837387055.us-east-1.elb.amazonaws.com/client`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `${token}`
        },
        body: JSON.stringify(dados)
      });
      const response = await promise.json();

      if (response.status !== 200) {
        toast.warn(`${response.message}`, {
          position: "top-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
        return;
      }

      toast.success('Novo cliente cadastrado com sucesso!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      setModalOpenCliente(false);
    } catch (err) {
      console.log(err.message)
    }
  };

  function deslogar() {
    removeToken('token', '');
  }

  function handleAbrirModalEditarCliente(e) {
    const clienteEditar = clientes.find(cliente => cliente.id === Number(e.target.id));
    setClienteEditar(clienteEditar)
    setTelefoneModalCliente(clienteEditar.phone);
    setNomeModalCliente(clienteEditar.name);
    setEmailModalCliente(clienteEditar.email);
    setDataModalCliente(new Date(clienteEditar.birthdate).toLocaleDateString('pt-BR', { year: 'numeric', month: 'numeric', day: 'numeric' }));
    setCpfModalCliente(clienteEditar.cpf);
    setModalOpenCliente(true);
  };


  async function handleEditarCliente(e) {
    e.preventDefault();
    if (!nomeModalCliente || !telefoneModalCliente || !emailModalCliente || !cpfModalCliente || !dataModalCliente) {
      toast.warn(`Todos os campos são necessários!`, {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      return;
    }
    const { id } = clienteEditar;
    const data = separaData(dataModalCliente);
    console.log(data.anoNascimeto);
    console.log(data.mesNascimeto - 1);
    console.log(data.diaNascimeto);

    try {
      const dados = {
        name: nomeModalCliente,
        phone: telefoneModalCliente.replace(/\D/g, "").trim(),
        email: emailModalCliente,
        cpf: cpfModalCliente.replace(/\D/g, "").trim(),
        birthdate: new Date(data.anoNascimeto, (parseInt(data.mesNascimeto) - 1).toString(), data.diaNascimeto).getTime()
      };

      console.log(dados)
      console.log(JSON.stringify(dados))

      //const promise = await fetch(`${process.env.BD_URL_CLIENT}/${id}`, {
      const promise = await fetch(`a4a5565ef371b45409b3149010e78197-837387055.us-east-1.elb.amazonaws.com/client/${id}`, {
        method: 'PUT',
        body: JSON.stringify(dados),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `${token}`
        },
      });

      const response = await promise.json();

      if (response.status !== 200) {
        toast.warn(`Erro ao atualizar cliente!`, {
          position: "top-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
        return;
      }

      toast.success('Cliente editado com sucesso!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      setModalOpenCliente(false);
    } catch (err) {
      console.log(err.message)
    }
  }

  async function handleExcluirCliente(e) {
    e.preventDefault();
    const { id } = clienteEditar;
    try {
      //const promise = await fetch(`${process.env.BD_URL_CLIENT}/${id}`, {
      const promise = await fetch(`a4a5565ef371b45409b3149010e78197-837387055.us-east-1.elb.amazonaws.com/client/${id}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `${token}`
        }
      });

      const response = await promise.json();

      if (response.status !== 202) {
        toast.warn(`Erro ao excluir cliente!`, {
          position: "top-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
        });
      }
      toast.success('Cliente excluido com sucesso!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
      });
      setModalExcluirOpen(false);
    } catch (err) {
      console.log(err.message)
    }
  }
  const handleDataMask = () => {
    return "99/99/9999";
  };
  const handleTelefoneMask = () => {
    if (telefoneModalCliente) {
      if (telefoneModalCliente[4] === "9") {
        return "(99)99999-9999";
      }
    }
    return "(99)9999-9999";
  };


  function formatCpf(value) {
    const cpf = value.replace(/\D/g, '');

    if (cpf.length === 11) {
      return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3-\$4");
    }

    return value;
  }

  function formatPhone(value) {

    const regex = /^([0-9]{2})([0-9]{4,5})([0-9]{4})$/;
    let str = value.replace(/[^0-9]/g, "").slice(0, 11);

    const result = str.replace(regex, "($1)$2-$3");

    return result;
  }

  function formatDate(value) {
    return new Date(value).toLocaleDateString('pt-BR', { year: 'numeric', month: 'numeric', day: 'numeric' });
  }

  return {
    nomeCadastroAdministrador,
    setNomeCadastroAdministrador,
    emailCadastroAdministrador,
    setEmailCadastroAdiministrador,
    senhaCadastroAdministrador,
    setSenhaCadastroAdministrador,
    confirmaSenhaCadastroAdministrador,
    setConfirmaSenhaCadastroAdministrador,
    emailLoginAdministrador,
    setEmailLoginAdiministrador,
    senhaLoginAdministrador,
    setSenhaLoginAdministrador,
    handleCadastrarAdministrador,
    handleLogin,
    token,
    setToken,
    verificaToken,
    removeToken,
    telefoneModalCliente,
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
    handleExcluirCliente,
    carregando,
    setCarregando,
    ToastContainer,
    toast,
    Router,
    Route,
    Switch,
    Redirect,
    useEffect,
    deslogar,
    modalOpenCliente,
    setModalOpenCliente,
    modalExcluirOpen,
    setModalExcluirOpen,
    clienteEditar,
    setClienteEditar,
    tipoAtual,
    setTipoAtual,
    clientes,
    setClientes,
    listarClientes,
    pedidosPorCliente,
    setPedidosPorCliente,
    handleAbrirModalEditarCliente,
    listarPedidos,
    pedidos,
    setPedidos,
    getPedidosPorClientes,
    tipoTabelao,
    setTipoTabelao,
    tipoModalCliente,
    setTipoModalCliente,
    formatCpf,
    formatPhone,
    formatDate,
    idClientePorPedido,
    setIdClientePorPedido

  }
}

export default useUserProvider;
