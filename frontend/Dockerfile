# ===== Build Stage =====
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# ===== Run Stage (Nginx) =====
FROM nginx:1.25-alpine
# احذف الإعداد الافتراضي واستبدله بإعداد بسيط
RUN rm -f /etc/nginx/conf.d/default.conf
COPY <<'NGINX' /etc/nginx/conf.d/site.conf
server {
  listen 80;
  server_name _;

  location / {
    root   /usr/share/nginx/html;
    try_files $uri /index.html;
    index  index.html;
  }
}
NGINX

COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
