import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    // settings to true allows the host to listen on all IP addresses
    // https://vitejs.dev/config/server-options.html
    host: true,
    // TODO: unsure what to set here, I think default is 4173
    port: 3000
  }
})
