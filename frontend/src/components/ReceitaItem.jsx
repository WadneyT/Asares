function ReceitaItem({ receita }) {
  return (
    <li style={{ padding: "8px 0", borderBottom: "1px solid #eee" }}>
      <strong>{receita.descricao}</strong> — R$ {receita.valor.toFixed(2)} |{" "}
      <span style={{ color: "#666" }}>{receita.categoria}</span> | Data: {receita.data}
    </li>
  );
}

export default ReceitaItem;