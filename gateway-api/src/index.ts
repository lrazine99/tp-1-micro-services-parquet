import express from "express";
import { createProxyMiddleware } from "http-proxy-middleware";

const app = express();
const PORT = process.env.PORT || 3000;

// Exemple de proxy vers un microservice utilisateur
app.use('/api/order', createProxyMiddleware({
  target: 'http://order-service:8081',
  changeOrigin: true,
}));

// Exemple de proxy vers un microservice produit
app.use('/api/products', createProxyMiddleware({
  target: 'http://product-service:8082',
  changeOrigin: true,
}));

app.listen(PORT, () => {
  console.log(`API Gateway is running on http://localhost:${PORT}`);
});
