// rollup.config.js
import commonjs from '@rollup/plugin-commonjs';
import resolve from '@rollup/plugin-node-resolve';
import babel from '@rollup/plugin-babel';
import {
    terser
} from 'rollup-plugin-terser';

export default {
    input: 'src/index.js',
    output: [{
        file: 'release/web-monitor-sdk-client.js',
        format: 'umd',
        name: 'webMonitorSdkClient'
    }, {
        file: 'release/web-monitor-sdk-client.min.js',
        format: 'iife',
        name: 'webMonitorSdkClient',
        plugins: [terser()]
    }],
    plugins: [
        commonjs(),
        resolve({
            browser: true
        }),
        babel({
            exclude: 'node_modules/**',
            babelHelpers: 'runtime',
            plugins: ['@babel/plugin-transform-runtime']
        })
    ]
};