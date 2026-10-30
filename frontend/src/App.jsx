import { useState } from "react";
import Landing from "./components/Landing.jsx";
import AuthForm from "./components/AuthForm.jsx";
import Courses from "./components/Courses.jsx";
import Units from "./components/Units.jsx";
import Test from "./components/Test.jsx";
import Dashboard from "./components/Dashboard.jsx";

export default function App() {
  const [user, setUser] = useState(() => JSON.parse(localStorage.getItem("user") || "null"));
  const [guestPage, setGuestPage] = useState("home"); // home | login | register
  const [page, setPage] = useState("courses");        // courses | units | test | dashboard
  const [course, setCourse] = useState(null);
  const [unit, setUnit] = useState(null);

  const login = (u) => { localStorage.setItem("user", JSON.stringify(u)); setUser(u); setPage("courses"); };
  const logout = () => { localStorage.removeItem("user"); setUser(null); setGuestPage("home"); };

  // ---------- before login: landing page + Login / Register at top right ----------
  if (!user)
    return (
      <>
        <nav className="topbar">
          <b className="logo" onClick={() => setGuestPage("home")}>GetSetLearn</b>
          <div className="nav-right">
            <button className="btn-outline" onClick={() => setGuestPage("login")}>Login</button>
            <button onClick={() => setGuestPage("register")}>Register</button>
          </div>
        </nav>
        {guestPage === "home"
          ? <Landing onStart={() => setGuestPage("register")} />
          : <AuthForm mode={guestPage} onSwitch={setGuestPage} onLogin={login} />}
      </>
    );

  // ---------- after login ----------
  const tab = (p, label, active) => (
    <button className={"nav-btn" + (active ? " active" : "")} onClick={() => setPage(p)}>{label}</button>
  );
  return (
    <>
      <nav className="topbar">
        <b className="logo">GetSetLearn</b>
        <div className="nav-right">
          {tab("courses", "Courses", page !== "dashboard")}
          {tab("dashboard", "Dashboard", page === "dashboard")}
          <span className="who">{user.name || user.email}</span>
          <button className="btn-outline" onClick={logout}>Logout</button>
        </div>
      </nav>
      <main className="app">
        {page === "courses" && <Courses user={user} onOpen={(c) => { setCourse(c); setPage("units"); }} />}
        {page === "units" && <Units course={course} onBack={() => setPage("courses")} onStart={(u) => { setUnit(u); setPage("test"); }} />}
        {page === "test" && <Test user={user} course={course} unit={unit} onBack={() => setPage("units")} onDashboard={() => setPage("dashboard")} />}
        {page === "dashboard" && <Dashboard user={user} />}
      </main>
    </>
  );
}
