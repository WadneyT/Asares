import { useState } from "react";
import api from "./services/api";
import DespesaList from "./components/DespesaList";
import ReceitaList from "./components/ReceitaList";

function App() {
  const [token, setToken] = useState(localStorage.getItem("token") || "");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erroLogin, setErroLogin] = useState("");

  async function handleLogin(e) {
    e.preventDefault();
    setErroLogin("");

    try {
      const resposta = await api.post("/auth/login", { email, senha });
      const jwt = resposta.data.token;
      localStorage.setItem("token", jwt);
      setToken(jwt);
    } catch (err) {
      setErroLogin("Falha na autenticação. Verifique seu e-mail e senha.");
    }
  }

  function handleLogout() {
    localStorage.removeItem("token");
    setToken("");
  }

  if (!token) {
    return (
      <div style={{ maxWidth: "400px", margin: "50px auto", fontFamily: "sans-serif", padding: "20px", border: "1px solid #ddd", borderRadius: "8px" }}>
        <h2>Login - Finanças</h2>
        {erroLogin && <p style={{ color: "red" }}>{erroLogin}</p>}
        <form onSubmit={handleLogin}>
          <div style={{ marginBottom: "10px" }}>
            <label>E-mail:</label>
            <input
              style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div style={{ marginBottom: "15px" }}>
            <label>Senha:</label>
            <input
              style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />
          </div>
          <button style={{ width: "100%", padding: "10px", background: "#007bff", color: "#fff", border: "none", borderRadius: "4px", cursor: "pointer" }}>
            Entrar
          </button>
        </form>
      </div>
    );
  }

  return (
    <div style={{ maxWidth: "800px", margin: "30px auto", fontFamily: "sans-serif", padding: "20px" }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h1>Painel Financeiro</h1>
        <button onClick={handleLogout} style={{ padding: "8px 16px", background: "#dc3545", color: "#fff", border: "none", borderRadius: "4px", cursor: "pointer" }}>
          Sair
        </button>
      </div>

      <div style={{ marginTop: "30px" }}>
        <h2>Minhas Despesas</h2>
        <DespesaList />
      </div>

      <div style={{ marginTop: "30px" }}>
        <h2>Minhas Receitas</h2>
        <ReceitaList />
      </div>
    </div>
  );
}

export default App;