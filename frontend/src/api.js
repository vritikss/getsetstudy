const BASE = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

async function req(path, method = "GET", body) {
  const res = await fetch(BASE + path, {
    method,
    headers: { "Content-Type": "application/json" },
    body: body ? JSON.stringify(body) : undefined,
  });
  const data = await res.json().catch(() => ({}));
  if (!res.ok) throw new Error(data.error || "Request failed");
  return data;
}

export const api = {
  register: (data) => req("/auth/register", "POST", data),
  login: (email, password) => req("/auth/login", "POST", { email, password }),
  courses: (uid) => req(`/courses?userId=${uid}`),
  enroll: (cid, uid) => req(`/courses/${cid}/enroll?userId=${uid}`, "POST"),
  unenroll: (cid, uid) => req(`/courses/${cid}/enroll?userId=${uid}`, "DELETE"),
  units: (cid) => req(`/courses/${cid}/units`),
  questions: (unitId) => req(`/units/${unitId}/questions`),
  submit: (unitId, userId, answers) => req(`/units/${unitId}/submit`, "POST", { userId, answers }),
  dashboard: (uid) => req(`/dashboard/${uid}`),
};
