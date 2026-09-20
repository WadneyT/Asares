import { useState } from "react";
import "./Dashboard.css";

import DespesaList from "../components/DespesaList";
import ReceitaList from "../components/ReceitaList";
import NovaDespesa from "../components/NovaDespesa";
import NovaReceita from "../components/NovaReceita";


function Dashboard({ onLogout }) {

    const [mostrarDespesa, setMostrarDespesa] = useState(false);
    const [mostrarReceita, setMostrarReceita] = useState(false);

    const [atualizarListas, setAtualizarListas] = useState(0);

    function atualizarDados() {
        setAtualizarListas((valor) => valor + 1);
    }

    function despesaCriada() {
        setMostrarDespesa(false);
        atualizarDados();
    }

    function receitaCriada() {
        setMostrarReceita(false);
        atualizarDados();
    }

    return (
        <div className="dashboard">

            {/* CABEÇALHO */}

            <header className="dashboard-header">

                <div>
                    <h1>ASARES</h1>
                    <p>Painel Financeiro</p>
                </div>

                <button
                    onClick={onLogout}
                    className="logout-button"
                >
                    Sair
                </button>

            </header>


            {/* AÇÕES */}

            <section className="actions">

                <button
                    className="action-button expense-button"
                    onClick={() => {
                        setMostrarDespesa(!mostrarDespesa);
                        setMostrarReceita(false);
                    }}
                >
                    + Nova despesa
                </button>

                <button
                    className="action-button income-button"
                    onClick={() => {
                        setMostrarReceita(!mostrarReceita);
                        setMostrarDespesa(false);
                    }}
                >
                    + Nova receita
                </button>

            </section>


            {/* FORMULÁRIO DE DESPESA */}

            {mostrarDespesa && (
                <NovaDespesa
                    onCriada={despesaCriada}
                    onCancelar={() => setMostrarDespesa(false)}
                />
            )}


            {/* FORMULÁRIO DE RECEITA */}

            {mostrarReceita && (
                <NovaReceita
                    onCriada={receitaCriada}
                    onCancelar={() => setMostrarReceita(false)}
                />
            )}


            {/* DESPESAS */}

            <section className="finance-section">

                <div className="section-title">

                    <div>
                        <h2>Minhas despesas</h2>
                        <p>Controle seus gastos</p>
                    </div>

                    <button
                        className="small-add-button expense-button"
                        onClick={() => {
                            setMostrarDespesa(true);
                            setMostrarReceita(false);
                        }}
                    >
                        + Adicionar
                    </button>

                </div>

                <DespesaList
                    key={`despesas-${atualizarListas}`}
                />

            </section>


            {/* RECEITAS */}

            <section className="finance-section">

                <div className="section-title">

                    <div>
                        <h2>Minhas receitas</h2>
                        <p>Acompanhe seu dinheiro entrando</p>
                    </div>

                    <button
                        className="small-add-button income-button"
                        onClick={() => {
                            setMostrarReceita(true);
                            setMostrarDespesa(false);
                        }}
                    >
                        + Adicionar
                    </button>

                </div>

                <ReceitaList
                    key={`receitas-${atualizarListas}`}
                />

            </section>

        </div>
    );
}

export default Dashboard;