import http from "@/lib/http";

export function loadFavorites(token, page = 0, size = 10) {
  return http.get("/api/v1/favorites/getAll", {
    params: { page, size },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}
