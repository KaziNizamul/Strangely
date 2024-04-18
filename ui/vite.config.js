import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import eslint from 'vite-plugin-eslint';
import path from 'path';

export default defineConfig({
  plugins: [react(), eslint()],
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
    },
  },
  resolve: {
    alias: {
      '@organism/': path.resolve('./src/components/organisms'),
      '@http': path.resolve('./src/services/http'),
    },
  },
});
