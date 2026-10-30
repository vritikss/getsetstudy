import { useEffect, useState } from "react";
import { api } from "../api.js";

export default function Units({ course, onBack, onStart }) {
  const [units, setUnits] = useState([]);
  useEffect(() => { api.units(course.id).then(setUnits); }, [course.id]);

  return (
    <div>
      <button className="link-btn" onClick={onBack}>← Back to courses</button>
      <h2 className="page-title">{course.name}</h2>
      <p className="sub">{course.description}</p>
      {units.map((u) => (
        <div className="unit" key={u.id}>
          <div className="num">{u.number}</div>
          <div className="info">
            <h3>{u.title}</h3>
            <p>{u.topics}</p>
          </div>
          {u.questionCount > 0
            ? <button onClick={() => onStart(u)}>Start test ({u.questionCount} Qs)</button>
            : <span className="soon">Test coming soon</span>}
        </div>
      ))}
    </div>
  );
}
