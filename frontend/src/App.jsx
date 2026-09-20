import { useState } from "react";

import Login from "./components/Login";
import Cadastro from "./components/Cadastro";
import Dashboard from "./pages/Dashboard";

function App() {
  const [token, setToken] = useState(
      localStorage.getItem("token") || ""
  );

  const [mostrarCadastro, setMostrarCadastro] = useState(false);

  function handleLogout() {
    localStorage.removeItem("token");
    setToken("");
    setMostrarCadastro(false);
  }

  // Usuário não está logado
  if (!token) {

    // Tela de cadastro
    if (mostrarCadastro) {
      return (
          <Cadastro
              setToken={setToken}
              onVoltarLogin={() => setMostrarCadastro(false)}
          />
      );
    }

    // Tela de login
    return (
        <Login
            setToken={setToken}
            onCriarConta={() => setMostrarCadastro(true)}
        />
    );
  }

  // Usuário está logado
  return (
      <Dashboard
          onLogout={handleLogout}
      />
  );
}

export default App;