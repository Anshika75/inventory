/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#115297',
        secondary: '#383E49',
        formBorder: '#D9D9D9'
      },
    },
  },
  plugins: [],
}