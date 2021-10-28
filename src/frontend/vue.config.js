const path = require("path");
module.exports = {
    // vue로 인해 bulid될 때 출력되는 static파일들이 아래의 경로로 떨어지도록 설정
    outputDir: path.resolve(__dirname, "../main/resources/static"),
    // Vue Proxy 설정
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:9000',
                ws: true,
                changeOrigin: true
            },
        }
    }
}