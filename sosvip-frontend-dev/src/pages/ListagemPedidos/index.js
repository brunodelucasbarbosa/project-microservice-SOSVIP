// import { ToastContainer } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';
// import { useEffect, useState } from 'react';
// import HeaderListagem from '../../components/HeaderListagem';
// import Tabelao from '../../components/Tabelao';



// function ListagemPedidos(props) {

//   //const [carregando, setCarregando] = useState(false);
//   const [pedidosPorCliente, setPedidosPorCliente] = useState([]);

//   // async function getPedidosPorClientes() {

//   //   const idDoCliente = props.location.pathname[props.location.pathname.length - 1]

//   //   try {
//   //     setCarregando(true);

//   //     await fetch(`http://localhost:8082/orders/client/${idDoCliente}`, {
//   //       method: 'GET',
//   //       headers: {
//   //         'Content-Type': 'application/json'
//   //       },
//   //     }).then(promise => promise.json()).then(data => {
//   //       console.log(data);
//   //       setPedidosPorCliente(data.content);
//   //       setCarregando(false)
//   //     })

//   //   } catch (err) {
//   //     console.log(err.message);
//   //   };

//   // }

//   function formartarData(data) {
//     //TODO: COLOCAR NA PASTA UTILS PARA GLOBALIZAR
//     const ano = data.substr(0, 4);
//     const mes = data.substr(5, 2);
//     const dia = data.substr(8, 2);
//     return `${dia}/${mes}/${ano}`;
//   }

//   // useEffect(() => {
//   //   getPedidosPorClientes();
//   //   //eslint-disable-next-line
//   // });

//   return (
//     <main className="tela-contatos">
//       <ToastContainer
//         className="toast-error"
//         position="top-right"
//         autoClose={2000}
//         hideProgressBar={false}
//         newestOnTop={false}
//         closeOnClick
//         rtl={false}
//         pauseOnFocusLoss
//         draggable
//         pauseOnHover={false}
//         theme="colored"
//       />
//       <HeaderListagem />
//       <section className="section-contatos">
//         <div className="div-botao">
//         </div>
//           {/* <Paginacao /> */}
//         <Tabelao />
//       </section>
//     </main>
//   );
// }

// export default ListagemPedidos;
