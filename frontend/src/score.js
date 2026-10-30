// above 70 = green, 40 to 70 = yellow, below 40 = red
export const scoreClass = (pct) => (pct > 70 ? "good" : pct >= 40 ? "mid" : "bad");
export const pctOf = (score, total) => (total ? Math.round((score * 100) / total) : 0);
