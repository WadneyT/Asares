import { useEffect, useState } from "react";
import api from "../services/api";
import DespesaItem from "./DespesaItem";

function DespesaList() {
  const [despesas, setDespesas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    api.get("/despesas")
      .then((resposta) => {
        setDespesas(resposta.data);
        setLoading(false);
      })
      .catch(() => {
        setErro("Erro ao carregar despesas.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Carregando despesas...</p>;
  if (erro) return <p style={{ color: "red" }}>{erro}</p>;
  if (despesas.length === 0) return <p>Nenhuma despesa cadastrada.</p>;

  return (
    <ul style={{ listStyle: "none", paddingLeft: 0 }}>
      {despesas.map((despesa) => (
        <DespesaItem key={despesa.id} despesa={despesa} />
      ))}
    </ul>
  );
}

export default DespesaList;