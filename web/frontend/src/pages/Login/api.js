import http from "@/lib/http";

export function login(body) {
  return http.post("/api/v1/auth/login", body);
}
