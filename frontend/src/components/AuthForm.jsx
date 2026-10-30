import { useState } from "react";
import { api } from "../api.js";

const rules = [
  ["At least 8 characters", (p) => p.length >= 8],
  ["One uppercase letter", (p) => /[A-Z]/.test(p)],
  ["One lowercase letter", (p) => /[a-z]/.test(p)],
  ["One number", (p) => /\d/.test(p)],
  ["One special character", (p) => /[^A-Za-z0-9]/.test(p)],
];

export default function AuthForm({ mode, onSwitch, onLogin }) {
  const [form, setForm] = useState({ name: "", dob: "", mobile: "", email: "", password: "" });
  const [msg, setMsg] = useState("");
  const isLogin = mode === "login";
  const set = (k) => (e) => setForm({ ...form, [k]: e.target.value });

  const submit = async (e) => {
    e.preventDefault();
    setMsg("");
    try {
      if (isLogin) {
        onLogin(await api.login(form.email, form.password));
      } else {
        if (!/^[6-9]\d{9}$/.test(form.mobile)) return setMsg("Mobile must be a 10-digit number");
        if (!rules.every(([, ok]) => ok(form.password))) return setMsg("Password does not meet all the rules");
        await api.register(form);
        onSwitch("login");
        setForm({ ...form, password: "" });
        setMsg("Registered! Please log in.");
      }
    } catch (err) {
      setMsg(err.message);
    }
  };

  return (
    <form className="card auth" onSubmit={submit}>
      <h1>GetSetLearn</h1>
      <h2>{isLogin ? "Login" : "Register"}</h2>

      {!isLogin && (
        <>
          <input placeholder="Full name" value={form.name} onChange={set("name")} required />
          <label>Date of birth
            <input type="date" max={new Date().toISOString().split("T")[0]} value={form.dob} onChange={set("dob")} required />
          </label>
          <input type="tel" placeholder="Mobile (10 digits)" maxLength={10} value={form.mobile} onChange={set("mobile")} required />
        </>
      )}

      <input type="email" placeholder="Email" value={form.email} onChange={set("email")} required />
      <input type="password" placeholder="Password" value={form.password} onChange={set("password")} required />

      {!isLogin && (
        <ul className="rules">
          {rules.map(([label, ok]) => (
            <li key={label} className={ok(form.password) ? "ok" : "no"}>
              {ok(form.password) ? "✓" : "✗"} {label}
            </li>
          ))}
        </ul>
      )}

      <button type="submit">{isLogin ? "Login" : "Create account"}</button>
      {msg && <p className="msg">{msg}</p>}
      <p>
        {isLogin ? "New here? " : "Already registered? "}
        <a href="#" onClick={(e) => { e.preventDefault(); setMsg(""); onSwitch(isLogin ? "register" : "login"); }}>
          {isLogin ? "Register" : "Login"}
        </a>
      </p>
    </form>
  );
}
