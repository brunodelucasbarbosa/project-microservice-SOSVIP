import React from 'react'
import Arrow from '../../assets/arrow.svg'
import './styles.css'

function Paginacao({conteudoPaginacao, handleTrocarPagina}) {

  return (
    <div className="pagination-container">
      <div className="pagination-box">
        <button className="pagination-button"
          disabled={conteudoPaginacao.first}
          onClick={() => handleTrocarPagina(conteudoPaginacao.number - 1)} >
          <img src={Arrow} alt="seta paginacao"/>
        </button>

        <p>{`${conteudoPaginacao.number + 1} de ${conteudoPaginacao.totalPages}`}</p>

        <button className="pagination-button"
          disabled={conteudoPaginacao.last}
          onClick={() => handleTrocarPagina(conteudoPaginacao.number + 1)} >
          <img src={Arrow} className="flip-horizontal" alt="seta paginacao"/>
        </button>
      </div>
    </div >
  )
}

export default Paginacao