const path = require('path');

module.exports = {
    entry: ['babel-polyfill', './src/index.js'],
    output: {
        path: __dirname,
        filename: 'bundle.js',
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },
            {
                test: /\.css$/,
                use:['style-loader', 'css-loader']
            }
        ]
    },
    devServer: {
        historyApiFallback: true,
    }
};