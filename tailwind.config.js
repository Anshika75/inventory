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
        tertiary: '#667085',
        formBorder: '#D9D9D9',
        key: '#858D9D',
        value: '#5D6679'
      },
    },
  },
  plugins: [],
}