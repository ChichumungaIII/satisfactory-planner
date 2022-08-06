if (config) {
    if (config.devServer) {
        config.devServer.historyApiFallback = true
    }
    if (config.output) {
        config.output.publicPath = "/"
    }
}
