import http from "@/lib/http";

export function getRecommendations(token) {
  return http.get(`/api/v1/users/recommendations`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}
