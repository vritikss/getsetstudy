import { useEffect, useState } from "react";
import { api } from "../api.js";

const colors = { AI: "#4f46e5", FSD: "#0891b2", CN: "#ea580c", ML: "#9333ea", CC: "#0d9488" };

export default function Courses({ user, onOpen }) {
  const [courses, setCourses] = useState([]);
  const load = () => api.courses(user.userId).then(setCourses);
  useEffect(() => { load(); }, []);

  const toggle = async (c) => {
    c.enrolled ? await api.unenroll(c.id, user.userId) : await api.enroll(c.id, user.userId);
    load();
  };

  return (
    <div>
      <h2 className="page-title">Courses</h2>
      <p className="sub">Enroll in a subject to unlock its units and tests.</p>
      <div className="grid">
        {courses.map((c) => (
          <div className="course" key={c.id}>
            <div className="strip" style={{ background: colors[c.code] || "#4f46e5" }}>
              <span className="code">{c.code}</span>
              <span className={"status " + (c.enrolled ? "on" : "off")}>{c.enrolled ? "Enrolled" : "Not enrolled"}</span>
            </div>
            <div className="body">
              <h3>{c.name}</h3>
              <p>{c.description}</p>
              <div className="actions">
                {c.enrolled && <button onClick={() => onOpen(c)}>Open course</button>}
                <button className={c.enrolled ? "btn-outline" : ""} onClick={() => toggle(c)}>
                  {c.enrolled ? "Unenroll" : "Enroll"}
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
