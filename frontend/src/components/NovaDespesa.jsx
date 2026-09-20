import { useState } from "react";
import api from "../services/api";

function NovaDespesa({ onCriada, onCancelar }) {
    const [descricao, setDescricao] = useState("");
    const [valor, setValor] = useState("");
    const [data, setData] = useState("");
    const [categoria, setCategoria] = useState("");
    const [paga, setPaga] = useState(false);
    const [erro, setErro] = useState("");

    async function handleSubmit(e) {
        e.preventDefault();
        setErro("");

        try {
            await api.post("/despesas", {
                descricao,
                valor: Number(valor),
                data,
                categoria,
                paga
            });

            setDescricao("");
            setValor("");
            setData("");
            setCategoria("");
            setPaga(false);

            onCriada();

        } catch (err) {
            console.error("Erro ao cadastrar despesa:", err);

            setErro(
                err.response?.data?.message ||
                "Não foi possível cadastrar a despesa."
            );
        }
    }

    return (
        <div className="form-card">

            <h3>Nova despesa</h3>

            {erro && <p className="form-error">{erro}</p>}

            <form onSubmit={handleSubmit}>

                <label>Descrição</label>
                <input
                    type="text"
                    value={descricao}
                    onChange={(e) => setDescricao(e.target.value)}
                    placeholder="Ex.: Mercado"
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
                    placeholder="Ex.: Alimentação"
                />

                <label className="checkbox-label">
                    <input
                        type="checkbox"
                        checked={paga}
                        onChange={(e) => setPaga(e.target.checked)}
                    />

                    Despesa já paga
                </label>

                <div className="form-buttons">

                    <button
                        type="submit"
                        className="button-primary"
                    >
                        Cadastrar despesa
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

export default NovaDespesa;