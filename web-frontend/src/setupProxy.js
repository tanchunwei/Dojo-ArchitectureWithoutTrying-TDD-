const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
    app.use(
        '/api',
        createProxyMiddleware({
            target: process.env.REACT_APP_API_PROXY_URL,
            changeOrigin: true
        })
    );
    app.use(
        '/websocket',
        createProxyMiddleware({
            target: process.env.REACT_APP_API_PROXY_URL,
            changeOrigin: true
        })
    );
    app.use(
        '/websocket/*',
        createProxyMiddleware({
            target: process.env.REACT_APP_API_PROXY_URL,
            changeOrigin: true
        })
    );
    app.use(
        '/app/*',
        createProxyMiddleware({
            target: process.env.REACT_APP_API_PROXY_URL,
            changeOrigin: true
        })
    );
    app.use(
        '/topic/*',
        createProxyMiddleware({
            target: process.env.REACT_APP_API_PROXY_URL,
            changeOrigin: true
        })
    );
};