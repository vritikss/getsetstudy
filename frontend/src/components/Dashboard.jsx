import { useEffect, useState } from "react";
import { api } from "../api.js";
import { scoreClass, pctOf } from "../score.js";

const when = (t) => t.replace("T", " ").slice(0, 16);

function Badge({ a }) {
  const p = pctOf(a.score, a.total);
  return <span className={"badge " + scoreClass(p)}>{a.score}/{a.total} · {p}%</span>;
}

export default function Dashboard({ user }) {
  const [d, setD] = useState(null);
  useEffect(() => { api.dashboard(user.userId).then(setD); }, []);
  if (!d) return <p>Loading...</p>;

  // attempts come newest-first, so the first row seen for each unit is its latest score
  const seen = new Set();
  const latest = d.attempts.filter((a) => {
    const k = a.course + a.unit;
    if (seen.has(k)) return false;
    seen.add(k);
    return true;
  });
  const avg = latest.length ? Math.round(latest.reduce((s, a) => s + pctOf(a.score, a.total), 0) / latest.length) : null;

  return (
    <div>
      <h2 className="page-title">Dashboard</h2>
      <p className="sub">{d.email}</p>

      <div className="stats">
        <div className="stat"><b>{d.enrolled.length}</b><span>Courses enrolled</span></div>
        <div className="stat"><b>{d.attempts.length}</b><span>Tests taken</span></div>
        <div className={"stat " + (avg === null ? "" : scoreClass(avg))}>
          <b>{avg === null ? "-" : avg + "%"}</b><span>Average (latest scores)</span>
        </div>
      </div>

      <div className="panel">
        <b>Enrolled courses: </b>
        {d.enrolled.length ? d.enrolled.map((n) => <span className="chip" key={n}>{n}</span>) : "None yet"}
      </div>

      <div className="legend">
        <span className="badge good">Above 70% — great</span>
        <span className="badge mid">40–70% — okay</span>
        <span className="badge bad">Below 40% — revise</span>
      </div>

      <h3>Most recent score per unit</h3>
      <table>
        <thead><tr><th>Course</th><th>Unit</th><th>Score</th><th>Taken at</th></tr></thead>
        <tbody>
          {latest.map((a, i) => (
            <tr key={i}><td>{a.course}</td><td>{a.unit}</td><td><Badge a={a} /></td><td>{when(a.takenAt)}</td></tr>
          ))}
          {!latest.length && <tr><td colSpan="4">No tests taken yet</td></tr>}
        </tbody>
      </table>

      <h3>All attempts</h3>
      <table>
        <thead><tr><th>Course</th><th>Unit</th><th>Score</th><th>Taken at</th></tr></thead>
        <tbody>
          {d.attempts.map((a, i) => (
            <tr key={i}><td>{a.course}</td><td>{a.unit}</td><td><Badge a={a} /></td><td>{when(a.takenAt)}</td></tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
