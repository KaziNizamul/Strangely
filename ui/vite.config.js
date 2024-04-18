import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import eslint from 'vite-plugin-eslint';
import path from 'path';
import fs from 'fs/promises';

export default defineConfig({
  plugins: [react(), eslint()],
  esbuild: {
    loader: 'jsx',
    include: /src\/.*\.jsx?$/,
    // loader: "tsx",
    // include: /src\/.*\.[tj]sx?$/,
    exclude: [],
  },
  build: {
    outDir: 'dist',
    rollupOptions: {
      input: './src/core/index.jsx',
    },
  },
  server: {
    host: '0.0.0.0',
  },
  test: {
    globals: true,
  },
  optimizeDeps: {
    force: true,
    esbuildOptions: {
      loader: {
        '.js': 'jsx',
      },
      plugins: [
        {
          name: 'load-js-files-as-jsx',
          setup(build) {
            build.onLoad({ filter: /src\/.*\.js$/ }, async args => ({
              loader: 'jsx',
              contents: await fs.readFile(args.path, 'utf8'),
            }));
          },
        },
      ],
    },
  },
  resolve: {
    alias: {
      '@organism/': path.resolve('./src/components/organisms'),
      '@http': path.resolve('./src/services/http'),
    },
  },
});
