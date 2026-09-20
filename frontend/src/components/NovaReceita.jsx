import { useState } from "react";
import api from "../services/api";

function NovaReceita({ onCriada, onCancelar }) {
    const [descricao, setDescricao] = useState("");
    const [valor, setValor] = useState("");
    const [data, setData] = useState("");
    const [categoria, setCategoria] = useState("");
    const [erro, setErro] = useState("");

    async function handleSubmit(e) {
        e.preventDefault();
        setErro("");

        try {
            await api.post("/receitas", {
                descricao,
                valor: Number(valor),
                data,
                categoria
            });

            setDescricao("");
            setValor("");
            setData("");
            setCategoria("");

            onCriada();

        } catch (err) {
            console.error("Erro ao cadastrar receita:", err);

            setErro(
                err.response?.data?.message ||
                "Não foi possível cadastrar a receita."
            );
        }
    }

    return (
        <div className="form-card">

            <h3>Nova receita</h3>

            {erro && <p className="form-error">{erro}</p>}

            <form onSubmit={handleSubmit}>

                <label>Descrição</label>
                <input
                    type="text"
                    value={descricao}
                    onChange={(e) => setDescricao(e.target.value)}
                    placeholder="Ex.: Salário"
                    required
                />

                <label>Valor</label>
                <input
                    type="number"
                    step="0.01"
                    min="0.01"
                    value={valor}
                    onChange={(e) => setValor(e.target.value)}
                    placeholder="0,00"
                    required
                />

                <label>Data</label>
                <input
                    type="date"
                    value={data}
                    onChange={(e) => setData(e.target.value)}
                    required
                />

                <label>Categoria</label>
                <input
                    type="text"
                    value={categoria}
                    onChange={(e) => setCategoria(e.target.value)}
                    placeholder="Ex.: Salário"
                />

                <div className="form-buttons">

                    <button
                        type="submit"
                        className="button-primary"
                    >
                        Cadastrar receita
                    </button>

                    <button
                        type="button"
                        className="button-secondary"
                        onClick={onCancelar}
                    >
                        Cancelar
                    </button>

                </div>

            </form>
        </div>
    );
}

export default NovaReceita;