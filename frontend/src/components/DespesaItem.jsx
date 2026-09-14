function DespesaItem({ despesa }) {
  return (
    <li style={{ padding: "8px 0", borderBottom: "1px solid #eee" }}>
      <strong>{despesa.descricao}</strong> — R$ {despesa.valor.toFixed(2)} |{" "}
      <span style={{ color: "#666" }}>{despesa.categoria}</span> | Data: {despesa.data} |{" "}
      <span>{despesa.paga ? "✅ Paga" : "⏳ Pendente"}</span>
    </li>
  );
}

export default DespesaItem;