import { useState } from "react";
import api from "../services/api";

function Login({ setToken, onCriarConta }) {
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [erroLogin, setErroLogin] = useState("");

    async function handleLogin(e) {
        e.preventDefault();
        setErroLogin("");

        try {
            const resposta = await api.post("/auth/login", {
                email,
                senha
            });

            const jwt = resposta.data.token;

            localStorage.setItem("token", jwt);
            setToken(jwt);

        } catch (err) {
            setErroLogin(
                "Falha na autenticação. Verifique seu e-mail e senha."
            );
        }
    }

    return (
        <div
            style={{
                maxWidth: "400px",
                margin: "50px auto",
                fontFamily: "sans-serif",
                padding: "20px",
                border: "1px solid #ddd",
                borderRadius: "8px"
            }}
        >
            <h2>Login - Finanças</h2>

            {erroLogin && (
                <p style={{ color: "red" }}>
                    {erroLogin}
                </p>
            )}

            <form onSubmit={handleLogin}>
                <div style={{ marginBottom: "10px" }}>
                    <label>E-mail:</label>

                    <input
                        style={{
                            width: "100%",
                            padding: "8px",
                            boxSizing: "border-box"
                        }}
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <div style={{ marginBottom: "15px" }}>
                    <label>Senha:</label>

                    <input
                        style={{
                            width: "100%",
                            padding: "8px",
                            boxSizing: "border-box"
                        }}
                        type="password"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        required
                    />
                </div>

                <button
                    type="submit"
                    style={{
                        width: "100%",
                        padding: "10px",
                        background: "#007bff",
                        color: "#fff",
                        border: "none",
                        borderRadius: "4px",
                        cursor: "pointer"
                    }}
                >
                    Entrar
                </button>
            </form>

            <p style={{ marginTop: "20px", textAlign: "center" }}>
                Ainda não possui uma conta?
            </p>

            <button
                onClick={onCriarConta}
                style={{
                    width: "100%",
                    padding: "10px",
                    background: "#6c757d",
                    color: "#fff",
                    border: "none",
                    borderRadius: "4px",
                    cursor: "pointer"
                }}
            >
                Criar usuário
            </button>
        </div>
    );
}

export default Login;