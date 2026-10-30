import { useEffect, useState } from "react";
import { api } from "../api.js";
import { scoreClass, pctOf } from "../score.js";

export default function Test({ user, course, unit, onBack, onDashboard }) {
  const [qs, setQs] = useState([]);
  const [answers, setAnswers] = useState({}); // questionId -> chosen option index
  const [result, setResult] = useState(null); // { score, total, correct: {questionId: index} }
  const [err, setErr] = useState("");

  useEffect(() => { api.questions(unit.id).then(setQs); }, [unit.id]);

  const answered = Object.keys(answers).length;
  const pct = result ? pctOf(result.score, result.total) : 0;

  const submit = async () => {
    try {
      setResult(await api.submit(unit.id, user.userId, answers));
      window.scrollTo(0, 0);
    } catch (e) { setErr(e.message); }
  };
  const retake = () => { setAnswers({}); setResult(null); setErr(""); window.scrollTo(0, 0); };

  return (
    <div>
      <button className="link-btn" onClick={onBack}>← Back to units</button>
      <h2 className="page-title">{course.code} · Unit {unit.number}: {unit.title}</h2>

      {!result && (
        <div className="progress">
          <div className="bar"><i style={{ width: (answered * 100) / (qs.length || 1) + "%" }} /></div>
          <span>{answered}/{qs.length} answered</span>
        </div>
      )}

      {result && (
        <div className={"result " + scoreClass(pct)}>
          <div className="big-score">{result.score}/{result.total} ({pct}%)</div>
          <div>{pct > 70 ? "Great job!" : pct >= 40 ? "Good effort, keep practising." : "Revise the unit topics and try again."}</div>
          <div className="actions">
            <button onClick={retake}>Retake test</button>
            <button className="btn-outline" onClick={onDashboard}>View dashboard</button>
          </div>
        </div>
      )}

      {qs.map((q, i) => (
        <div className="q" key={q.id}>
          <p className="qtext">{i + 1}. {q.question}</p>
          {[q.o1, q.o2, q.o3, q.o4].map((opt, k) => {
            let cls = "opt";
            if (result) {
              if (result.correct[q.id] === k) cls += " right";
              else if (answers[q.id] === k) cls += " wrong";
            }
            return (
              <label key={k} className={cls}>
                <input type="radio" name={"q" + q.id} disabled={!!result}
                       checked={answers[q.id] === k}
                       onChange={() => setAnswers({ ...answers, [q.id]: k })} /> {opt}
              </label>
            );
          })}
        </div>
      ))}

      {!result && (
        <div>
          <button disabled={answered < qs.length} onClick={submit}>Submit test</button>
          {answered < qs.length && <span className="hint"> Answer all questions to submit</span>}
          {err && <span className="err"> {err}</span>}
        </div>
      )}
    </div>
  );
}
