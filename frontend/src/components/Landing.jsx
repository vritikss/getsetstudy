const features = [
  ["📚", "Course-wise learning", "Five core subjects, each split into five units that follow our syllabus."],
  ["📝", "Unit tests", "Take a 10-question test on a unit and see the correct answers as soon as you submit."],
  ["📊", "Track your progress", "Your dashboard shows every score, colour-coded so you know what to revise."],
];
const subjects = ["Artificial Intelligence", "Full Stack Development", "Computer Networks", "Machine Learning", "Cloud Computing"];

export default function Landing({ onStart }) {
  return (
    <div className="landing">
      <section className="hero">
        <h1>Learn your syllabus, one unit at a time.</h1>
        <p>
          GetSetLearn is an online learning platform built around our B.Tech coursework. Pick a subject,
          enroll, go through its five units and test yourself with unit-wise quizzes, then check how you
          are doing on your personal dashboard.
        </p>
        <button className="big" onClick={onStart}>Get started</button>
      </section>

      <section className="features">
        {features.map(([icon, title, text]) => (
          <div className="feature" key={title}>
            <div className="icon">{icon}</div>
            <h3>{title}</h3>
            <p>{text}</p>
          </div>
        ))}
      </section>

      <section className="subjects">
        <h2>Subjects you can learn</h2>
        <div className="chips">{subjects.map((s) => <span className="chip" key={s}>{s}</span>)}</div>
      </section>
    </div>
  );
}
