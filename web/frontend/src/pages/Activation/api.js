import http from "@/lib/http";

export function activateUser(token) {
  return http.get("/api/v1/auth/activate-account", { params: { token } });
}
