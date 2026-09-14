import { useEffect, useState } from "react";
import api from "../services/api";
import ReceitaItem from "./ReceitaItem";

function ReceitaList() {
  const [receitas, setReceitas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    api.get("/receitas")
      .then((resposta) => {
        setReceitas(resposta.data);
        setLoading(false);
      })
      .catch(() => {
        setErro("Erro ao carregar receitas.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Carregando receitas...</p>;
  if (erro) return <p style={{ color: "red" }}>{erro}</p>;
  if (receitas.length === 0) return <p>Nenhuma receita cadastrada.</p>;

  return (
    <ul style={{ listStyle: "none", paddingLeft: 0 }}>
      {receitas.map((receita) => (
        <ReceitaItem key={receita.id} receita={receita} />
      ))}
    </ul>
  );
}

export default ReceitaList;